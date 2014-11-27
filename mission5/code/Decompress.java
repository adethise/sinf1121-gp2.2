import java.io.*;
import java.util.*;

public class Decompress {
/*
 * Utilise un InputBitStream -> cree une MAP
 * Ecrit avec un FileWriter
 * 
 * File struct v1.4b

 * | byte (8b) -> 3 si version 1.3
 * | short (16b) -> number of characters in dictionary (->n)
 * | long (64b) -> number of characters in file
 *   n*| char (16b) -> character 
 *     | int (32b) -> codeword, left-padded with 00..01
 * | (variable length, binary) -> compressed data 
 *
 */    
   public static void main(String[] args)
   {
	   if (args.length < 2)
	   {
		   usage();
		   System.exit(0);
	   }   
	  
	   String compressFile = args[0];
	   String filename = args[1];
	   
	   InputBitStream in;
	   FileWriter fw;
	   try
	   {
		   in = new InputBitStream(compressFile);
		   fw = new FileWriter(filename);
		   byte version = in.readByte();
		   short numChar = 0;
		   long fileSize = 0;
		   if(version == 3) 
		   {
			   numChar = (short) in.readShort();
			   fileSize = in.readLong();
		   }
		   
		   HashMap<String, Character> map;
		   map = readHeader(in, numChar);
		   translateData(in, map, fileSize, fw);
		   in.close();
		   fw.close();
	   }
	   catch(IOException e) 
	   {
			e.printStackTrace(System.err);
			System.exit(1);
	   }
   }
   
   public static void usage()
   {
	   
   }
   
   public static HashMap<String, Character> readHeader(InputBitStream in, short numChar) throws IOException
   {
	   HashMap<String, Character> map = new HashMap<String, Character>();
	   char c;
	   for(int i = 0; i < numChar; i++)
	   {
		   c = in.readChar();
		   StringBuffer sb = new StringBuffer();
		   boolean padding = true;
		   for (int j=0; j<32; j++)
		   {
			   if (padding) 
			   {
				   if (in.readBoolean())
				   {
					   padding = false;
				   }
			   }
			   else {
				   sb.append(in.readBoolean() ? '1' : '0');
			   }
		   }
		   map.put(sb.toString(), c);
	   }
	   return map;
   }
   
   public static void translateData(InputBitStream in, HashMap<String,Character> map, long fileSize, FileWriter fw) throws IOException
   {	
	  StringBuffer sb = new StringBuffer();
	  for(int i=0; i<fileSize; i++)
	  {
		  sb.append(in.readBoolean() ? '1' : '0');
		  if(map.containsKey(sb.toString()))
		  {
			 fw.write(map.get(sb.toString()));
			 sb = null;
			 sb = new StringBuffer();
			 //sb.delete(0, sb.length()-1);
		  }
	  }
   }
}
