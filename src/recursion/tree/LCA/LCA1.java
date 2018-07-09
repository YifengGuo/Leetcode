package recursion.tree.LCA;
/**
 * 
 * @author yifengguo
  Given two nodes in a binary tree, find their lowest common ancestor.

	Assumptions
	
	There is no parent pointer for the nodes in the binary tree
	
	The given two nodes are guaranteed to be in the binary tree
	
	Examples
	
	        5
	
	      /   \
	
	     9     12
	
	   /  \      \
	
	  2    3      14
	
	The lowest common ancestor of 2 and 14 is 5
	
	The lowest common ancestor of 2 and 9 is 9
 */
/*
 * basic idea: 3-step process (applicable for all bottom-up value pass problems)
 * 		step 1: what do you expect from you left-child / right-child
 *              (usually it is the return type of the recursion function)
 *              
 *      step 2: what do you want to do in the current layer?
 *      
 *      step 3: what do you want to report to your parent node? (value passed during step 1 and step 3 is the same)
 */
class TreeNode {
	public int key;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int key) {
		this.key = key;
	}
}
public class LCA1 {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null || root == a || root == b) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, a, b);
		TreeNode right = lowestCommonAncestor(root.right, a, b);
		if (left == null && right == null) {
			return null;
		} else if (left == null || right == null) {
			return left == null ? right : left;
		} else {
			return root;
		}
	}
}
