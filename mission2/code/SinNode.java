
public class SinNode implements OperationNode{
	private OperationNode a; // operands

	public SinNode(OperationNode a)
	{
		this.a = a;
	}

	public double evaluate(double x)
	{
		return -Math.sin(a.evaluate(x));
	}

	public OperationNode derivate()
	{
		return new MulNode(a.derivate(),new CosNode(a));
	}

	public String toString()
	{
		return "(sin("+a.toString()+"))";
	}
}
