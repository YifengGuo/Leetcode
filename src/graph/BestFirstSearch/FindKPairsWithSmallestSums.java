package graph.BestFirstSearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * @author guoyifeng
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

	Define a pair (u,v) which consists of one element from the first array and one element 
	from the second array.
	
	Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
	
	Example 1:
	Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
	
	Return: [1,2],[1,4],[1,6]
	
	The first 3 pairs are returned from the sequence:
	[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
	Example 2:
	Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
	
	Return: [1,1],[1,1]
	
	The first 2 pairs are returned from the sequence:
	[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
	Example 3:
	Given nums1 = [1,2], nums2 = [3],  k = 3 
	
	Return: [1,3],[2,3]
	
	All possible pairs are returned from the sequence:
	[1,3],[2,3]
 */
/*
 * basic idea: best first search
 *             pairs are composed by choosing one element from nums1 and 
 *             choosing another from nums2
 *             so actually we are comparing the sum of pair
 *             this problem is converted to a best search problem
 *             and to represent pair's info in detailed, we apply 
 *             an auxiliary class called point
 *             
 *             and we need to also pay attention to k and m * n
 *             relationship
 *             
 *  1. initial state: point[0][0]  (start node which needs to offer into heap at very beginning)
 *  
 *  2. expansion / generation rule:
 *                    2.1 expand point[i][j]  (poll out from heap)
 *                    2.2 generate matrix[i + 1][j]
 *                                 matrix[i][j + 1]   if it is not out of bound and not visited
 *  3. termination condition: meet the kth element polled out from heap or heap is empty
 *  
 *  time = O(n log k)
 *  space = O(n)
 */
public class FindKPairsWithSmallestSums {
	class Point {
		int x;
		int y;
		int value;

		public Point(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> res = new ArrayList<>();
		if (nums1 == null || nums2 == null || k <= 0) {
			return null;
		}
		if (nums1.length == 0 || nums2.length == 0) {
			return res;
		}
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if (p1.value == p2.value) {
					return 0;
				}
				return p1.value < p2.value ? -1 : 1;
			}
		});
		boolean[][] visit = new boolean[nums1.length][nums2.length];
		pq.offer(new Point(0, 0, nums1[0] + nums2[0]));
		visit[0][0] = true;
		List<Point> aux = new ArrayList<>();
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			aux.add(cur);
			if (aux.size() == k) {
				break;
			}
			// move nums1 pointer
			if (cur.x + 1 < nums1.length && !visit[cur.x + 1][cur.y]) {
				pq.offer(new Point(cur.x + 1, cur.y, nums1[cur.x + 1] + nums2[cur.y]));
				visit[cur.x + 1][cur.y] = true;
			}
			// move nums2 pointer
			if (cur.y + 1 < nums2.length && !visit[cur.x][cur.y + 1]) {
				pq.offer(new Point(cur.x, cur.y + 1, nums1[cur.x] + nums2[cur.y + 1]));
				visit[cur.x][cur.y + 1] = true;
			}
		}
		int limit = k >= nums1.length * nums2.length ? nums1.length * nums2.length : k;
		for (int i = 0; i < limit; i++) {
			int[] pair = new int[2];
			pair[0] = nums1[aux.get(i).x];
			pair[1] = nums2[aux.get(i).y];
			res.add(pair);
		}
		return res;
	}
}
