package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng
 * 
 	Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
/*
 * basic idea: divide and conquer
 * time = O(n)
 * space = O(n)
 */
public class ConvertSortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		TreeNode root = helper(0, nums.length - 1, nums);
		return root;
	}

	private TreeNode helper(int start, int end, int[] nums) {
		if (start > end) {
			return null;
		}
		TreeNode res = new TreeNode(nums[(start + end) / 2]);
		res.left = helper(start, (start + end) / 2 - 1, nums);
		res.right = helper((start + end) / 2 + 1, end, nums);
		return res;
	}
}