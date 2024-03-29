import java.util.HashMap;
import java.util.Map;


public class PSCalculator_test {

	protected static DNodeStack<Object> PSStack = new DNodeStack<Object>();
	protected static Map<String, Float> def = new HashMap<String, Float>();
	protected static String outString = new String();	
	protected static int intLine = 1;
	public String getOutString() {
		return outString;
	}

	public PSCalculator_test() {
	}

	void feedStackFromLine(String lineToFeed){
		String[] tabElement = lineToFeed.split(" ");
		for( String element : tabElement)
		{
			if (interpretor(element) == -1)
				return;			
		}		
	}
	protected static int interpretor(String element)
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

	protected static boolean isFloat(String element)
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
	protected static Float getFloatFromStack()
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
	protected static void add()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 + temp2);

	}
	protected static void sub()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 - temp2);

	}
	protected static void mul()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 * temp2);

	}
	protected static void div()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MAX_VALUE)
			return;
		else 
			PSStack.push(temp1 / temp2);

	}
	protected static void eq(boolean not)
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
	protected static void def()
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


	protected static void dup()
	{
		Object temp = PSStack.top();
		PSStack.push(temp);

	}
	protected static void exch()
	{
		Object temp1 = PSStack.pop();
		Object temp2 = PSStack.pop();
		PSStack.push(temp2);
		PSStack.push(temp1);
	}
	protected static void pop()
	{
		PSStack.pop();
	}
	protected static void pstack()
	{
		outString += (intLine++) + ") "+PSStack.toString() + '\n';
	}
}
