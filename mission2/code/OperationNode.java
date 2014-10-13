/*
 * Interface for all nodes belonging to
 * an EquationTree.
 */
public interface OperationNode
{
	/*
	 * @pre -
	 * @post return the value of the node with
	 * all VarNode in the subtree evaluated equal to x
	 */
	public double evaluate(double x);

	/*
	 * @pre -
	 * @post return a tree representing the equation
	 * derivated in VarNode
	 */
	public OperationNode derivate();

	/*
	 * @pre -
	 * @post return the string evaluation of the subtree
	 */
	public String toString();
}
