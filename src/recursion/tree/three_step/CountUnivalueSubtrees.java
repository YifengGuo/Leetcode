package recursion.tree.three_step;

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;

	TreeNode(int val) {
		this.val = val;
	}
}

public class CountUnivalueSubtrees {
	public int countUnivalSubtrees(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// count[0] represents the number of uni-value subtrees
		int[] count = new int[1];
		isUniSubtree(root, count);
		return count[0];
	}

	// to identify if current subtree is univalue subtree or not
	private boolean isUniSubtree(TreeNode root, int[] count) {
		// base case
		if (root == null) {
			return true;
		}

		boolean left = isUniSubtree(root.left, count);
		boolean right = isUniSubtree(root.right, count);

		if (left && right) {
			if (root.left != null && root.left.val != root.val) {
				return false;
			}
			if (root.right != null && root.right.val != root.val) {
				return false;
			}
			count[0]++;
			return true;
		}
		return false;
	}
}