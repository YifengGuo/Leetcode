package graph.BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author guoyifeng
 * Given a binary Tree and the two keys, determine whether the two nodes are cousins of each other or not. 
 * Two nodes are cousins of each other if they are at the same level and have different parents.

	Assumptions:
	
	It is not guranteed the two keys are all in the binary tree.
	There are no duplicate keys in the binary tree.
	Examples:
	
	     6
	   /   \
	  3     5
	 / \   / \
	
	7   8 1   3
	7 and 1, result is true.
	3 and 5, result is false.
	7 and 5, result is false.
 */
public class CousinsInBinaryTree {
	/*
	 * method 1: BFS
	 */
	public boolean isCousin(TreeNode root, int a, int b) {
		if (root == null) {
			return false;
		}

		Queue<TreeNode> q = new LinkedList<>();

		q.offer(root);

		while (!q.isEmpty()) {
			// need to guarantee parent are on the same level
			// so we need to initialize the parents in the while loop
			TreeNode parentA = null;
			TreeNode parentB = null;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();

				if (cur.left != null) {
					q.offer(cur.left);
					if (cur.left.val == a) {
						parentA = cur;
					} else if (cur.left.val == b) {
						parentB = cur;
					}
				}

				if (cur.right != null) {
					q.offer(cur.right);
					if (cur.right.val == a) {
						parentA = cur;
					} else if (cur.right.val == b) {
						parentB = cur;
					}
				}
			}
			if (parentA != null && parentB != null && parentA != parentB) {
				return true;
			}
		}
		return false;
	}
}
