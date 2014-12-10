import java.util.*;
import java.io.*;

public class Graph
{
	private List<List<Edge>> main;
	private int nnodes;

	//Edge = arete
	//Vertex = Sommet
	public class Edge
	{
		public int cost;
		public int origin, destination;

		public Edge(int cost, int origin, int destination)
		{
			this.cost = cost;
			this.origin = origin;
			this.destination = destination;
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
	

		}
		catch(IOException e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	public List<Edge> getNode(int number)
	{
		return main.get(number);
	}
	
	
	public static void main(String[]args)
	{
		System.out.println("Kikooooooo :) :) :) :)");
	}
}

