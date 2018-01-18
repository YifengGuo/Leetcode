package recursion.tree;

public class MaximumPathSumBinaryTreeRootToLeaf {
	public int globalMax = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root, 0);
		return globalMax;
	}
	
	private void helper(TreeNode root, int prefixSum) {
		// base case
		if (root == null) {
			return;
		}
		
		// root to leaf, so only when root.left == null && root.right == null
		// it means we reach a leaf node and 
		// can we update the value of globalMax
		if (root.left == null && root.right == null) {
			globalMax = Math.max(globalMax, prefixSum + root.key);
		}
		
		helper(root.left, prefixSum + root.key); // update prefixSum and go left
		helper(root.right, prefixSum + root.key); // update prefixSum and go right
		
	}
}
