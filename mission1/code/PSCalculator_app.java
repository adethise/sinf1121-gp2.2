import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PSCalculator_app {

	public static void main(String[] args) {		
		
		DNodeStack<Object>  stack = new DNodeStack<Object>();
		List<String> file = new ArrayList<String>(); 
		file = FileAcces.ReadFromFile("ps.txt");
		Map<String, Integer> def = new HashMap<String, Integer>();
		
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

}
