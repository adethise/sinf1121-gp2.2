public class VarNode implements OperationNode
{
	public VarNode()
	{}

	public double evaluate(double x)
	{
		return x;
	}

	public OperationNode derivate()
	{
		return new ConstNode(1);
	}

	public String toString()
	{
		return "x";
	}
}
