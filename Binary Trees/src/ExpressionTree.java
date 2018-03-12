import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

public class ExpressionTree {

	final String MULTSIGN = "*";
	final String ADDSIGN = "+";

	ExpressionTree() {
		super();
	}

	/**
	 * Builds Expression tree from postfix expression
	 * 
	 * @param postfix
	 *            array of postfix expression
	 * 
	 * @return TreeNode built ExpressionTree
	 */
	public TreeNode buildTree(String[] postfix) {

		Stack<TreeNode> exp = new Stack<TreeNode>();
		for (int i = 0; i < postfix.length; i++) {
			if (!isOperator(postfix[i])) {
				exp.push(new TreeNode(postfix[i]));
			} else {
				TreeNode operator = new TreeNode(postfix[i]);
				operator.setRight(exp.pop());
				operator.setLeft(exp.pop());
			}
		}

		return exp.pop();
	}

	/**
	 * Traverses through a tree and evaluates
	 * 
	 * @param root
	 *            Tree to be evaluated
	 * 
	 * @return int final value of the tree
	 */
	public int evalTree(TreeNode root) {
		int evalNum = 0;
		while (root != null) {
			String rootString = root.getValue().toString();
			if (isOperator(rootString))
				evalNum = performOperation(evalTree(root.getLeft()), evalTree(root.getRight()), rootString);
			else
				evalNum = Integer.parseInt(rootString);
		}
		return evalNum;

	}

	/**
	 * Converts ExpressionTree to prefix notation
	 * 
	 * @param root
	 *            ExpressionTree to be converted
	 * 
	 * @return String Tree in prefix notation
	 */
	public String toPrefixNotation(TreeNode root) {
		String notation = "";
		if (root != null) {
			notation += root.getValue().toString();
			notation += toPrefixNotation(root.getLeft());
			notation += toPrefixNotation(root.getRight());
		}
		return notation;
	}

	/**
	 * Converts ExpressionTree to infix notation
	 * 
	 * @param root
	 *            ExpressionTree to be converted
	 * 
	 * @return String Tree in infix notation
	 */
	public String toInfixNotation(TreeNode root) {
		String notation = "";
		if (root != null) {
			TreeNode leftRoot = root.getLeft();
			if (leftRoot == null)
				notation += "(" + toInfixNotation(root.getLeft());
			else
				notation += toInfixNotation(root.getLeft());
			notation += root.getValue().toString();

			TreeNode rightRoot = root.getRight();
			if (rightRoot == null)
				notation += toInfixNotation(root.getRight()) + ")";
			else
				notation += toInfixNotation(root.getRight());
		}
		return notation;
	}

	/**
	 * Converts ExpressionTree to postfix notation
	 * 
	 * @param root
	 *            ExpressionTree to be converted
	 * 
	 * @return String Tree in postfix notation
	 */
	public String toPostfixNotation(TreeNode root) {
		String notation = "";
		if (root != null) {
			notation += toPostfixNotation(root.getLeft());
			notation += toPostfixNotation(root.getRight());
			notation += root.getValue().toString();
		}
		return notation;
	}

	/**
	 * Evaluates a postfix expression
	 * 
	 * @param postfix
	 *            array of postfix expression
	 * 
	 * @return int final value of postfix expression
	 */
	public int postfixEval(String[] postfix) {
		Stack<Integer> exp = new Stack<Integer>();
		for (int i = 0; i < postfix.length; i++) {
			if (!isOperator(postfix[i])) {
				exp.push(Integer.parseInt(postfix[i]));
			} else if (!exp.isEmpty())
				exp.push(performOperation(exp.pop(), exp.pop(), postfix[i]));
			else
				throw new IllegalArgumentException();
		}
		int test = exp.pop();
		System.out.println(test);
		return test;
	}

	/**
	 * Determines if String sent in is an operator
	 * 
	 * @param s
	 *            String, integer or an operator (+ or * only)
	 * 
	 * @return boolean true or false if an operator
	 */
	private boolean isOperator(String s) {
		return (s.equals(ADDSIGN) || s.equals(MULTSIGN));
	}

	/**
	 * Performs specified operation
	 * 
	 * @param a
	 *            first value to be added or multiplied
	 * 
	 * @param b
	 *            second value to be added or multiplied
	 * 
	 * @operator operator to specify operation
	 * 
	 * @return int value of operation performed
	 */
	private int performOperation(int a, int b, String operator) {
		switch (operator) {
		case MULTSIGN:
			return a * b;
		case ADDSIGN:
			return a + b;
		default:
			throw new IllegalArgumentException();
		}

	}

}
