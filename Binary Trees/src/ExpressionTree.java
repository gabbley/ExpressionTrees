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

	public String toPrefixNotation(TreeNode root) {
		String notation = "";
		if (root != null) {
			notation += root.getValue().toString();
			notation += toPrefixNotation(root.getLeft());
			notation += toPrefixNotation(root.getRight());
		}
		return notation;
	}

	public String toInfixNotation(TreeNode root) {
		String notation = "";
		if (root != null) {
			notation += toInfixNotation(root.getLeft());
			notation += root.getValue().toString();
			notation += toInfixNotation(root.getRight());
		}
		return notation;
	}

	public String toPostfixNotation(TreeNode root) {
		String notation = "";
		if (root != null) {
			notation += toPostfixNotation(root.getLeft());
			notation += toPostfixNotation(root.getRight());
			notation += root.getValue().toString();
		}
		return notation;
	}

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

	private boolean isOperator(String s) {
		return (s.equals(ADDSIGN) || s.equals(MULTSIGN));
	}

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
