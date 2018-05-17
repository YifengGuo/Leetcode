package DataStructureImplementations.SegmentTree;

import java.util.LinkedList;
import java.util.Queue;

class SegmentTreeNode {
	SegmentTreeNode left, right;
	int start, end; // left limit, right limit
	int max; // Segment Tree with every node value
	         // represents the corresponding interval
					 // max value in the array
	
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Second Constructor
	 *    [3, 2, 1, 4]
	 *                                  [0,3](max = 4)
	 *                          /                           \
	 *                  [0,1](max = 3)                      [1,3](max = 4)
	 *                  /             \                          /          \
	 *               [0,0](max = 3)  [1,1](max = 2)     [2,2](max = 1)     [3,3](max = 4)
	 *
	 *
	 */
	public SegmentTreeNode(int start, int end, int max) {
		this(start, end);
		this.max = max;
	}
	
	@Override
	public String toString() {
		return start == end ? start + "" : start + " " + end;
	}
}

/**
 * 
 * @author guoyifeng
 * The structure of Segment Tree is a binary tree which each
 * node has two attributes: start and end denote the segment / interval
 * 
 * The left child of node A:
 * 	start = A.left
 *  end = (A.left + A.right) / 2
 *  
 * The right child of node A:
 * 	start = (A.left + A.right) / 2 + 1
 *  end = A.right
 *  
 * If start == end, then node A has no child
 */
public class SegmentTree {
	/**
	 * First method to build a Segment Tree
	 * Time = O(n)
	 * Space = O(n)
	 */
	public SegmentTreeNode build(int start, int end) {
		// invalid case
		if (start > end) {
			return null;
		}
		
		SegmentTreeNode node = new SegmentTreeNode(start, end);
		
		// base case
		if (start == end) {
			return node;
		}
		
		int mid = start + ((end - start) >>> 1);
		
		node.left = build(start, mid);
		node.right = build(mid + 1, end);
		
		return node;
	}
	
  
	/**
	 * Second method to build a Segment Tree given an array
	 */
	public SegmentTreeNode build2(int[] arr) {
		return buildSegmentTree(0, arr.length - 1, arr);
	}

	/**
	 * Helper function fur build2()
	 * build a Segment Tree by an array
	 * initial left_limit is index 0
	 * initial left_limit is index arr.length - 1
	 */
	private SegmentTreeNode buildSegmentTree(int start, int end, int[] arr) {
		// invalid case
		if (start > end) {
			return null;
		}
		
		// default max is set as arr[start]
		SegmentTreeNode root = new SegmentTreeNode(start, end, arr[start]);

		// base case
		if (start == end) {
			return root;
		}

		int mid  = start + ((end - start) >>> 1);
		root.left = buildSegmentTree(start, mid, arr);
		root.right = buildSegmentTree(mid + 1, end, arr);

		// update max for the current node with greater value of left_child_max and right_child_max
		if (root.left != null) {
			root.max = root.left.max;
		}

		if (root.right != null && root.right.max > root.max) {
			root.max = root.right.max;
		}

		return root;
	}
	
	/**
	 * query the max value among the range [start, end] in current Segment Tree
	 */
	public int query(SegmentTreeNode root, int start, int end) {
		// corner case
		if (root == null || start > end || root.end < start || root.start > end) {
			return Integer.MIN_VALUE;
		}

		// base case
		// query range is in the current root's interval range
		if (root.start >= start && root.end <= end) {
			return root.max;
		}
		
		int mid = root.start + ((root.end - root.start) >>> 1);

		// get max value from both child within its corresponding valid interval range
		int leftMax = query(root.left, start, Math.min(end, mid));
		int rightMax = query(root.right, Math.min(mid + 1, start), end);
		
		// return larger max value from child nodes
		return Math.max(leftMax, rightMax);
	}
	/**
	 * To print the Segment Tree by BFS
	 */
	private void BFS_print(SegmentTreeNode root) {
		if (root == null) {
			return;
		}
		Queue<SegmentTreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			SegmentTreeNode cur = q.poll();
			System.out.println(cur);
			if (cur.left != null) {
				q.offer(cur.left);
			}
			if (cur.right != null) {
				q.offer(cur.right);
			}
		}
	}
	
	public static void main(String[] args) {
		int start = 0;
		int end = 3;
		SegmentTree tree = new SegmentTree();
		SegmentTreeNode root = tree.build(start, end);
		tree.BFS_print(root);
	}
}
