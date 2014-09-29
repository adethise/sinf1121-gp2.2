public class DNode<E>
{
	// Instance variables:
	private E element;
	private DNode<E> next;
	//private DNode<E> prev;

	/** Creates a node with null references to its element, next node and previous node */
	public DNode()
	{
		this(null, null);
	}

	/** Creates a node with the given element, next node and previous node */
	public DNode(E e, DNode<E> n)
	{
		element = e;
		next = n;
	}

	// Accessor methods:
	public E getElement()
	{
		return element; 
	}

	public DNode<E> getNext()
	{ 
		return next;
	}

	// Modifier methods:
	public void setElement(E newElem)
	{ 
		element = newElem; 
	}

	public void setNext(DNode<E> newNext)
	{
		next = newNext; 
	}
}
