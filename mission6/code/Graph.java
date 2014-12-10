import java.util.ArrayList;
import java.util.LinkedList;

public class Graph
{
	private List<List<Edge>> main;

	private class Edge
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
}
