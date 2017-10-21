package TopInterviewQuestions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author guoyifeng 
 * 		   Given a n x n matrix where each of the rows and columns are
 *         sorted in ascending order, find the kth smallest element in the
 *         matrix.
 * 
 *         Note that it is the kth smallest element in the sorted order, not the
 *         kth distinct element.
 * 
 *         Example:
 * 
 *         matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8,
 * 
 *         return 13. Note: You may assume k is always valid, 1 ≤ k ≤ n2.
 */
/*
 * time = O(klogk)
 * space = O(n ^ 2)
 */
public class KthSmallestElementInASortedMatrix {
	class Entry {
		int x; // x coordinate
		int y; // y coordinate
		int value; // value in the matrix

		public Entry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
			return 0;
		}
		boolean[][] visit = new boolean[matrix.length][matrix[0].length];
		PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
			@Override
			public int compare(Entry a, Entry b) {
				if (a.value == b.value) {
					return 0;
				}
				return a.value < b.value ? -1 : 1;
			}
		});
		Entry start = new Entry(0, 0, matrix[0][0]);
        minHeap.offer(start);
		// run best first search for each row and column is sorted.
        // go down and right we can always get a larger element
        // remove first k - 1 elements in ascending order
		for (int i = 0; i < k - 1; i++) {
			Entry cur = minHeap.poll();
			// go down
			if (cur.x + 1 < matrix.length && !visit[cur.x + 1][cur.y]) {
				visit[cur.x + 1][cur.y] = true;
				minHeap.offer(new Entry(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
			}
			// go right
			if (cur.y + 1 < matrix[0].length && !visit[cur.x][cur.y + 1]) {
				visit[cur.x][cur.y + 1] = true;
				minHeap.offer(new Entry(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
			}
		}
		return minHeap.peek().value;
	}
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1,1,1},{1,1,1},{1,1,2}};
		int k = 8;
		System.out.println(new KthSmallestElementInASortedMatrix().kthSmallest(matrix, k));
	}
}