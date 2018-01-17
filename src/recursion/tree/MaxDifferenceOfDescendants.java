package recursion.tree;
/**
 * 
 * @author yifengguo
 * Find the node with the max difference in the total number of 
 * descendants in its left subtree and right subtree
 */
public class MaxDifferenceOfDescendants {
	public int globalMax = Integer.MIN_VALUE;
	public TreeNode res;
	public TreeNode find(TreeNode root) {
		helper(root);
		return res;
	}
	
	private int helper(TreeNode root) {
		// base case
		if (root == null) {
			return 0;
		}
		
		// step 1: get total child nodes of left subtree and right tree respectively
		int left_total = helper(root.left);
		int right_total = helper(root.right);
		
		// step 2: current layer process
		// if current node's difference between left subtree and right subtree
		// is greater than globalMax, then update globalMax
		// and update res as current node
		if (Math.abs(left_total - right_total) > globalMax) {
			globalMax = Math.abs(left_total - right_total);
			res = root;
		}
		// step 3: report to parent node
		// total nodes = current node + left_total + right_total
		return left_total + right_total + 1; // report total nodes including current node itself to its parent node
	}
}
