
public class DivNode implements OperationNode{
	private OperationNode a,b; // operands

	public DivNode(OperationNode a, OperationNode b)
	{
		this.a = a;
		this.b = b;
	}

	public double evaluate(double x)
	{
		return a.evaluate(x) / b.evaluate(x);
	}

	public OperationNode derivate()
	{
		return new DivNode(new SubNode(new MulNode(a.derivate(),b),new MulNode(a,b.derivate())), new MulNode(b,b));
	}

	public String toString()
	{
		return "("+a.toString()+"/"+b.toString()+")";
	}
}
