package graph.BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author yifengguo
 * Given a matrix of size N x M. For each row the elements are sorted in ascending order, 
 * and for each column the elements are also sorted in ascending order. Find the Kth smallest 
 * number in it.

	Assumptions
	
	the matrix is not null, N > 0 and M > 0
	K > 0 and K <= N * M
	Examples
	
	{ {1,  3,   5,  7},
	
	  {2,  4,   8,   9},
	
	  {3,  5, 11, 15},
	
	  {6,  8, 13, 18} }
	
	the 5th smallest number is 4
	the 8th smallest number is 6
 */
/*
 * basic idea: best first search using PriorityQueue
 *             the kth smallest element is the kth
 *             entry polled by pq as well
 *             and because we need to check validation of next
 *             added element, so we need to apply an auxiliary 
 *             class to record entry's coordinates and its value
 *             in matrix
 *             
 *  1. initial state: matrix[0][0]  (start node which needs to offer into heap at very beginning)
 *  
 *  2. expansion / generation rule:
 *                    2.1 expand matrix[i][j]  (poll out from heap)
 *                    2.2 generate matrix[i + 1][j]
 *                                 matrix[i][j + 1]   if it is not out of bound and not visited
 *  3. termination condition: meet the kth element polled out from heap or heap is empty
 *  
 *  time = O(n log k)
 *  space = O(k)
 */
public class KthSmallestNumberInSortedMatrix {
	class Entry {
		int x; // x coordinate in matrix
		int y; // y coordinate in matrix
		int value; // value of matrix[x][y]

		public Entry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
			return Integer.MAX_VALUE;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] visit = new boolean[m][n];
		PriorityQueue<Entry> pq = new PriorityQueue<>(new Comparator<Entry>() {
			@Override
			public int compare(Entry e1, Entry e2) {
				if (e1.value == e2.value) {
					return 0;
				}
				return e1.value < e2.value ? -1 : 1;
			}
		});
		pq.offer(new Entry(0, 0, matrix[0][0]));
		visit[0][0] = true;
		int count = 0;
		while (!pq.isEmpty()) {
			Entry cur = pq.poll();
			count++;
			if (count == k) {
				return cur.value;
			}
			// go down
			if (cur.x + 1 < m && !visit[cur.x + 1][cur.y]) {
				pq.offer(new Entry(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
				visit[cur.x + 1][cur.y] = true;
			}
			// go right
			if (cur.y + 1 < n && !visit[cur.x][cur.y + 1]) {
				pq.offer(new Entry(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
				visit[cur.x][cur.y + 1] = true;
			}
		}
		return -1;
	}
}

