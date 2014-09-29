public class DNode<E>
{
	// Instance variables:
	private E element;
	private DNode<E> next;

	/** Creates a node with null references to its element, next node and previous node */
	public DNode()
	{
		this(null, null);
	}

	/** Creates a node with the given element, next node and previous node */
	public DNode(E element, DNode<E> next)
	{
		this.element = element;
		this.next = next;
	}

	// Accessor methods:
	public E getElement()
	{
		return this.element; 
	}

	public DNode<E> getNext()
	{ 
		return next;
	}

	// Modifier methods:
	public void setElement(E newElem)
	{ 
		this.element = newElem; 
	}

	public void setNext(DNode<E> newNext)
	{
		this.next = newNext; 
	}
}
