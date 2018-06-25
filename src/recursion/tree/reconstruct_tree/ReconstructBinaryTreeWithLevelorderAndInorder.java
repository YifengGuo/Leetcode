package recursion.tree.reconstruct_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author yifengguo
 * Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

	Assumptions
	
	The given sequences are not null and they have the same length
	There are no duplicate keys in the binary tree
	Examples
	
	levelorder traversal = {5, 3, 8, 1, 4, 11}
	
	inorder traversal = {1, 3, 4, 5, 8, 11}
	
	the corresponding binary tree is
	
	        5
	
	      /    \
	
	    3        8
	
	  /   \        \
	
	1      4        11
	
	How is the binary tree represented?
	
	We use  level order traversal sequence with a special symbol "#" denoting the null node.
	
	For Example:
	
	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	
	    1
	
	  /   \
	
	 2     3
	
	      /
	
	    4
 */
/*
 * time = O(height * n) = O(n ^ 2) for worst case
 * space = O(n ^ 2) for each level on recursion tree we need to initialize two lists
 */
public class ReconstructBinaryTreeWithLevelorderAndInorder {
	public TreeNode reconstruct(int[] in, int[] level) {
		if (in == null || level == null) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < level.length; i++) {
			list.add(level[i]);
		}
		return helper(list, map);
	}
	private TreeNode helper(List<Integer> cur_level, Map<Integer, Integer> map) {
		// base case
		if (cur_level.size() == 0) {
			return null;
		}
		TreeNode root = new TreeNode(cur_level.remove(0));
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		// for level order, the order node being added into list matches with the order it is on the tree level
		for (int i : cur_level) {
			if (map.get(i) < map.get(root.val)) {
				left.add(i);
			} else {
				right.add(i);
			}
		}
		root.left = helper(left, map);
		root.right = helper(right, map);
		return root;
	}
}
