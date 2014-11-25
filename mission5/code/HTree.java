import java.util.*;
/**
 *
 */
public class HTree
{

	private PriorityQueue queue;

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

		public void createMap(String s, Map m)
		{}

		public int compareTo(Node n)
		{
			return Integer.valueOf(this.value).compareTo(Integer.valueOf(n.value));
		}
	}

	/**
	 *
	 */
	public HTree(Set<Map.Entry<Character,Integer>> set)
	{
		queue = new PriorityQueue<Node>(set.size());

		Iterator<Map.Entry<Character,Integer>> iter = set.iterator();
		
		while (iter.hasNext()) {
			Map.Entry<Character,Integer> entry = iter.next();
			queue.add(new Node(entry.getKey(), entry.getValue()));
		}

	}

	/**
	 *
	 */
	public Map<Character,String> makeMap()
	{
		return null;
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
