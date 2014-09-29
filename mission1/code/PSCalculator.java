import java.util.HashMap;
import java.util.Map;


public class PSCalculator {

	private static DNodeStack<Object> PSStack = new DNodeStack<Object>();
	private static Map<String, Float> def = new HashMap<String, Float>();
	private static String outString = new String();	

	public String getOutString() {
		return outString;
	}

	public PSCalculator() {
	}

	void feedStackFromLine(String lineToFeed){
		System.out.println(lineToFeed);
		String[] tabElement = lineToFeed.split(" ");
		for( String element : tabElement)
		{
			interpretor(element);
		}		
	}
	private static void interpretor(String element)
	{	
		switch(element){
		case "add" : 
			add();
			break;
		case "sub" : 
			sub();
			break;
		case "mul" : 
			mul();
			break;
		case "div" : 
			div();
			break;
		case "eq" : 
			eq(false);
			break;
		case "ne" : 
			eq(true);
			break;
		case "pstack" : 
			pstack();
			break;
		case "dup" : 
			dup();
			break;
		case "exch" : 
			exch();
			break;
		case "def" : 
			def();
			break;
		case "pop":
			pop();
			break;
		default :
			if(def.containsKey(element))
			{
			element = def.get(element).toString();
			}
			PSStack.push(element);
		}
	}
	private static Float getFloatFromStack()
	{
		Float returnFloat;
		try
		{
			returnFloat = Float.parseFloat(PSStack.pop().toString());
		}
		catch (NumberFormatException | NullPointerException e2) {
			System.err.println("La valeur du def n'est pas dans le bon format ou n'a pas été spécifiée.");
			outString += "error of conversion \n";
			returnFloat = Float.MIN_VALUE;
		}
		return 	returnFloat;	
	}
	private static void add()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 + temp2);

	}
	private static void sub()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 - temp2);

	}
	private static void mul()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 * temp2);

	}
	private static void div()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 / temp2);

	}
	private static void eq(boolean not)
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		boolean tempResult ;
		if (temp1.equals(temp2))
		{
			tempResult = true;
		}
		else
		{
			tempResult = false;
		}		

		if (not)
		{
			tempResult = ! tempResult;	
		}
		PSStack.push(tempResult);
	}
	private static void def()
	{

		Float fl = getFloatFromStack();
		if( fl == Float.MIN_VALUE)
		{
			return;
		}
		String d = PSStack.pop().toString();
		if (d.startsWith("/")){
			String tabD[] = d.split("/");
			d= tabD[1];
		}
		else{
			System.err.println("L'identifiant pour le def a été mal défini.");
		}
		def.put(d, fl);
	}


	private static void dup()
	{
		Object temp = PSStack.top();
		PSStack.push(temp);

	}
	private static void exch()
	{
		Object temp1 = PSStack.pop();
		Object temp2 = PSStack.pop();
		PSStack.push(temp2);
		PSStack.push(temp1);
	}
	private static void pop()
	{
		PSStack.pop();
	}
	private static void pstack()
	{
		outString += PSStack.toString() + '\n';
	}
}
