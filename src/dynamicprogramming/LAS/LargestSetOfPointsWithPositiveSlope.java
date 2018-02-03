package dynamicprogramming.LAS;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author yifengguo
  Given an array of 2D coordinates of points (all the coordinates are integers), 
  find the largest number of points that can form a set such that any pair of points 
  in the set can form a line with positive slope. Return the size of such a maximal set.

	Assumptions
	
	The given array is not null
	Note: if there does not even exist 2 points can form a line with positive slope, should return 0.
	Examples
	
	<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
 */
/*
 * time = O(n ^ 2)
 * space = O(n)
 */
public class LargestSetOfPointsWithPositiveSlope {
	public int largest(Point[] points) {
		if (points == null || points.length <= 1) {
			return 0;
		}
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if (p1.x == p2.x) {
					return 0;
				}
				return p1.x < p2.x ? -1 : 1;
			}
		});
		// M[i] represents the largest set from index 0 to index i(exclusive) without considering duplicate
		int M[] = new int[points.length];
		Arrays.fill(M, 0); // initialize each entry as 0
		int globalMax = 0;
		for (int i = 1; i < M.length; i++) {
			int same = 0; // number of duplicate point with points[i]
			int most = 0; // largest number of positive slope lines ended at points[i]
			for (int j = 0; j < i; j++) {
				if (points[i].x == points[j].x && points[i].y == points[j].y) {
					same++;
				} else if (points[j].y < points[i].y) {
					if (M[j] == 0) { // if point[j] is a single point which has no historical built line
						M[i] = Math.max(2, M[i]);
					} else {
						M[i] = Math.max(M[j] + 1, M[i]);
					}
				}
				most = Math.max(M[i], most); // update most for there are different lines ended at points[i]
				                             // but starting from different points
			}
			if (most != 0) { // if point[i] can built a valid line, we need add all duplicate points[i] to this line
				most += same;
			}

			globalMax = Math.max(globalMax, most); // update globalMax
		}
		return globalMax;
	}
}
