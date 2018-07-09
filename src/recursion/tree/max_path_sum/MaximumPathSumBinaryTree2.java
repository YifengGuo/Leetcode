package recursion.tree.max_path_sum;
/**
 * 
 * @author yifengguo
  Given a binary tree in which each node contains an integer number. 
  Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 

	Assumptions
	
	​The root of the given binary tree is not null
	Examples
	
	   -1
	
	  /    \
	
	2      11
	
	     /    \
	
	    6    -14
	
	one example of paths could be -14 -> 11 -> -1 -> 2
	
	another example could be the node 11 itself
	
	The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
	
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
 * for it is between any node to any node
 * so any time we calculate the path sum, we
 * need to update globalMax if greater value exists
 */
public class MaximumPathSumBinaryTree2 {
	public int globalMax = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		helper(root);
		return globalMax;
	}

	private int helper(TreeNode root) {
		// base case
		if (root == null) {
			return 0;
		}

		// step 1
		// for what we get from subtree path sum may be negative value
		// for negative values cannot contribute greater globalMax
		// we could filter the negative value out before processing
		int left = Math.max(helper(root.left), 0);
		int right = Math.max(helper(root.right), 0);

		// step 2: current layer process
		if (root.left != null && root.right != null) {
			globalMax = Math.max(globalMax, root.key + left + right);
			return root.key + Math.max(left, right);
		}
		//step 3: report to parent node
		globalMax = Math.max(globalMax, Math.max(left, right) + root.key);
		return root.left == null ? right + root.key : left + root.key;
	}
}