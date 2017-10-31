package TopInterviewQuestions;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author guoyifeng 
 *         Given a binary search tree, write a function kthSmallest to
 *         find the kth smallest element in it.
 * 
 *         Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 *         Follow up: What if the BST is modified (insert/delete operations)
 *         often and you need to find the kth smallest frequently? How would you
 *         optimize the kthSmallest routine?
 * 
 * 
 */
/*
 * basic idea: inorder traversal of BST is an ascending sequence
 * find the kth element popped out by stack 
 */
public class KthSmallestElementInBST {
	public int kthSmallest(TreeNode root, int k) {
		if (root == null) {
			return -1;
		}
		int res = 0;
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode cur = root;
		int count = 0;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				count++;
				cur = stack.pop();
				if (count == k) {
					return cur.val;
				}
				cur = cur.right;
			}
		}
		return -1;
	}
}
