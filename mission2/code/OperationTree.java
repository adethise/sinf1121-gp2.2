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
		if (args.length <= 1) {
			printHelp();
		}
		else if (args[1].equals("-h") || args[1].equals("--help")) {
			printHelp();
		}
		else if (args[1].equals("-e") && args.length == 4) {
			OperationTree myTree = new OperationTree(args[2]);
			System.out.println(myTree.evaluate(Double.parseDouble(args[3])));
		}
		else if (args[1].equals("-d") && args.length == 4) {
			OperationTree myTree = new OperationTree(args[2]);
			System.out.println(myTree.evaluate(Double.parseDouble(args[3])));
		}
		else if (args.length == 3) {
			List<String> equations = FileAccess.ReadFromFile(args[1]);
			double x = Double.parseDouble(args[2]);

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

	protected static OperationNode parseEquation(String s) {
		return null;
	}
}
