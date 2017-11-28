package graph.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author yifengguo
 * Get the list of list of keys in a given binary tree layer by layer. Each layer is 
 * represented by a list of keys and the keys are traversed from left to right.

	Examples
	
	        5
	
	      /    \
	
	    3        8
	
	  /   \        \
	
	 1     4        11
	
	the result is [ [5], [3, 8], [1, 4, 11] ]
	
	Corner Cases
	
	What if the binary tree is null? Return an empty list of list in this case.
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
 * basic idea: bfs
 */
public class GetKeysInBinaryTreeLayerByLayer {
	public List<List<Integer>> layerByLayer(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				level.add(cur.val);
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
			res.add(level);
		}
		return res;
	}
}
