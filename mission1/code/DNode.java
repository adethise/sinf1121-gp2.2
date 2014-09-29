public class DNode<E>
{
	// Instance variables:
	private E element;
	private DNode<E> next;
	private DNode<E> prev;

	/** Creates a node with null references to its element, next node and previous node */
	public DNode()
	{
		this(null, null, null);
	}

	/** Creates a node with the given element, next node and previous node */
	public DNode(E e, DNode<E> n, DNode<E> p)
	{
		element = e;
		next = n;
		prev = p;
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
	
	public DNode<E> getPrev()
	{
		return prev;
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

	public void setPrev(DNode<E> prev)
	{
		this.prev = prev;
	}
}
