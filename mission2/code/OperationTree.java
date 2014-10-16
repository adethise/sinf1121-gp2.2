import java.util.List;

public class OperationTree
{
	private OperationNode firstNode;

	public OperationTree(String s)
	{
		this.firstNode = parseEquation(s);
	}

	public double evaluate(double x)
	{
		return firstNode.evaluate(x);
	}

	public void derivate()
	{
		firstNode = firstNode.derivate();
	}

	public double derivate(double x)
	{
		return firstNode.derivate().evaluate(x);
	}

	public String toString()
	{
		return firstNode.toString();
	}


	public static void main(String[] args)
	{
		if (args.length == 0) {
			printHelp();
		}
		else if (args[0].equals("-h") || args[1].equals("--help")) {
			printHelp();
		}
		else if (args[0].equals("-e") && args.length == 3) {
			OperationTree myTree = new OperationTree(args[1]);
			System.out.println(myTree.evaluate(Double.parseDouble(args[2])));
		}
		else if (args[0].equals("-d") && args.length == 3) {
			OperationTree myTree = new OperationTree(args[1]);
			System.out.println(myTree.evaluate(Double.parseDouble(args[2])));
		}
		else if (args.length == 2) {
			List<String> equations = FileAccess.ReadFromFile(args[0]);
			double x = Double.parseDouble(args[1]);

			for (String s : equations) {
				OperationTree myTree = new OperationTree(s);
				System.out.println("f(x) = " + myTree);
				System.out.println("f("+x+") = " + myTree.evaluate(x));
				System.out.println("f'("+x+") = " + myTree.derivate(x));
			}
		}
		else {
			printHelp();
		}

	}

	public static void printHelp()
	{
		System.out.println("USAGE :");
		System.out.println("\t./OperationTree <file> <value>");
		System.out.println("\tFor each equation in <file>, print its value and derivate computed at x=<value>");
		System.out.println("\t./OperationTree -e <equation> <value>");
		System.out.println("\tPrint the value of <equation> at x=<value>");
		System.out.println("\t./OperationTree -d <equation> <value>");
		System.out.println("\tPrint the derivate of <equation> at x=<value>");
	}

	/*
	 * @pre s is a correct, fully parenthesed equation
	 * @post return the first OperationNode of the OperationTree corresponding to s
	 */
	protected static OperationNode parseEquation(String s) throws IllegalArgumentException
	{
		if ( s.charAt(0) == '(' ) {
			int end = findMatchingParenthesis(s, 0);
			if ( end+1 == s.length() ) {
				// assuming (...)
				return parseEquation(s.substring(1, end));
			}
			else {
				// assuming (...)'+'(...)
				OperationNode firstOp = parseEquation(s.substring(1,end));
				OperationNode secondOp = parseEquation(s.substring(end+2, s.length()));
				return parseEquation(firstOp, secondOp, s.charAt(end+1));
			}
		}
		else if ( s.substring(0, 4).equals("sin(") ) {
			int end = findMatchingParenthesis(s, 3);
			OperationNode firstOp = new SinNode(parseEquation(s.substring(4, end)));
			if ( end+1 == s.length() ) {
				// assuming sin(...)
				return firstOp;
			}
			else {
				// assuming sin(...)'+'(...)
				OperationNode secondOp = parseEquation(s.substring(end+2, s.length()));
				return parseEquation(firstOp, secondOp, s.charAt(end+1));
			}
		}
		else if ( s.substring(0, 4).equals("cos(") ) {
			int end = findMatchingParenthesis(s, 3);
			OperationNode firstOp = new CosNode(parseEquation(s.substring(4, end)));
			if ( end+1 == s.length() ) {
				// assuming cos(...)
				return firstOp;
			}
			else {
				// assuming cos(...)'+'(...)
				OperationNode secondOp = parseEquation(s.substring(end+2, s.length()));
					return parseEquation(firstOp, secondOp, s.charAt(end+1));
				}
	
			}
		else if ( Character.isDigit(s.charAt(0)) ) {
			int i;
			for (i = 0 ; i < s.length() ; i++) {
				if ( !( Character.isDigit(s.charAt(i)) || s.charAt(i) == '.') ) {
					break;
				}
			}
			// after this loop, i is either the index of the first non-digit character or s.length()
			if ( i == s.length() ) {
				// assuming s is a number
				return new ConstNode(Double.parseDouble(s));
			}
			else {
				// assuming a+(...) where a is a number
				OperationNode firstOp = new ConstNode(Double.parseDouble(s.substring(0,i)));
				OperationNode secondOp = parseEquation(s.substring(i+1, s.length()));
				return parseEquation(firstOp, secondOp, s.charAt(i));
			}
		}
		else {
			throw new IllegalArgumentException("Bad string");
			return null;
		}
	}

	/*
	 * @pre operator if a valid operator for the operands firstOp and secondOp
	 * @post return the OperationNode corresponding to operator with its operands
	 */
	protected static OperationNode parseEquation(OperationNode firstOp, OperationNode secondOp, char operator) throws IllegalArgumentException
	{
		switch(operator) {
		case '+':
			return new AddNode(firstOp, secondOp);
			break;
		case '-':
			return new SubNode(firstOp, secondOp);
			break;
		case '*':
			return new MulNode(firstOp, secondOp);
			break;
		case '/':
			return new DivNode(firstOp, secondOp);
			break;
		case '^':
			return new PowNode(firstOp, secondOp);
			break;
		default:
			throw new IllegalArgumentException("Bad string");
			return null;
		}
	}

	/*
	 * @pre s.charAt(index) == '(', a matching parenthesis exists in s
	 * @post index of the parenthesis matching s[index]
	 */
	protected static int findMatchingParenthesis(String s, int index) throws IllegalArgumentException
	{
		assert ( s.charAt(index) == '(' );
		int count = 1;
		for (int i = index+1 ; i < s.length() ; i++) {
			if ( s.charAt(i) == '(' ) {
				count++;
			}
			else if ( s.charAt(i) == ')' ) {
				count--;
			}
			if ( count == 0 ) {
				return i;
			}
		}

		// if we reach this point then no mathcing parenthesis has been found
		throw new IllegalArgumentException("Bad string");
	}
}
