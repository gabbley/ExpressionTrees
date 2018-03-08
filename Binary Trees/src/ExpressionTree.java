import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

public class ExpressionTree {

	ExpressionTree() {
		super();
	}

	private TreeNode buildTree(String[] postfix) {

		Stack<TreeNode> exp = new Stack<TreeNode>();
		ExpressionTree tree = new ExpressionTree();
		for (int i = 0; i < postfix.length; i++) {
			if (!isOperator(postfix[i])) {
				exp.push((TreeNode) (postfix[i]));
			} else {
				TreeNode operator = new TreeNode(postfix[i]);
				operator.setRight(exp.pop());
				operator.setLeft(exp.pop());
			}

		}

		return exp.pop();
	}

	private int evalTree(TreeNode root) {
		TreeNode a = traverseTree(root);
		TreeNode b = traverseTree(root);
		return evalTree(a) * evalTree(b);
	}

	private String toPrefixNotation(TreeNode root) {
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

	private String toInfixNotation(TreeNode root) {
		// traverse tree and returns root
		String finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = toInfixNotation(root.getLeft());
			performOperation(root.getValue());
			toInfixNotation(root.getRight());
		}
		return finalRoot.toString(); // haha what
	}

	private String toPostfixNotation(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			toPostfixNotation(root.getLeft());
			toPostfixNotation(root.getRight());
			performOperation(root.getValue());
		}
		return finalRoot.toString(); // look at thsi again lol
	}

	private int postfixEval(String[] postfix) {
		Stack<Integer> exp = new Stack<Integer>();
		for (int i = 0; i < postfix.length; i++) {
			if (!isOperator(postfix[i])) {
				exp.push(postfix[i]);
			} else
				exp.push(performOperation(exp.pop(), exp.pop(), postfix[i]));
		}
		return exp.pop();
	}

	}

	private TreeNode traverseTree(TreeNode root) { //this needs help
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = performOperation(root.getValue());
			traverseTree(root.getLeft());
			traverseTree(root.getRight());
		}
		return finalRoot;
	}

	private boolean isOperator(String s) {
		return (s.equals("+") || s.equals("*"));
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
