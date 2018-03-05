package recursion.tree;
/**
 * 
 * @author guoyifeng
 * Given two nodes in a binary tree, find their lowest common ancestor 
 * (the given two nodes are not guaranteed to be in the binary tree).

	Return null If any of the nodes is not in the tree.
	
	Assumptions
	
	There is no parent pointer for the nodes in the binary tree
	
	The given two nodes are not guaranteed to be in the binary tree
	
	Examples
	
	        5
	
	      /   \
	
	     9     12
	
	   /  \      \
	
	  2    3      14

	The lowest common ancestor of 2 and 14 is 5
	
	The lowest common ancestor of 2 and 9 is 9
	
	The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */
/*
 * basic idea: determine if both two nodes are in the tree or not and do the LCA
 */
public class LCA2_TargetMayNotInTheTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null || one == null || two == null) {
			return root;
		}
		if (inTree(root, one) && inTree(root, two)) {
			return helper(root, one, two);
		}
		return null;
	}

	private TreeNode helper(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null || root == one || root == two) {
			return root;
		}
		TreeNode left = helper(root.left, one, two);
		TreeNode right = helper(root.right, one, two);
		if (left == null && right == null) {
			return null;
		} else if (left == null || right == null) {
			return left == null ? right : left;
		} else {
			return root;
		}
	}

	private boolean inTree(TreeNode root, TreeNode a) {
		if (root == a) {
			return true;
		}
		if (root == null) {
			return false;
		}
		return inTree(root.left, a) || inTree(root.right, a);
	}
}
