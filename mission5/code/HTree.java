import java.util.*;
/**
 *
 */
public class HTree
{
	private class Node implements Comparable<Node>
	{
		private Node left;
		private Node right;
		private boolean isLeaf;
		private char character;
		private int value;

		public Node(Node left, Node right, int value)
		{
			this.left = left;
			this.right = right;
			this.value = value;

			this.isLeaf = false;
		}

		public Node(char character, int value)
		{
			this.character = character;
			this.isLeaf = true;
			this.value = value;

			this.left = null;
			this.right = null;
		}

		public void createMap(String s, Map<Character,String> m)
		{
		}

		public int compareTo(Node n)
		{
			return Integer.valueOf(this.value).compareTo(Integer.valueOf(n.value));
		}

		public int getValue()
		{
			return value;
		}
	}

	private Node root;
	private int size;


	/**
	 *
	 */
	public HTree(Set<Map.Entry<Character,Integer>> set)
	{
		PriorityQueue<Node> queue;
		queue = new PriorityQueue<Node>(set.size());
		size = set.size();

		Iterator<Map.Entry<Character,Integer>> iter = set.iterator();
		
		while (iter.hasNext()) {
			Map.Entry<Character,Integer> entry = iter.next();
			queue.offer(new Node(entry.getKey(), entry.getValue()));
		}

		while (queue.size() > 1) {
			Node e1 = queue.poll();
			Node e2 = queue.poll();
			queue.offer(new Node(e1, e2, e1.getValue() + e2.getValue() ) );
		}

		root = queue.poll();
	}

	/**
	 *
	 */
	public Map<Character,String> makeMap()
	{
		Map<Character,String> map = new HashMap<Character,String>(this.size);

		this.root.createMap("", map);

		return map;
	}

	/**
	 *
	 */
	public static Map<Character,String> buildMap(Set<Map.Entry<Character,Integer>> set)
	{
		HTree t = new HTree(set);
		return t.makeMap();
	}
}
