import java.util.ArrayList;

/*
 * Objet permettant de stocker sous forme de liste chainee un ensemble de publications, ordonnées par ordre alphabétique
 */
public class SortedList
{
	public class Node
	{
		public Node next;
		public Publication data;
		public Node(Publication data)
		{
			this.data=data;
			this.next = null;
		}
	
	}
	private Node first;
	private int size;
	public SortedList()
	{
		this.size = 0;
		this.first = null;
	}
	public int size()
	{
		return this.size;
	}
	public Publication get(int index)
	{
		if(index < size)
		{
			Node current = first;
			for(int i=0;i<index;i++)
			{
				if(i==index)
				{
					return current.data;
				}
				else
				{
					current = current.next;
				}
			}
		}
		else
		{
			return null;
		}
		return null;
	}
	public void add(Publication pub)
	{
		if(pub==null)
		{
			System.out.println("Erreur, publication null");
			return;
		}
		
		if(size==0)
		{
			Node newnode = new Node(pub);
			first = newnode;
			size++;
		}
		else if(first!=null && pub.compareTo(first.data)<0)
		{
			Node newnode = new Node(pub);
			newnode.next = first;
			first = newnode;
			size++;
		}
		else
		{
			Node newnode = new Node(pub);
			Node current = first;
			while(current.next!=null && pub.compareTo(current.next.data)>0)
			{
				current = current.next;
			}
			if(current.next ==null)
			{
				current.next = newnode;
			}
			else if(pub.compareTo(current.next.data)!=0)
			{
				newnode.next = current.next;
				current.next = newnode;
			}
			size++;
		}
	}
	public ArrayList<Publication> getall()
	{
		ArrayList<Publication> liste = new ArrayList<Publication>();
		Node current = first;
		while(current!=null)
		{
			liste.add(current.data);
			current = current.next;
		}
		return liste;
	}
	public void printall()
	{
		Node current = first;
		for(int i=0;i<size;i++)
		{
			System.out.println(current.data.getName());
			current = current.next;
		}
	}

}
