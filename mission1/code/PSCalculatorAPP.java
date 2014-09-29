import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PSCalculatorAPP {

	public static void main(String[] args) {		
		
		DNodeStack<Object>  stack = new DNodeStack<Object>();
		List<String> file = new ArrayList<String>(); 
		file = FileAccess.ReadFromFile("ps.txt");
		Map<String, Float> def = new HashMap<String, Float>();
		for (String x : file)
		{	System.out.println(x);
			String[] element = x.split(" ");
			for (String e : element)
			{
				Float temp;
				try
				{
					temp = Float.parseFloat(e);
					stack.push(temp);
				}
				catch(NumberFormatException err){
					switch(e){
					case "mul":
						stack.push((Float)stack.pop()*(Float)stack.pop());
						break;
					case "add":
						stack.push((Float)stack.pop()+(Float)stack.pop());
						break;
					case "div":
						stack.push((Float)stack.pop()/(Float)stack.pop());
						break;
					case "sub":
						stack.push((Float)stack.pop()-(Float)stack.pop());
						break;
					case "pstack":
						System.out.println(stack.toString());
						break;
					case "pop":
						stack.pop();
						break;
					case "dup":
						stack.push(stack.top());
						break;
					case "exch":
						Float temp1,temp2;
						temp1 = (Float)stack.pop();
						temp2 = (Float)stack.pop();
						stack.push(temp1);
						stack.push(temp2);
						break;
					case "eq":
						if (((Float)stack.pop()).equals((Float)stack.pop()))
							stack.push("true");
						else
							stack.push("false");
						break;
					case "neq":
						if (!((Float)stack.pop()).equals((Float)stack.pop()))
							stack.push("true");
						else
							stack.push("false");
						break;
					case "def":						
						break;
					default: //si def, les deux précédents sont des strings. Donc on doit les ajouter à la stack.
						stack.push(e);		
						break;
					} 
				}
			}
		    
		}
	}

	/*
	 * Set or update the value in map mapped with key s.
	 */
	protected static void def(float n, String s, Map<String, Float> map)
	{
		map.put(s, n);
	}

	/*
	 * If o is an alias to a value mapped in map, return said value.
	 * Else, o should be a float and this function will return its value.
	 *
	 * Throw an exception if o isn't a String and can't be casted to a float.
	 * Return NULL if there is no key o in map.
	 */
	protected static float getValue(Object o, Map<String, Float> map)
	{
		if (o instanceof String) {
			return map.get(o);
		}
		else {
			return (Float) o;
		}
	}

}
