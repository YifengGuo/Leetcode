package graph.BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author yifengguo
  
	Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, 
	where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.
	
	Assumptions
	
	A is not null and A is not of zero length, so as B
	K > 0 and K <= m * n
	Examples
	
	A = {1, 3, 5}, B = {4, 8}
	
	1st smallest s is 1 + 4 = 5
	2nd smallest s is 3 + 4 = 7
	3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
	5th smallest s is 3 + 8 = 11
 */
public class KthSmallestSumInTwoSortedArrays {
	class Entry {
		int x;
		int y;
		int sum;

		public Entry(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}

	public int kthSum(int[] A, int[] B, int k) {
		// Write your solution here
		if (A == null || B == null || A.length == 0 || B.length == 0) {
			return 0;
		}
		PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
			@Override
			public int compare(Entry e1, Entry e2) {
				if (e1.sum == e2.sum) {
					return 0;
				}
				return e1.sum < e2.sum ? -1 : 1;
			}
		});
		// initial state: start node
		Entry start = new Entry(0, 0, A[0] + B[0]);
		// deduplicate
		boolean[][] visited = new boolean[A.length][B.length];
		minHeap.offer(start); // expand
		int count = 0;
		while (!minHeap.isEmpty()) {
			Entry cur = minHeap.poll();
			count++;
			if (count == k) {
				return cur.sum;
			}
			// generation
			if (cur.x + 1 < A.length && !visited[cur.x + 1][cur.y]) {
				minHeap.offer(new Entry(cur.x + 1, cur.y, A[cur.x + 1] + B[cur.y]));
				visited[cur.x + 1][cur.y] = true;
			}
			if (cur.y + 1 < B.length && !visited[cur.x][cur.y + 1]) {
				minHeap.offer(new Entry(cur.x, cur.y + 1, A[cur.x] + B[cur.y + 1]));
				visited[cur.x][cur.y + 1] = true;
			}
		}
		return 0;
	}
}
