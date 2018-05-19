package DataStructureImplementations.SegmentTree;

import java.util.LinkedList;
import java.util.Queue;

class SegmentTreeNode {
	SegmentTreeNode left, right;
	int start, end; // left limit, right limit
	double max; // Segment Tree with every node value
	         // represents the corresponding interval
           // max value in the array
	
	int count; // represents the element count in this node's range
	
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Second Constructor
	 *    [3, 2, 1, 4]
	 *                                  [0,3](max = 4)
	 *                          /                           \
	 *                  [0,1](max = 3)                      [2,3](max = 4)
	 *                  /             \                          /          \
	 *               [0,0](max = 3)  [1,1](max = 2)     [2,2](max = 1)     [3,3](max = 4)
	 *
	 *
	 */
	public SegmentTreeNode(int start, int end, double max) {
		this(start, end);
		this.max = max;
	}

	/**
	 * Third Constructor
	 * Given [0, 2, 3]
	 *                                  [0,3](count = 3)
         *                          /                           \
         *                 [0,1](count = 1)                      [2,3](count = 2)
         *                  /             \                          /          \
         *           [0,0](count = 1)  [1,1](count = 0)     [2,2](count = 1)     [3,3](count = 1)
	 */
	public SegmentTreeNode(int start, int end, int count) {
		this(start, end);
		this.count = count;
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
	 * This method enable query max value given interval
	 */
	public SegmentTreeNode buildWithMax(int[] arr) {
		return buildSegmentTreeWithMax(0, arr.length - 1, arr);
	}

	/**
	 * Helper function fur build2()
	 * build a Segment Tree by an array
	 * initial left_limit is index 0
	 * initial left_limit is index arr.length - 1
	 */
	private SegmentTreeNode buildSegmentTreeWithMax(int start, int end, int[] arr) {
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

	public SegmentTreeNode buildWithCount(int[] arr) {
		return buildSegmentTreeWithCount(0, arr.length - 1, arr);
	}

	private SegmentTreeNode buildSegmentTreeWithCount(int start, int end, int[] arr) {
		// invalid case
		if (start > end) {
			return null;
		}
		
		// default count of the node is set as the arr.length
		SegmentTreeNode root = new SegmentTreeNode(start, end, arr.length);
		
		// base case
		if (start == end) {
			int realCount = 0;
			for (int i : arr) {
				if (i == start) {
					realCount++;
				}
			}
			root.count = realCount;
			return;
		}

		int mid = start + ((end - start) >>> 1);

		root.left = buildSegmentTreeWithCount(start, Math.min(mid, end), arr);
		root.right = buildSegmentTreeWithCount(Math.min(mid + 1, start), end, arr);

		if (root.left != null) {
			root.count += root.left.count;
		}

		if (root.right != null) {
			root.count += root.right.count;
		}

		return root;
	}
	
	/**
	 * query the max value among the range [start, end] in current Segment Tree
	 */
	public double query(SegmentTreeNode root, int start, int end) {
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
		double leftMax = query(root.left, start, Math.min(end, mid));
		double rightMax = query(root.right, Math.min(mid + 1, start), end);
		
		// return larger max value from child nodes
		return Math.max(leftMax, rightMax);
	}

	/**
	 *	query the count of element among the current node's interval given
	 *	with an array
	 */
	public int queryCount(SegmentTreeNode root, int start, int end) {
		// invalid case
		if (root == null || start > end || root.end < start || root.start > end) {
			return 0;
		}

		if (root.start <= start && root.end >= end) {
			return root.count;
		}

		int mid = root.start + ((root.end - root.start) >>> 1);
		int leftCount = queryCount(root.left, start, Math.min(mid, end));
		int rightCount = queryCount(root.right, Math.min(mid + 1, start), end);

		return leftCount + rightCount;

	}

	/**
	 * To modify the value of the entry on the given index of the array
	 */
	public void modify(SegmentTreeNode root, int index, int value) {
		// invalid case
		if (root == null || index < root.start || index > root.end) {
			return;
		}

		// base case
		if (index == root.start && index == root.end) {
			root.max = value;
			return;
		}

		int mid = root.start + ((root.end - root.start) >>> 1);

		if (index <= mid) {
			modify(root.left, index, value);
		} else {
			modify(root.right, index, value);
		}

		// update parent's max in bottom-up direction if necessary
		root.max = Math.max(root.left == null ? Integer.MIN_VALUE : root.left.max,
				                root.right == null ? Integer.MIN_VALUE : root.right.max);
		return;
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
		// tree.BFS_print(root);
		
		SegmentTreeNode root2 = tree.buildWithMax(new int[]{0, 5, 2, 1});
		double queryMax = tree.query(root2, 0, 1);
		System.out.println(queryMax);
	}
}
