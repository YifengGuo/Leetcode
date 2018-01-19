package recursion.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 
 * @author @Yifeng
 * Given a binary tree in which each node contains an integer number. 
 * Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), 
 * the sum of the numbers on the path is the given target number.

	Examples
	
	    5
	
	  /    \
	
	2      11
	
	     /    \
	
	    6     14
	
	  /
	
	 3
	
	If target = 17, There exists a path 11 + 6, the sum of the path is target.
	
	If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
	
	If target = 10, There does not exist any paths sum of which is target.
	
	If target = 11, There exists a path only containing the node 11.
	
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
		      -5 cur
		     /  \
		    2   11 cur
		       /  \
		     6 cur 14
		    / \
		   8   10
		   
		   if target = 17, there exists a path 11 + 6, the sum of the path is target
		   if target = 100, there does not exist any paths sum of which is target
		   
		   Solution 1: 
		   path_prefix = {-5, 11, 6}
		   with 6 as the bottom end, there are 3 paths: (linear scan looking back)
		   6         sum = 6
		   6, 11     sum = 6 + 11 = 17
		   6, 11, -5 sum = 6 + 11 + -5 = 12
		   This solution's time complexity:
		   				there are n nodes in the tree
		   				for each node X, we do a for loop O(height) (straight up and down path)
		   				so time complexity is O(n * height) = O(n ^ 2) for worst case(not balanced but a linkedlist)
		   				
		   Solution 2:
		   how to optimize linear scan looking back process
		   	partial sum:
		   	-5
		   	-5 + 11 
		   	-5 + 11 + 6
		   		then find what kind of data structure to store this?
		   		
		   	for	path_prefix = {-5,11,6,10}
		   	for each node:
		   	problem is to check if X + target == path_prefix to current node
		   	i.e check if X == path_prefix to current node - target
  			X is like a partial_path_sum we have already add into set
  			if current prefix_sum - target is what we have added, it means
  			target is another partial_path in the tree, so target exists
  			
		   	so we decide to use a hashset to record partial_sum and let current node value - target
		   		eg: when current node = 6
		   		HashSet = {-5, 6, 12}
		   		path_prefix to current node - target = 12 - 17 = -5
		   		-5 is in the HashSet
		   	check if this difference is in the hashset or not O(1) time complexity
		   	And we have n nodes so 
		   	 	Time complexity is O(n * 1 ) = O(n)
 *  			Space : O(n)  only one HashSet, do not initialize new HashSet on each layer
 *  
 */
public class BinaryTreePathSumToTarget3 {
	// method 1: explicitly calculate the prefix_sum
	//	path_prefix = {-5, 11, 6}
	//	   with 6 as the bottom end, there are 3 paths: (linear scan looking back)
	//	   6         sum = 6
	//	   6, 11     sum = 6 + 11 = 17
	//	   6, 11, -5 sum = 6 + 11 + -5 = 12
	public boolean exist1(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		// to maintain the prefixSum from root to each node on this path
		List<Integer> path = new ArrayList<>();
		return helper1(root, target, path);
	}
	
	private boolean helper1(TreeNode root, int target, List<Integer> path) {
		// base case
		if (root == null) {
			return false;
		}
		path.add(root.key);
		int prefixSum = 0;
		for (int i = path.size() - 1; i >= 0; i++) { // calculate all the path_value 
			                                         // ended at current node to see
			                                         // if there exists a path == target
			prefixSum += path.get(i);
			if (prefixSum == target) {
				return true;
			}
		}
		if (root.left != null && helper1(root.left, target, path)) {
			return true;
		}
		if (root.right != null && helper1(root.right, target, path)) {
			return true;
		}
		path.remove(path.size() - 1); // recover when backtracking
		return false;
	}

	
	// method 2: use set and idea: X + target == prefixSum to current node == > target is a partial_path in the tree
	public boolean exist2(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		Set<Integer> set = new HashSet<>();
		int prefixSum = 0;
		set.add(0);
		return helper2(root, target, set, prefixSum);
	}

	private boolean helper2(TreeNode root, int target, Set<Integer> set, int prefixSum) {
		if (root == null) {
			return false;
		}
		prefixSum += root.key;
		if (set.contains(prefixSum - target)) { // it means target is a partial_path 
			                                    // in the tree
			return true;
		}
		boolean needRemove = set.add(prefixSum);
		if (root.left != null && helper2(root.left, target, set, prefixSum)) {
			return true;
		}
		if (root.right != null && helper2(root.right, target, set, prefixSum)) {
			return true;
		}
		if (needRemove) { // if we have added prefixSum at current level, when backtracking, we
			              // need to remove it from set
			set.remove(prefixSum);
		}
		return false;
	}
}



