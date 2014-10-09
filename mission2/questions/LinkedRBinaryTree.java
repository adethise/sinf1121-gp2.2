package derivationTree;

import java.util.ArrayList;

public class LinkedRBinaryTree<E> implements RBinaryTree<E> {
	protected LinkedRPosition<E> root;
	protected RBinaryTree<E> left;
	protected RBinaryTree<E> right;
	protected int size;	
	//Constructor
	public LinkedRBinaryTree() {
		this.root = null;
		this.left = null;
		this.right = null;
		this.size = 0;
	}
	@Override
	public boolean isEmpty() {
		return (this.root()==null);		
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public RPosition<E> root() {
		return this.root;
	}

	@Override
	public boolean isLeaf() {
		return (this.leftTree() == null && this.rightTree() == null);
	}

	@Override
	public RBinaryTree<E> leftTree() {
		return this.left;
	}

	@Override
	public RBinaryTree<E> rightTree() {
		return this.right;
	}

	@Override
	public void setElement(E o) {
		this.root.element= o;

	}

	@Override
	public void setLeft(RBinaryTree<E> tree) {
		this.left = tree;

	}

	@Override
	public void setRight(RBinaryTree<E> tree) {
		this.right = tree;

	}

	@Override
	public Iterable<RPosition<E>> positions(){
		ArrayList<RPosition<E>> positions = new ArrayList<RPosition<E>>();
		addPositions(this, positions);
		return positions;
	}

	public void addPositions(RBinaryTree<E> tree, ArrayList<RPosition<E>> positions)
	{
		positions.add(tree.root());

		{
			addPositions(tree.leftTree(),positions);
		}

		if (tree.rightTree()!=null) 
		{
			addPositions(tree.rightTree(),positions);
		}

	}

}
