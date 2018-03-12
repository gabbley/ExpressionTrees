import java.io.File;
import java.io.FileNotFoundException;
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
	
	public static void main(String[] args) {
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
			while (in.hasNextLine()){
				
			}
		}
			
			//System.out.println("haha");
	}
	
	public String[] toArray(String s){
		String[] arr = new String[s.length()];
		for (char c : s.toCharArray()){
			
		}
	}
}
