package recursion.tree;

import java.util.Arrays;
/**
 * 
 * @author yifengguo
 * Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.

	Assumptions
	
	The given sequence is not null
	There are no duplicate keys in the binary search tree
	Examples
	
	preorder traversal = {5, 3, 1, 4, 8, 11}
	
	The corresponding binary search tree is
	
	        5
	
	      /    \
	
	    3        8
	
	  /   \        \
	
	1      4        11
	
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
public class ReconstructBinarySearchTreeWithPreorderTraversal {
	public TreeNode reconstruct(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		return helper(pre);
	}

	private TreeNode helper(int[] pre) {
		// base case
		if (pre.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(pre[0]);
		int right_start = pre.length; // initialize right_start as pre.length, if cannot find valid right_start
		                              // it means the root only has left substree, so recursion function will copy left tree with left substree
		                              // and root.right will return null for its pre.length == 0
		for (int i = 1; i < pre.length; i++) {
			if (pre[i] > pre[0]) {
				right_start = i;
				break;
			}
		}
		root.left = helper(Arrays.copyOfRange(pre, 1, right_start));
		root.right = helper(Arrays.copyOfRange(pre, right_start, pre.length));
		return root;
	}
}
