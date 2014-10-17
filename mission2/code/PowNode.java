/*
 * This is the node for the power function.
 * Derivation will not work if b is not a constant.
 */
public class PowNode implements OperationNode {

	private OperationNode a,b; // operands

	public PowNode(OperationNode a, OperationNode b)
	{
		this.a = a;
		this.b = b;
	}

	public double evaluate(double x)
	{
		return Math.pow(a.evaluate(x),b.evaluate(x));
	}

	public OperationNode derivate()
	{
		return new MulNode(a.derivate(), new MulNode(b, new PowNode(a,new SubNode(b,new ConstNode(1)))));
	}

	public String toString()
	{
		return "("+a.toString()+"^"+b.toString()+")";
	}
}
