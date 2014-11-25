/**
 *
 */
import java.io.*;
import java.util.*;
public class Compress
{
	/*
	 *
	 */
	public static void main(String[] args)
	{
		
	}

	/**
	 * Help
	 */
	public static void usage()
	{}

	/**
	 *
	 */
	public static void writeHeader(OutputBitStream out, Set<Map.Entry<Character,String>> set, int filelen, int version) throws IOException
	{
		
	
	}

	/**
	 *
	 */
	public static void writeData(OutputBitStream out, Reader in, Map<Character,String> map) throws IOException
	{
		int r;
		String codeword;
		boolean b;
		char ch;
		while((r = in.read())!=-1)
		{
			ch = (char) r;
			codeword = map.get(ch);
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
	public static Set<Map.Entry<Character,Integer>> computeFrequency(Reader in)
	{
		
	}

}
