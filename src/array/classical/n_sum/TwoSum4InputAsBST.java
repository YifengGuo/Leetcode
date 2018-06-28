package array.classical.n_sum;

import java.util.Stack;

/**
 * 
 * @author guoyifeng
 * Given a Binary Search Tree and a target number, 
 * return true if there exist two elements in the BST such that their sum 
 * is equal to the given target.

	Example 1:
	Input: 
	    5
	   / \
	  3   6
	 / \   \
	2   4   7
	
	Target = 9
	
	Output: True
	Example 2:
	Input: 
	    5
	   / \
	  3   6
	 / \   \
	2   4   7
	
	Target = 28
	
	Output: False
 */
class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	TreeNode(int val) {
		this.val = val;
	}
}
/*
 * use in-order traversal property
 */
public class TwoSum4InputAsBST {
	public boolean findTarget(TreeNode root, int k) {
		Stack<TreeNode> stackL = new Stack<TreeNode>(); // iterator 1 that gets next smallest value
		Stack<TreeNode> stackR = new Stack<TreeNode>(); // iterator 2 that gets next largest value
		
		for (TreeNode cur = root; cur != null; cur = cur.left)
			stackL.push(cur);
		for (TreeNode cur = root; cur != null; cur = cur.right)
			stackR.push(cur);

		while (stackL.size() != 0 && stackR.size() != 0 && stackL.peek() != stackR.peek()) {
			int tmpSum = stackL.peek().val + stackR.peek().val;
			if (tmpSum == k)
				return true;
			else if (tmpSum < k)
				for (TreeNode cur = stackL.pop().right; cur != null; cur = cur.left)
					stackL.push(cur);
			else
				for (TreeNode cur = stackR.pop().left; cur != null; cur = cur.right)
					stackR.push(cur);
		}

		return false;
	}
}