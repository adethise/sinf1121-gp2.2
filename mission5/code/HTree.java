import java.util.*;
/**
 * This class build a Huffman Tree from an Entry Set.
 * @author Arnaud Dethise <arnaud.dethise@student.uclouvain.be>
 * @version 1.0
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

		public void createMap(String s, Map<Character,String> map)
		{
			if ( this.isLeaf ) {
				map.put(this.character, s);
			}
			else {
				this.left.createMap(s + "0", map);
				this.right.createMap(s + "1", map);
			}
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

		this.root = queue.poll();
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

	/**
	 *
	 */
	public static void main(String[] args)
	{
		Map<Character,Integer> testMapIn;
		testMapIn = new HashMap<Character,Integer>();
		testMapIn.put('a', 18);
		testMapIn.put('z', 42);
		testMapIn.put('e', 2);
		testMapIn.put('r', 8);
		testMapIn.put('t', 12);
		testMapIn.put('y', 12);

		Map<Character,String> testMapOut;

		testMapOut = buildMap(testMapIn.entrySet());

		System.out.println(testMapOut.get('a'));
		System.out.println(testMapOut.get('z'));
		System.out.println(testMapOut.get('e'));
		System.out.println(testMapOut.get('r'));
		System.out.println(testMapOut.get('t'));
		System.out.println(testMapOut.get('y'));
	}
}
