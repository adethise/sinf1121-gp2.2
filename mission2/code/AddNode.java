public class AddNode implements OperationNode
{
	private OperationNode a,b; // operands

	public AddNode(OperationNode a, OperationNode b)
	{
		this.a = a;
		this.b = b;
	}

	public double evaluate(double x)
	{
		return a.evaluate(x) + b.evaluate(x);
	}

	public OperationNode derivate()
	{
		return new AddNode(a.derivate(), b.derivate());
	}

	public String toString()
	{
		return "("+a.toString()+"+"+b.toString()+")";
	}
}
