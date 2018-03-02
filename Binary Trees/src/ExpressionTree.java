
public class ExpressionTree {

	ExpressionTree() {
		super();
	}

	public ExpressionTree buildTree(String[] postfix) {

		return new ExpressionTree();
	}

	public int evalTree(TreeNode root) {
		TreeNode a =  traverseTree(root);
		TreeNode b =  traverseTree(root);
		return evalTree(a) * evalTree(b)
	}

	public String toPrefixNotation(TreeNode root) {

	}

	public String toInfixNotation(TreeNode root) {

	}

	public String toPostfixNotation(TreeNode root) {

	}

	public int postfixEval(String[] postfix) {

	}

	public TreeNode traverseTree(TreeNode root) {
		// traverse tree and returns root
	}

}
