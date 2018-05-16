package DataStructureImplementations.SegmentTree;

import java.util.LinkedList;
import java.util.Queue;

class SegmentTreeNode {
	SegmentTreeNode left, right;
	int start, end;
	
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
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
