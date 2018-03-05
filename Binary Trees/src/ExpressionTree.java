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
		for (int i = 0; i<postfix.length; i++){
			ifOperator(postfix[i]){
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
		return finalRoot.toString(); //this isnt' right!
	}

	public String toInfixNotation(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = toInfixNotation(root.getLeft());
			process(root.getValue());
			traverseInorder(root.getRight());
		}
		return finalRoot;
	}

	public String toPostfixNotation(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			toPostfixNotation(root.getLeft());
			toPostfixNotation(root.getRight());
			process(root.getValue());
		}
		return finalRoot.toString(); //look at thsi again lol
	}

	public int postfixEval(String[] postfix) {
		Stack<Object> exp = new Stack<Object>();
		for (int i = 0; i<postfix.length; i++){
			ifOperator(postfix[i]){
				exp.push(postfix[i]);
			}
		}
	}

	public TreeNode traverseTree(TreeNode root) {
		// traverse tree and returns root
		TreeNode finalRoot = root;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			finalRoot = process(root.getValue());
			traversePreorder(root.getLeft());
			traversePreorder(root.getRight());
		}
		return finalRoot;
	}

	public boolean ifOperator(String s) {
		return (s.equals("+") || s.equals("*"));
	}

	// CODE FROM BOOK HERE FOR REFERENCE
	private void traversePreorder(TreeNode root) {
		TreeNode finalRoot;
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			process(root.getValue());
			traversePreorder(root.getLeft());
			traversePreorder(root.getRight());
		}

	}

	private void traversePostorder(TreeNode root) {
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			traversePostorder(root.getLeft());
			traversePostorder(root.getRight());
			process(root.getValue());
		}

	}

	private void traverseInorder(TreeNode root) {
		// Base case: root == null, the tree is empty, do nothing
		if (root != null) {
			traverseInorder(root.getLeft());
			process(root.getValue());
			traverseInorder(root.getRight());
		}

	}

	private TreeNode process(Object n) {
		// what the heck is this? check book boi
		return new TreeNode(n);
	}

}
