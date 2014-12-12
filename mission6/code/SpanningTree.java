import java.util.*;
public class SpanningTree
{
	public static void main(String[] args)
	{
		assert (args.length == 1);

		Graph g = new Graph(args[0]);

		PriorityQueue<Graph.Edge> p = new PriorityQueue<Graph.Edge>();

		boolean[] visited = new boolean[g.size()];
		for (int i = 0 ; i < g.size() ; i++)
		{
			visited[i] = false;
		}

		List<Graph.Edge> solution = new LinkedList<Graph.Edge>();

		visited[0] = true;
		for (Graph.Edge e : g.getEdges(0))
		{
			p.offer(e);
		}

		Graph.Edge e;
		while ((e = p.poll()) != null)
		{
			if (!visited[e.origin])
			{
				solution.add(e);
				visited[e.origin] = true;
				for (Graph.Edge edge : g.getEdges(e.origin))
				{
					p.offer(edge);
				}
			}
			else if (!visited[e.destination])
			{
				solution.add(e);
				visited[e.destination] = true;
				for (Graph.Edge edge : g.getEdges(e.destination))
				{
					p.offer(edge);
				}
			}
		}

		for (Graph.Edge edge : solution)
		{
			System.out.println(edge.toString());
		}
	}
}
