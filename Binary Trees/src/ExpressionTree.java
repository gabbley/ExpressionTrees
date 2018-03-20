import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {

   final String MULTSIGN = "*";
   final String ADDSIGN = "+";

   
   ExpressionTree(String[] arr) {
      super(" ");
      TreeNode tree = this.buildTree(arr);
      this.setValue(tree);
      this.setRight(tree.getRight());
      this.setLeft(tree.getLeft());
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
         } 
         else {
            TreeNode operator = new TreeNode(postfix[i]);
            operator.setRight(exp.pop());
            operator.setLeft(exp.pop());
            exp.push(operator);
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
      if (root == null)
         return 0;
   
      String rootString = (root.getValue().toString());
      try{
    	  evalNum = Integer.parseInt(root.getValue().toString());
      }
      catch(NumberFormatException ex){
    	  evalNum = performOperation(evalTree(root.getLeft()), evalTree(root.getRight()), rootString);
      }
   
    
//      if (isOperator(rootString))
//         evalNum = performOperation(evalTree(root.getLeft()), evalTree(root.getRight()), rootString);
//      else
//         evalNum = Integer.parseInt(rootString);
   
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
         } 
         else if (!exp.isEmpty())
            exp.push(performOperation(exp.pop(), exp.pop(), postfix[i]));
         else
            throw new IllegalArgumentException();
      }
      int test = exp.pop();
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
   
   private int getOperand(String s){
	   return Integer.parseInt(s);
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

	/**
	 * Traverses through a tree and evaluates
	 * 
	 * @return int final value of the tree
	 */
	@Override
	public int evalTree() {
		return evalTree(this);
	}

	/**
	 * Converts ExpressionTree to prefix notation
	 * 
	 * @return String Tree in prefix notation
	 */
	@Override
	public String toPrefixNotation() {
		return toPrefixNotation(this);
	}

	/**
	 * Converts ExpressionTree to infix notation
	 * 
	 * @return String Tree in infix notation
	 */
	@Override
	public String toInfixNotation() {
		return toInfixNotation(this);
	}

	/**
	 * Converts ExpressionTree to postfix notation
	 * 
	 * @return String Tree in postfix notation
	 */
	@Override
	public String toPostfixNotation() {
		return toPostfixNotation(this);
	}

}
