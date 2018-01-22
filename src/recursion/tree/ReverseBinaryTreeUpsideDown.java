package recursion.tree;
/**
 * 
 * @author yifengguo
 * Given a binary tree where all the right nodes are leaf nodes, 
 * flip it upside down and turn it into a tree with left leaf nodes as the root.

	Examples
	
	        1
	
	      /    \
	
	    2        5
	
	  /   \
	
	3      4
	
	is reversed to
	
	        3
	
	      /    \
	
	    2        4
	
	  /   \
	
	1      5
	
	How is the binary tree represented?
	
	We use the pre order traversal sequence with a special symbol "#" denoting the null node.
	
	For Example:
	
	The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:
	
	    1
	
	  /   \
	
	 2     3
	
	      /
	
	    4


 */
/*
 * time = O(n) for worst case
 * space = O(height)
 */
public class ReverseBinaryTreeUpsideDown {
	public TreeNode reverse(TreeNode root) {
		// base case
		// always look at the left subtree
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newHead = reverse(root.left); // only one newHead in whole recursion tree 
		                                       // it will be firstly returned when reach base case
		// current layer processing
		root.left.left = root;
		root.left.right = root.right;
		root.left = null;
		root.right = null;
		// return
		return newHead;
	}
}
