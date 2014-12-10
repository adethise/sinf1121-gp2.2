import java.util.*;
import java.io.*;

public class Graph
{
	private List<List<Edge>> main;
	private int nnodes;

	//Edge = arete
	//Vertex = Sommet
	public class Edge implements Comparable<Edge>
	{
		public int cost;
		public int origin, destination;

		public Edge(int cost, int origin, int destination)
		{
			this.cost = cost;
			this.origin = origin;
			this.destination = destination;
		}
		public int compareTo(Edge sommet)
		{
			
			return (this.cost-sommet.cost);
			
		}
		public String toString()
		{
			return (origin+"\t"+destination+"\t"+cost);
		}
	}
	public Graph(String filename)
	{
		String lines = null;
		int n = 0;
		try
		{
			BufferedReader buffs = new BufferedReader(new FileReader(filename));
			while ((lines = buffs.readLine())!=null)
			{
				n++;
			}
			buffs.close();
			String line = null;
			String [] tab =null;
		
			main = new ArrayList<List<Edge>>(n);
			for (int i = 0 ; i < n ; i++ ) {
				main.add(i, new LinkedList<Edge>());
			}
			this.nnodes = n;
		
			try
			{
				BufferedReader buff = new BufferedReader(new FileReader(filename));
				while ((line = buff.readLine())!=null)
				{
					tab = line.split("\t");
					Edge edge = new Edge( Integer.parseInt(tab[2]),Integer.parseInt(tab[0]),Integer.parseInt(tab[1]));
					main.get(edge.origin).add(edge);
					main.get(edge.destination).add(edge);
				}
			buff.close();
	

		}
		catch(IOException e)
		{
			e.printStackTrace(System.err);
		}

		}
		catch(IOException e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	public Graph(String filename, int n)
	{
		String line = null;
		String [] tab =null;
		
		main = new ArrayList<List<Edge>>(n);
		for (int i = 0 ; i < n ; i++ ) {
			main.add(i, new LinkedList<Edge>());
		}
		this.nnodes = n;
		
		try
		{
			BufferedReader buff = new BufferedReader(new FileReader(filename));
			while ((line = buff.readLine())!=null)
			{
				tab = line.split("\t");
				Edge edge = new Edge( Integer.parseInt(tab[2]),Integer.parseInt(tab[0]),Integer.parseInt(tab[1]));
				main.get(edge.origin).add(edge);
				main.get(edge.destination).add(edge);
			}
			buff.close();
	

		}
		catch(IOException e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	public List<Edge> getEdges(int number)
	{
		return main.get(number);
	}
	public int size()
	{
		return main.size();
	}
	
	public static void main(String[]args)
	{
		System.out.println("Kikooooooo :) :) :) :)");
	}
}

