package TopInterviewQuestions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author guoyifeng
 	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	But the following [1,2,2,null,3,null,3] is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
 */
public class SymmetricBinaryTree {
	/*
	 * recursive solution
	 * design recursion function by thinking each time 
	 * we are comparing the left.left with right.right  and left.right with rigt.left
	 * so recursion function should design with two TreeNodes
	 */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) { // base case
            return true;
        } else if (left == null || right == null) { // if one of two nodes is empty, so cannot be possibly symmetric
            return false;
        } else if (left.val != right.val) { // left and right both not null
                return false;
        }
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
    
    /*
     * iterative solution using queue
     */
	public boolean isSymmetricIterative(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root.left);
		queue.offer(root.right);
		while (!queue.isEmpty()) {
			TreeNode left = queue.poll();
			TreeNode right = queue.poll();
			if (left == null && right == null) { // left.left <=> right.right or left.right <=> right.left are both null
				                               // so far the tree is symmetric
				continue;
			} else if (left == null || right == null) { // left.left <=> right.right or left.right <=> right.left 
											          // one node of its pair is null
											          // so the tree cannot be symmetric
				return false;
			} else if (left.val != right.val) { // value of corresponding node is not equal, not symmetric
				return false;
			}
			// if some child TreeNode is null, the queue will offer null and store it
			queue.offer(left.left);
			queue.offer(right.right);
			queue.offer(left.right);
			queue.offer(right.left);
		}
		return true;
	}
	/*
	 * test offer null to queue
	 */
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(null);
		q.offer(1);
		q.offer(2);
		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}
}
