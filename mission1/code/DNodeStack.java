public class DNodeStack<E> implements Stack<E>
{
	protected DNode<E> top;
	protected int size;
	
	// Contruction d'une pile vide
	public DNodeStack()
	{
		top = null;
		size = 0;
	}

	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		if (top == null) return true;
		return false;
	}
	
	// Rajoute un element dans la pile
	public void push(E elem)
	{
		DNode<E> v = new DNode<E>(elem, top, null);
		top = v;
		v.getNext().setPrev(v);
		size++;
	}
	
	// Renvoie le premiere element de la pile
	public E top() throws EmptyStackException
	{
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
		return top.getElement();
	}

	// Enleve le premier element de la pile 
	public E pop() throws EmptyStackException
	{
		if (isEmpty()) throw new EmptyStackException("Stack is empty.");
		E temp = top.getElement();
		top = top.getNext();
		top.setPrev(null);
		size--;
		return temp;
	}
	
	// Affiche le pile sous forme de string
	public String toString()
	{
		String out = "[";
		DNode<E> v = top;
		if(v == null) return (out + "]");
		while(v.getNext() != null)
		{
			out += (v.getElement().toString() + ",");
			v = v.getNext();
		}
		out += (v.getElement().toString() + "]");
		return out;
	}
}