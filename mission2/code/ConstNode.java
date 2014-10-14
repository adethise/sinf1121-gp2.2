public class ConstNode implements OperationNode
{
	private final double val;

	public ConstNode(double a)
	{
		this.val = a;
	}

	public double evaluate(double x)
	{
		return this.val;
	}

	public OperationNode derivate()
	{
		return new ConstNode(0);
	}

	public String toString()
	{
		return Double.toString(this.val);
	}
}
