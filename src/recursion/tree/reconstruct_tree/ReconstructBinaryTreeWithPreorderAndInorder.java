package recursion.tree.reconstruct_tree;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author yifengguo
 * Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

	Assumptions
	
	The given sequences are not null and they have the same length
	There are no duplicate keys in the binary tree
	Examples
	
	preorder traversal = {5, 3, 1, 4, 8, 11}
	
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
/*	basic idea
 *  example: tree in Q3
				 index      0	1		2	3		4	5		6
				 pre-order: 10	5 ||	2	7  |  	15	12 ||	20
				 in-order:  2	5 ||	7	10 | 	12	15 ||	20
				 
		So basic solution idea to Q4 is to separate global problem into two parts(recursively), return 
		a root node of subtree on each side
			find 10 as radical root of tree via pre-order, then look at 10 in in-order 
			to determine which part is left subtree and right subtree
			then recursively find second layer left subtree root is 5 via pre-order, 
			then find left and right subtree in  {2 5 7}, then link 5 as 10's left child
							...
			what we need in recursion function:  pre-order array, pre-order left limit, pre-order right limit, 
												 in-order array, pre-order left limit, pre-order right limit
												 A HashMap to map node's index in pre-order array and in-order array with O(1) time
 */
public class ReconstructBinaryTreeWithPreorderAndInorder {
	public TreeNode reconstruct(int[] in, int[] pre) {
		Map<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			indexMap.put(in[i], i); // record TreeNode value index in in[]
									// guarantee find current root node index in
									// in[]
									// with O(1) time
		                            // simply map is to let us know the size of current node's left substree and right subtree
		}
		TreeNode root = helper(in, 0, in.length - 1, pre, 0, pre.length - 1, indexMap);
		return root;
	}

	private TreeNode helper(int[] in, int inLeft, int inRight, int[] pre, int preLeft, int preRight,
			Map<Integer, Integer> indexMap) {
		if (inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preLeft]);
		int leftSize = indexMap.get(root.val) - inLeft;

		root.left = helper(in, inLeft, inLeft + leftSize - 1, pre, preLeft + 1, preLeft + leftSize, indexMap);
		root.right = helper(in, inLeft + leftSize + 1, inRight, pre, preLeft + leftSize + 1, preRight, indexMap);
		return root;
	}
}
