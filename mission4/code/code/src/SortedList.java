import java.util.ArrayList;

@SuppressWarnings("serial")
public class SortedList<E> extends ArrayList<E>
{
	public boolean add(E e)
	{
		if(e instanceof Publication)
		{
			
			if(super.size()==0)
			{
				super.add(e);
			}
			else if(super.size()==1)
			{
				if(((Publication) e).compareTo((Publication) super.get(0))>0)
				{
					super.add(1,e);
				}
				else if(((Publication)e).compareTo(((Publication) super.get(0)))==0)
				{
					
				}
				else
				{
					int count =0;
					while(count<super.size())
					{
						if(((Publication) e).compareTo((Publication) super.get(count))==0)
						{
							count = super.size()+2;
						}
						else if(((Publication) e).compareTo((Publication) super.get(count))>0)
						{
							super.add(count, e);
							count = super.size()+2;
						}
						else
						{
							count++;
						}
					}
				}
			}
			return true;
		}
		else
		{
			return false;
			
		}
	}
}
