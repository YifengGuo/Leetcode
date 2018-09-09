package recursion.divide_and_conquer;
/**
 * 
 * @author guoyifeng
 * Given an array of numbers, verify whether it is the correct preorder 
 * traversal sequence of a binary search tree.

	You may assume each number in the sequence is unique.
	
	Consider the following binary search tree: 

	     5
	    / \
	   2   6
	  / \
	 1   3
	Example 1:
	
	Input: [5,2,6,1,3]
	Output: false
	Example 2:
	
	Input: [5,2,1,3,6]
	Output: true
 */
//5 2 1 3 6
//
public class VerifyPreorderSequenceInBST {
	public boolean verifyPreorder(int[] preorder) {
		if (preorder == null || preorder.length == 0) return true;
		return helper(preorder, 0, preorder.length - 1);
	}
 
	 private boolean helper(int[] preorder, int start, int end) {
	     // base case
	     // only one node
	     if (start >= end) {
	         return true;
	     }
	     int root = preorder[start];
	     int right = -1; // the root of right subtree, initialize as -1
	     for (int i = start + 1; i <= end; i++) { // loop to find root of right subtree
	         if (right == -1 && preorder[i] > root) right = i;
	         if (right != -1 && preorder[i] < root) return false; // all the right subtree nodes > root
	     }
	     if (right == -1) {
	         return helper(preorder, start + 1, end);
	     } else {
	         return helper(preorder, start + 1, right - 1) && helper(preorder, right, end); // divide and conquer
	     }
	 }
}
