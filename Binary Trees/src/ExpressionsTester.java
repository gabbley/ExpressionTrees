import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionsTester {
	
	public static String filename;
	final static String OUTPUT = "myAnswers.txt";

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
				//System.out.println("Testing");
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
	public static PrintWriter writeToFile(String file) throws FileNotFoundException {

		File f = new File(file);
		PrintWriter output = null;
		try {
			output = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			PrintWriter out = new PrintWriter(file);
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
			out = writeToFile(OUTPUT);
		} catch (FileNotFoundException e) {
			out = new PrintWriter(new File(OUTPUT));
			e.printStackTrace();
		}
		
		String[] arr = in.nextLine().split(" ");
		TreeNode tree = test.buildTree(arr);
		System.out.println("evalTree: " + test.evalTree(tree));
		System.out.println("toInfixNotation: " + test.toInfixNotation(tree));
		System.out.println("toPostfixNotation: " + test.toPostfixNotation(tree));
		System.out.println("toPrefixNotation: " + test.toPrefixNotation(tree));
		System.out.println("postfixEval: " + test.postfixEval(arr));
		System.out.println("\n\n");
		out.close();
	}
}
