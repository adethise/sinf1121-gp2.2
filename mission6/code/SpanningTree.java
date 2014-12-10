public class SpanningTree
{
	public static void main(String[] args)
	{
		assert (args.length == 2) "Wrong number of parameters";

		Graph g = new Graph(args[0]);

		PriorityQueue p = new PriorityQueue();

		List<Boolean> visited = new ArrayList<Boolean>(g.size());
		for ( int i = 0 ; i < g.size() ; i++ ) {
			visited.add(i, false);
		}

		List<Graph.Edge> solution = new LinkedList<Graph.Edge>();

		visited.add(0, true);
		for ( Graph.Edge e : g.getEdges(0) ) {
			p.offer(e);
		}

		Graph.Edge e;
		while ( (e = p.poll()) != null ) {
			if ( !visited[e.origin] ) {
				solution.add(e);
				visited[e.origin] = true;
				for ( Graph.edge edge : g.getEdges(e.origin) ) {
					p.offer(edge);
				}
			}
			else if ( !visited[e.destination] ) {
				solution.add(e);
				visited[e.destination] = true;
				for ( Graph.edge edge : g.getEdges(e.destination) ) {
					p.offer(edge);
				}
			}
		}


		for ( e : solution ) {
			System.out.println(e.toString());
		}
	}
}
