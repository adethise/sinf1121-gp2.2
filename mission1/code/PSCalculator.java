import java.util.HashMap;
import java.util.Map;


public class PSCalculator {

	private static DNodeStack PSStack;;
	private static Map<String, Float> def = new HashMap<String, Float>();
	private static String outString = new String();	
	private static int intLine = 1;
	/*
	 * @post: permet de récuperer la sortie de l'interpreteur
	 */
	public String getOutString() {
		return outString;
	}

	public PSCalculator() {
		PSStack = new DNodeStack();
	}
	/*
	 * @Pré : Prend un String à interpreter
	 * @Post: Interprete le language
	 */
	void feedStackFromLine(String lineToFeed){
		String[] tabElement = lineToFeed.split(" ");
		for( String element : tabElement)
		{
			try{
			if (interpretor(element) == -1)
				break;		
			}
			catch(EmptyStackException err)
			{
				PSStack = new DNodeStack();
				System.err.println("ERROR : not enough argument for the "+element+" instruction\n");
				outString += intLine++ +") ERROR : not enough argument for the "+element+" instruction\n";
				break;
			}
			catch(NumberFormatException err){
				PSStack = new DNodeStack();
				System.err.println("ERROR : not a float \n");
				outString += intLine++ +") ERROR : not a float \n";
				break;
				
			}
			
		}	
		return;
	}
	
	/*
	 * @pre : prend un élément à interpreter
	 * @post : l'envoie sur la bonne fonction
	 */
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

	/*
	 * @pre:un element à interpreter
	 * @post: renvoie true si l'élément est un float, false dans le cas contraire.
	 */
	private static boolean isFloat(String element)
	{

		try {
			Float.parseFloat(element);
			return true;
		}
		catch(NumberFormatException err){return false;} 

	}
	/*
	 * @post: récupere un float de la stack
	 */
	private static Float getFloatFromStack()
	{
		Float returnFloat;
			returnFloat = Float.parseFloat(PSStack.pop().toString());
		return 	returnFloat;	
	}
	/*
	 * @post: effectue l'opération d'addition de deux float contenu dans la stack et met la réponse sur la stack
	 */
	private static void add()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
			PSStack.push(temp1 + temp2);

	}
	/*
	 * @post: effectue l'opération de substraction de deux float contenu dans la stack et met la réponse sur la stack
	 */
	private static void sub()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
			PSStack.push(temp2 - temp1);

	}
	/*
	 * @post: effectue l'opération de multiplication de deux float contenu dans la stacket met la réponse sur la stack
	 */
	private static void mul()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
			PSStack.push(temp1 * temp2);

	}
	/*
	 * @post: effectue l'opération de division de deux float contenu dans la stack et met la réponse sur la stack
	 */
	private static void div()
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
			PSStack.push(temp2 / temp1);

	}
	/*
	 * @pre: prend un booléan pour différencier la fonction equal et not equal
	 * @post renvoie un booléan 
	 * 	si not est false : renvoie true si les éléments sont égaux
	 *  si not est true : renvoie true si les éléments sont différent
	 */
	private static void eq(boolean not)
	{
		Float temp1 = getFloatFromStack();
		Float temp2 = getFloatFromStack();
		if (temp1 == Float.MIN_VALUE || temp2 == Float.MIN_VALUE )
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
	/*
	 * @post : prend les 2 derniers élement de la stack et les interprete de façon à définir une variable
	 */
	private static void def()
	{
		Float fl = getFloatFromStack();
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

	/*
	 * @post: effectue l'opération de duplication du dernier élément contenu dans la stack 
	 * 
	 */ 
	private static void dup()
	{
			PSStack.push(PSStack.top());

	}
	/*
	 * @post: effectue l'opération d'interchanger les 2 derniers élément de la pile
	 */
	private static void exch()
	{
		Object temp1 = PSStack.pop();
		Object temp2 = PSStack.pop();
		PSStack.push(temp2);
		PSStack.push(temp1);
	}
	/*
	 * @post: enleve un élément de la stack
	 */
	private static void pop()
	{
			PSStack.pop();
	}
	/*
	 * @post : affiche/écrit le résultat
	 */
	private static void pstack()
	{
		outString += (intLine++) + ") "+PSStack.toString() + '\n';
	}
}
