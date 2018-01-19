package recursion.tree;
/**
 * 
 * @author yifengguo
  Given a binary tree in which each node contains an integer number. Find the maximum possible subpath 
  sum(both the starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, 
  and the subpath is allowed to contain only one node).

	Assumptions
	
	The root of given binary tree is not null
	Examples
	
	   -5
	
	  /    \
	
	2      11
	
	     /    \
	
	    6     14
	
	           /
	
	        -3
	
	The maximum path sum is 11 + 14 = 25
	
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
 * basic idea: maintain a prefix_path from root to current node
 * demo:
 *                                 10
 *                               /    \
 *                             -2     7
 *                            /   \
 *                          8     -4
 *                          
 *                from root to node 4:
 *                	prefix_of_path = {10, 2, -4}
 *                
 */
public class MaximumPathSumBinaryTree3 {
	public int globalMax = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		helper(root, 0);
		return globalMax;
	}
	private void helper(TreeNode root, int prefixSum) {
		// base case
		if (root == null) {
			return;
		}
		// because it is any node to any node
		// we need try to update whenever we reach to a new node
		globalMax = Math.max(globalMax, root.key + prefixSum);
		
		helper(root.left, Math.max(prefixSum + root.key, 0)); // update prefixSum and go left
		helper(root.right, Math.max(prefixSum + root.key, 0)); // update prefixSum and go right
	}
	
	// method 2: dp: largest subarray
	public int globalMax2 = Integer.MIN_VALUE;
	public int maxPathSum2(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int sum = 0; // cache prefix sum
		helper2(root, sum);
		return globalMax2;
	}
	
	private void helper2(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		if (sum < 0) {
			sum = root.key; // if sum cannot do positive contribution, discard it and reset it as current root.key
		} else {
			sum += root.key; // if sum can do positive contribution, keep and increase it
		}
		globalMax2 = Math.max(globalMax2, sum);
		helper2(root.left, sum);
		helper2(root.right, sum);
	}
	
	
}
