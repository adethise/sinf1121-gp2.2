import java.util.HashMap;
import java.util.Set;
/**
 * Class UnionFind
 *
 * This class can be used to verify if a set of object has been unified.
 * UnionFind can be use with any type of hashable object.
 *
 * @version 1.0
 * @author Arnaud Dethise "arnauddethise@gmail.com"
 * 
 */
public class UnionFind<E>
{
	private class UnionNode
	{
		private E value;
		private UnionNode parent;

		public UnionNode()
		{
			this.parent = this;
		}

		public UnionNode find()
		{
			if ( this.parent == this )
				return this;
			else
				return this.parent.find();
		}

		public UnionNode getParent() { return this.parent; }

		public void setParent(UnionNode parent) { this.parent = parent; }
	}

	private HashMap<E, UnionNode> map;
	private int n; // the number of unions

	/**
	 * Constructor.
	 *
	 * Create a new UnionFind with not two nodes united.
	 *
	 * @param set -- The set of objects we want to unite. Can not be empty.
	 */
	public UnionFind(Set<E> set)
	{
		if ( set == null )
			throw new NullPointerException("parameter set is null");
		if ( set.size() == 0 )
			throw new IllegalArgumentException("set cannot be empty");

		map = new HashMap<E, UnionNode>(set.size());
		this.n = 0;
		for (E e : set) {
			map.put(e, new UnionNode());
			this.n++;
		}
	}

	/**
	 *
	 * Join two elements of the set in the same union.
	 *
	 * @param e1 -- The first element.
	 * @param e2 -- The second element we want to unite.
	 * @return true if the two elements have been united, false if they were already united.
	 * @throws InvalidArgumentException if one of the parameter was not in the initial set.
	 */
	public boolean unite(E e1, E e2)
	{
		UnionNode un1, un2;
		un1 = map.get(e1);
		un2 = map.get(e2);

		if ( un1 == null || un2 == null ) 
			throw new IllegalArgumentException("parameter is not in the set");

		if ( this.unite(un1, un2) ) {
			this.n--;
			return true;
		}
		else {
			return false;
		}
	}

	private boolean unite(UnionNode x, UnionNode y)
	{
		UnionNode xRoot, yRoot;
		xRoot = x.find();
		yRoot = y.find();

		if ( xRoot == yRoot ) {
			return false;
		}
		else {
			xRoot.setParent(yRoot);
			return true;
		}
	}

	/**
	 *
	 * Check if the set has been united, that is, if set.size()-1 succesfull unions have been performed.
	 *
	 * @return true if the set has been united, false otherwise.
	 */
	public boolean isUnited()
	{
		return this.n == 1;
	}
}
