package binary_search_tree;
/**
 * 
 * @author yifengguo
  In a binary search tree, find the node containing the largest number smaller than the given target number.

	If there is no such number, return INT_MIN.
	
	Assumptions:
	
	The given root is not null.
	There are no duplicate keys in the binary search tree.
	Examples
	
	    5
	
	  /    \
	
	2      11
	
	     /    \
	
	    6     14
	
	largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
	
	largest number smaller than 10 is 6
	
	largest number smaller than 6 is 5


 */
/*
 * time = O(n)
 * space = O(1)
 */
public class LargestNumberSmallerThanTargetInBST {
	public int largestSmaller(TreeNode root, int target) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		TreeNode cur = root;
		int tmpMax = Integer.MIN_VALUE;
		while (cur != null) {
			if (cur.key < target) { // only when cur.key < target, update curMax and go right to try to get larger one
				tmpMax = cur.key;
				cur = cur.right;
			} else { // if cur.key > target, directly go left
				cur = cur.left;
			}
		}
		return tmpMax;
	}
}
