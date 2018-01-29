package graph.DepthFirstSearch.classical;
/**
 * 
 * @author yifengguo
 *
 */
/*
 * basic idea: use dfs to find all permutations of { and } blocks
 *             then use these blocks permutation to print the if blocks
 *             maintain a variable headings to represent how many white spaces
 *             should be printed before if { and how many white spaces should be
 *             recovered when to print }
 *             
 *             time = O(n ^ 2)
 *             space = O(n)
 */
public class PrintAllValidIfBlocks {
	public void printAllIf(int n) {
		// store left and right blocks
		char[] arr = new char[2 * n];
		helper(arr, n, n, 0);
	}
	
	/**
	 * 
	 * @param arr char array
	 * @param left remainder of left blocks
	 * @param right remainder of right blocks
	 * @param pos current block position in arr
	 * 
	 * find all permutations of { and }
	 * 
	 * arr will store like {}{}{} 
	 *                     {{{}}}
	 *                     {{}}{}
	 *                     etc
	 */
	private void helper(char[] arr, int left, int right, int pos) {
		// base case
		// if arr is full and left and right are used up
		if (pos == arr.length && left == 0 && right == 0) {
			printSolution(arr, 0);
			return;
		}
		if (left < 0 || right < 0) {
			return;
		}
		// same idea in valid parenthesis
		if (left > 0) {
			arr[pos] = '{';
			helper(arr, left - 1, right, pos + 1);
		}
		if (right > left) { // left and right blocks must be balanced
			arr[pos] = '}';
			helper(arr, left, right - 1, pos + 1);
		}
	}
	
	private void printSolution(char[] arr, int headings) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '{') {
				printSpace(headings);
				System.out.println("if" + arr[i]);
				headings += 2;
			} else {
				headings -= 2;
				printSpace(headings);
				System.out.println(arr[i]);
			}
		}
	}
	
	private void printSpace(int headings) {
		for (int i = 0; i < headings; i++) {
			System.out.print(" ");
		}
	}
	

	
	public static void main(String[] args) {
		PrintAllValidIfBlocks test = new PrintAllValidIfBlocks();
		test.printAllIf(3);
	}
}
