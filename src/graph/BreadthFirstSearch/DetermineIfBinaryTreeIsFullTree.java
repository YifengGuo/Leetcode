package graph.BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author guoyifeng
 * 
	Determine if a given binary tree is full.
	
	A full tree is a binary tree such that each level of it is full.
	
	If the root is null, return false.
 */
/*
 * full binary tree: except the leaf node, every node in the tree must have two children
 */
public class DetermineIfBinaryTreeIsFullTree {
	public boolean isFull(TreeNode root) {
		if (root == null) {
			return false;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
				// it is ok for a leaf node has no child
				if (cur.left == null && cur.right == null) {
					continue;
				}
				// it is not allowable for a node only has one child in the full binary tree
				if (cur.left == null || cur.right == null) {
					return false;
				}
			}
		}
		return true;
	}
}
