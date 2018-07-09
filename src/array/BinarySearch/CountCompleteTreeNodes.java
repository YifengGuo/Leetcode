package array.BinarySearch;
class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	TreeNode(int val) {
		this.val = val;
	}
}
// step 1: get the level count before the last level
// step 2: get the count of nodes on the last level by binary search
public class CountCompleteTreeNodes {
	public int countNodes(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null)
			return 1;
		TreeNode cur = root;
		int res = 0;
		int height = 0;
		while (cur.left != null) {
			res += (1 << height);
			height++;
			cur = cur.left;
		}
		// reach at the last level
		return res + getCountOnTheLastLevel(root, height);
	}

	private int getCountOnTheLastLevel(TreeNode root, int height) {
		// base case
		if (height == 1) {
			if (root.left == null)
				return 0;
			else if (root.right == null)
				return 1;
			else
				return 2;
		}
		int curHeight = 1;
		TreeNode midNode = root.left;
		while (curHeight < height) {
			midNode = midNode.right;
			curHeight++;
		}
		if (midNode == null)
			return getCountOnTheLastLevel(root.left, height - 1);
		else
			return (1 << height - 1) + getCountOnTheLastLevel(root.right, height - 1);
	}
}