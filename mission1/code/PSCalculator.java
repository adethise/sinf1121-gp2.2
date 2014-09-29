import java.util.HashMap;
import java.util.Map;


public class PSCalculator {

	private static DNodeStack<Object> PSStack = new DNodeStack<Object>();
	private static Map<String, Float> def = new HashMap<String, Float>();
	private static String outString = new String();	
	private static int intLine = 1;
	public String getOutString() {
		return outString;
	}

	public PSCalculator() {
	}

	void feedStackFromLine(String lineToFeed){
		String[] tabElement = lineToFeed.split(" ");
		for( String element : tabElement)
		{
			if (interpretor(element) == -1)
				return;			
		}		
	}
	private static int interpretor(String element)
	{	
		switch(element){
		case "":
			return 0;
		case "add" : 
			add();
			return 0;
		case "sub" : 
			sub();
			return 0;
		case "mul" : 
			mul();
			return 0;
		case "div" : 
			div();
			return 0;
		case "eq" : 
			eq(false);
			return 0;
		case "ne" : 
			eq(true);
			return 0;
		case "pstack" : 
			pstack();
			
			return 0;
		case "dup" : 
			dup();
			return 0;
		case "exch" : 
			exch();
			return 0;
		case "def" : 
			def();
			return 0;
		case "pop":
			pop();
			return 0;
		default :
			if(def.containsKey(element))
			{
				element = def.get(element).toString();
				PSStack.push(element);
				return 0;
			}
			else if (isFloat(element) || element.startsWith("/"))
			{
				PSStack.push(element);
				return 0;
			}
			else
			{
				outString += (intLine++) + ") Erreur de format PostScript \n";
				PSStack = new DNodeStack<Object>();
				System.err.println("Erreur dans le format des instructions Post-Script");
				return -1;
			}
		}
	}

	private static boolean isFloat(String element)
	{

		boolean toReturn = true;
		try{
			Float.parseFloat(element);
		}
		catch(NumberFormatException err)
		{
			toReturn = false;
		}
		return toReturn;

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
		outString += (intLine++) + ") "+PSStack.toString() + '\n';
	}
}
