package recursion.tree.pathsum;
/**
 * 
 * @author yifengguo
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf 
 * path such that adding up all the values along the path equals the given sum.
	
	Note: A leaf is a node with no children.
	
	Example:
	
	Given the below binary tree and sum = 22,
	
	      5
	     / \
	    4   8
	   /   / \
	  11  13  4
	 /  \      \
	7    2      1
	return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class PathSum1 {
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		return helper(root, sum, 0);
	}

	public boolean helper(TreeNode root, int sum, int cur) {
		if (root == null) {
			return false;
		}
		if (root.val + cur == sum && root.left == null && root.right == null) {
			return true;
		}
		cur += root.val;
		return helper(root.left, sum, cur) || helper(root.right, sum, cur);
	}
}
