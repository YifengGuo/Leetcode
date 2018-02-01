package binary_search_tree;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author yifengguo
 * In a binary search tree, find the node containing the closest number to the given target number.

	Assumptions:
	
	The given root is not null.
	There are no duplicate keys in the binary search tree.
	Examples:
	
	    5
	
	  /    \
	
	2      11
	
	     /    \
	
	    6     14
	
	closest number to 4 is 5
	
	closest number to 10 is 11
	
	closest number to 6 is 6
	
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
 * basic idea: simple BFS
 * time = O(n)
 * space = O(n)
 */
public class ClosestNumberInBST {
	public int closest(TreeNode root, int target) {
		if (root == null) {
			return -1;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int globalMin = Integer.MAX_VALUE;
		TreeNode res = root;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (Math.abs(cur.key - target) < globalMin) {
					globalMin = Math.abs(cur.key - target);
					res = cur;
				}
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
		}
		return res.key;
	}
}