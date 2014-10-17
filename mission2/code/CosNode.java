
public class CosNode implements OperationNode {
	private OperationNode a; // operands

	public CosNode(OperationNode a)
	{
		this.a = a;
	}

	public double evaluate(double x)
	{
		return -Math.cos(a.evaluate(x));
	}

	public OperationNode derivate()
	{
		return new SubNode(new ConstNode(0),new MulNode(a.derivate(),new SinNode(a.derivate())));
	}

	public String toString()
	{
		return "(cos("+a.toString()+"))";
	}
}
