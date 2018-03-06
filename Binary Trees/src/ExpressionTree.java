import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

public class ExpressionTree {

	ExpressionTree() {
		super();
	}

	public ExpressionTree buildTree(String[] postfix) {

		Stack<Object> exp = new Stack<Object>();
		for (int i = 0; i < postfix.length; i++) {
			if (isOperator(postfix[i])) {
				exp.push(postfix[i]);
			}
		}

		return new ExpressionTree();
	}

	public int evalTree(TreeNode root) {
		TreeNode a = traverseTree(root);
		TreeNode b = traverseTree(root);
		return evalTree(a) * evalTree(b);

	}

	public String toPrefixNotation(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = process(root.getValue());
			toPrefixNotation(root.getLeft());
			toPrefixNotation(root.getRight());
		}
		return finalRoot.toString(); // this isnt' right!
	}

	public String toInfixNotation(TreeNode root) {
		// traverse tree and returns root
		String finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = toInfixNotation(root.getLeft());
			process(root.getValue());
			toInfixNotation(root.getRight());
		}
		return finalRoot.toString(); // haha what
	}

	public String toPostfixNotation(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			toPostfixNotation(root.getLeft());
			toPostfixNotation(root.getRight());
			process(root);
		}
		return finalRoot.toString(); // look at thsi again lol
	}

	public int postfixEval(String[] postfix) {
		Stack<String> exp = new Stack<String>();
		for (int i = 0; i < postfix.length; i++) {
			if (isOperator(postfix[i])) {
				exp.push(postfix[i]);
			} else
				exp.pop();
			//call performOperation at some point
		}
	}

	public TreeNode traverseTree(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = process(root.getValue());
			traverseTree(root.getLeft());
			traverseTree(root.getRight());
		}
		return finalRoot;
	}

	public boolean isOperator(String s) {
		return (s.equals("+") || s.equals("*"));
	}

	private TreeNode process(Object n) {
		// what the heck is this? check book boi
		return new TreeNode(n);
	}

	private int performOperation(int a, int b, String operator) {
		switch (operator) {
		case "*":
			return a * b;
		case "+":
			return a + b;
		}

	}

}
