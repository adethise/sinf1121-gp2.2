import java.util.ArrayList;
import java.util.LinkedList;

public class Graph
{
	private List<List<Edge>> main;
	private nnodes;

	//Edge = arete
	//Vertex = Sommet
	public class Edge
	{
		private int cost;
		private int origin, destination;

		public Edge(int cost, int origin, int destination)
		{
			this.cost = cost;
			this.origin = origin;
			this.destination = destination;
		}
	}

	public Graph(String filename, int n)
	{
		main = new ArrayList(n);
		for (int i = 0 ; i < n ; i++ ) {
			main.add(i, new LinkedList());
		}
		this.nnodes = n;
	}
}
