
public class MulNode implements OperationNode {
		private OperationNode a,b; // operands

		public MulNode(OperationNode a, OperationNode b)
		{
			this.a = a;
			this.b = b;
		}

		public double evaluate(double x)
		{
			return a.evaluate(x) * b.evaluate(x);
		}

		public OperationNode derivate()
		{
			return new AddNode(new MulNode(a.derivate(),b), new MulNode(a,b.derivate()));
		}

		public String toString()
		{
			return "("+a.toString()+"*"+b.toString()+")";
		}

}
