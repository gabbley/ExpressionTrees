import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionsTester {
	
	public static String filename;


	/**
	 * Opens file for reading
	 * 
	 * @param filename
	 * 	name of file
	 * 
	 * @return Scanner
	 * 	file to be read as Scanner Object
	 */
	public static Scanner openFile(String filename) {

		File f = new File(filename);
		Scanner input = null;
		try {
			input = new Scanner(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		return input;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner kb = new Scanner(System.in);
		
		if (args.length < 1) {
			filename = "postFixExpressions.txt";
			if (openFile(filename) == null){
				System.out.println("Please enter file name");
				filename = kb.next();
				
			}

			kb.close();
		}
		else{
			System.out.println("File Found");
			filename = args[0];
		}

		Scanner in = openFile(filename);
		if (in == null)
			System.exit(1);
		else{
			//System.out.println(in.nextLine());
			while (in.hasNextLine()){
				System.out.println("Testing");
				testTrees(in, new ExpressionTree());
			}
		}
			
			//System.out.println("haha");
	}
	

	/**
	 * Allows writing to file
	 * 
	 * @param filename
	 * 	name of file
	 * 
	 * @throws FileNotFoundException
	 * 	if file not found
	 * 
	 * @return PrintWriter
	 * 	Object able to write to given file
	 */
	public static PrintWriter writeToFile(String filename) throws FileNotFoundException {

		File f = new File(filename);
		PrintWriter output = null;
		try {
			output = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			PrintWriter out = new PrintWriter(filename);
			out.println("Unable to Open File");
			return null;
		}
		return output;
	}
	
	/**
	 * Tests ExpressionTree methods
	 * 
	 * @param in
	 * 	Scanner to write to file
	 * 
	 * @param test
	 * 	ExpressionTree to be tested
	 * 
	 * @throws FileNotFoundException
	 * 	if file not found
	 */
	public static void testTrees(Scanner in, ExpressionTree test) throws FileNotFoundException{
		PrintWriter out;

		try {
			out = writeToFile(filename);
		} catch (FileNotFoundException e) {
			out = new PrintWriter(new File(filename));
			e.printStackTrace();
		}
		
		String[] arr = in.nextLine().split(" ");
		TreeNode tree = test.buildTree(arr);
		out.print("evalTree: " + test.evalTree(tree));
		out.print("toInfixNotation: " + test.toInfixNotation(tree));
		out.print("toPostfixNotation: " + test.toPostfixNotation(tree));
		out.print("toPrefixNotation: " + test.toPrefixNotation(tree));
		out.print("postfixEval: " + test.postfixEval(test.toPostfixNotation(tree).split(" ")));
	
		out.close();
	}
}
