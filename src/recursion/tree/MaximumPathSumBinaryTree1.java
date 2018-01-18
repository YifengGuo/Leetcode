package recursion.tree;
/**
 * 
 * @author yifengguo
  Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one 
  leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).

	Examples
	
	  -15
	
	  /    \
	
	2      11
	
	     /    \
	
	    6     14
	
	The maximum path sum is 6 + 11 + 14 = 31.
	
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
/*
 * basic idea: 3 step
 *             because the problem asked path sum between two leaf nodes
 *             so only when two leaf nodes are guaranteed to be existed,
 *             can we update the value of globalMax
 */
public class MaximumPathSumBinaryTree1 {
	public int globalMax = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		
		helper(root);
		
		return globalMax;
	}
	
	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		// step 1: get left path sum and right path sum from l/r child
		int left = helper(root.left);
		int right = helper(root.right);
		
		// step 2: current layer process
		// only when current node's left and right child are both not null
		// can we have right to update globalMax for it means
		// left and right are path sum from two leaf nodes
		if (root.left != null && root.right != null) { 
		  globalMax = Math.max(globalMax, left + right + root.key);
		  return Math.max(left, right) + root.key; // step 3: return greater path sum to parent node
		}
		// if at least one of node is null
		// return non null value to the parent node
		return root.left == null ? root.key + right : root.key + left;
	}
}
