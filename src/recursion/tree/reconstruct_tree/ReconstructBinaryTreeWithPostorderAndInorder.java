package recursion.tree.reconstruct_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author @Yifeng
 * Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

	Assumptions
	
	The given sequences are not null and they have the same length
	There are no duplicate keys in the binary tree
	Examples
	
	postorder traversal = {1, 4, 3, 11, 8, 5}
	
	inorder traversal = {1, 3, 4, 5, 8, 11}
	
	the corresponding binary tree is
	
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
/*
 * basic idea:
 * 			use recursion
 * 			initialize mapping between in[] and post
 *          find root from post[] then find root index in in[]
 *          then we can guarantee left subtree and right subtree
 *          recursively generate root.left and root.right
 *          then link them together
 */
public class ReconstructBinaryTreeWithPostorderAndInorder {
	public TreeNode reconstruct(int[] in, int[] post) {
		if (in == null || in.length == 0 || post == null || post.length == 0) {
			return null;
		}
		Map<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			indexMap.put(in[i], i);
		}
		return helper(in, 0, in.length - 1, post, 0, post.length - 1, indexMap);
	}

	private TreeNode helper(int[] in, int inLeft, int inRight, int[] post, int postLeft, int postRight,
			Map<Integer, Integer> indexMap) {
		// Base case
		// reach leaf node
		if (inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(post[postRight]); // initialize current layer root via post[]
		
		int leftSize = indexMap.get(root.val) - inLeft;  // calculate left subtree element number via root index

		root.left = helper(in, inLeft, inLeft + leftSize - 1, post, postLeft, postLeft + leftSize - 1, indexMap);

		root.right = helper(in, inLeft + leftSize + 1, inRight, post, postLeft + leftSize, postRight - 1, indexMap);
		
		return root;
	}
}