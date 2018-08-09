package recursion.tree.three_step;


/**
 * 
 * @author yifengguo
  Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of 
  every node’s left and right subtree differ by at most 1.

	Examples
	
	        5
	
	      /    \
	
	    3        8
	
	  /   \        \
	
	1      4        11
	
	is balanced binary tree,
	
	        5
	
	      /
	
	    3
	
	  /   \
	
	1      4
	
	is not balanced binary tree.
	
	Corner Cases
	
	What if the binary tree is null? Return true in this case.
	How is the binary tree represented?
	
	We use the level order traversal sequence with a special symbol "#" denoting the null node.
	
	For Example:
	
	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	
	    1
	
	  /   \
	
	 2     3
	
	      /
	
	    4


 */
public class CheckIfBinaryTreeIsBalanced {
	/*
	 * better solution
	 * use height and -1 to represent if current tree is balanced or not
	 */
	public boolean isBalanced(TreeNode root) {
	    // base case
	    if (root == null) {
	      return true;
	    }
	    
	    int len = getHeight(root); // use length to determine if whole tree is balanced or not
	    
	    return len != -1;
	  }
	  
	/*
	 * use -1 as a flag to represent current node's subtree is balanced or not
	 */
	  private int getHeight(TreeNode root) {
	    if (root == null) {
	      return 0;
	    }
	    int left = getHeight(root.left);
	    int right = getHeight(root.right);
	    
	    if (left == -1 || right == -1 || 
	    Math.abs(left - right) > 1) {  // if one of the substree is not balanced
	    	                           // or current node's left and right substree 
	    	                           // depth difference > 1
	    	                           // then return -1
	      return -1;
	    }
	    return Math.max(left, right) + 1; // if both subtree is balanced, return depth normally
	  }
	  
	  
	// bad solution for we need to invoke recursion function
	// in another recursion function
	// time complexity is huge
//	public boolean isBalanced(TreeNode root) {
//		// base case
//		if (root == null) {
//			return true;
//		}
//		// step 1:
//		int left = getHeight(root.left);
//		int right = getHeight(root.right);
//		
//		// step 2:
//		if (Math.abs(left - right) > 1) {
//			return false;
//		}
//		
//		return isBalanced(root.left) && isBalanced(root.right);
//	}
//	
//	private int getHeight(TreeNode root) {
//		// base case
//		if (root == null) {
//			return 0;
//		}
//		int left = getHeight(root.left);
//		int right = getHeight(root.right);
//		
//		return Math.max(right, left) + 1;
//	}
}
