/**
 * Compress a file using a Huffman Tree.A
 */
import java.io.*;
import java.util.*;

public class Compress
{
	/**
	 *
	 */
	public static void main(String[] args)
	{
		if ( args.length < 2 ) {
			usage();
			System.exit(0);
		}

		FileReader in = null;
		OutputBitStream out = null;

		try {
			in = new FileReader(args[0]);

			Set<Map.Entry<Character,Integer>> set;
			set = computeFrequency(in);

			in.close();

			Map<Character,String> map;
			map = HTree.buildMap(set);

			in = new FileReader(args[0]);
			out = new OutputBitStream(args[1]);

			writeHeader(out, map.entrySet(), computeTotal(set), 3);
			writeData(out, in, map);

			in.close();
			out.close();
		}
		catch ( IOException e ) {
			try {
				in.close();
			}
			catch ( Exception f ) {}
			try {
				out.close();
			}
			catch ( Exception f ) {}
			e.printStackTrace(System.err);
			System.exit(1);
		}

	}

	/**
	 *
	 */
	public static long computeTotal(Set<Map.Entry<Character,Integer>> set)
	{
		Iterator<Map.Entry<Character,Integer>> iter;
		iter = set.iterator();

		long total = 0;

		while ( iter.hasNext() ) {
			total += iter.next().getValue();
		}

		return total;
	}

	/**
	 * Help
	 */
	public static void usage()
	{
		System.out.println("USAGE:");
		System.out.println("\tjava Compress <input file> <output file>");
	}

	/**
	 *
	 */
	public static void writeHeader(OutputBitStream out, Set<Map.Entry<Character,String>> set, long filelen, int version) throws IOException
	{
		if ( version == 2 ) {
			out.write( (byte)2 );
			out.write( (short)set.size() );
			out.write( filelen );

			Iterator<Map.Entry<Character,String>> iter;
			iter = set.iterator();

			while ( iter.hasNext() ) {
				Map.Entry<Character,String> entry;
				entry = iter.next();

				out.write(entry.getKey());
				String codeword = entry.getValue();
				out.write( (byte) codeword.length() );

				for ( int i = 32 ; i > codeword.length() ; i-- ) {
					out.write(false);
				}
				for ( int i = 0 ; i < codeword.length() ; i++ ) {
					out.write( codeword.charAt(i) != '0' );
				}
			}
		}
		else if ( version == 3 ) {
			out.write( (byte)3 );
			out.write( (short)set.size() );
			out.write( filelen );

			Iterator<Map.Entry<Character,String>> iter;
			iter = set.iterator();

			while ( iter.hasNext() ) {
				Map.Entry<Character,String> entry;
				entry = iter.next();

				out.write(entry.getKey());
				String codeword = entry.getValue();

				for ( int i = 32 ; i > codeword.length()+1 ; i-- ) {
					out.write(false);
				}
				out.write(true);
				for ( int i = 0 ; i < codeword.length() ; i++ ) {
					out.write( codeword.charAt(i) != '0' );
				}
			}
		}
		else {
			// No such version supported
		}
	}

	/**
	 *
	 */
	public static void writeData(OutputBitStream out, Reader in, Map<Character,String> map) throws IOException
	{
		int r;
		String codeword;
		char ch;
		while((r = in.read())!=-1)
		{
			ch = (char) r;
			codeword = map.get(ch);

			if ( codeword == null ) {
				System.err.println("An unknown error occured while reading the file");
				System.exit(2);
			}

			for(int i = 0;i<codeword.length();i++)
			{
				if(codeword.charAt(i)=='1')
				{
					out.write(true);
				}
				else
				{
					out.write(false);
				}
			}
		}
	}

	/**
	 *
	 */
	public static Set<Map.Entry<Character,Integer>> computeFrequency(Reader in) throws IOException
	{
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		int r;
		char ch;
		Integer freq;
		while((r = in.read())!=-1)
		{
			ch = (char)r;
			freq = map.get(ch);
			if( freq != null )
			{
				map.put(ch, freq+1);
			}
			else
			{
				map.put(ch,1);
			}
		}
		return map.entrySet();
	}
}
