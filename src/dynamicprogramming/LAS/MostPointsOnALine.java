package dynamicprogramming.LAS;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author yifengguo
  Given an array of 2D coordinates of points (all the coordinates are integers), 
  find the largest number of points that can be crossed by a single line in 2D space.

	Assumptions
	
	The given array is not null and it has at least 2 points
	Examples
	
	<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)


 */
class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/*
 *   basic idea
 1. use HashMap to record number of points which has same k
 2. special case: k does not exist (x1 == x2)
                  two points with same x and y are considered as different ones
                  
    time = O(n ^ 2)
    space = O(n ^ 2)
 */

public class MostPointsOnALine {
	public int most(Point[] points) {
		if (points == null) {
			return 0;
		}
		if (points.length <= 1) {
			return 0;
		}
		int globalMax = 0;
		for (int i = 0; i < points.length; i++) {
			Point seed = points[i];
			int same = 1; // x and y are both equal
			int sameX = 0; // on the same vertical line
			int most = 0;
			// key: slope, value: number of points
			Map<Double, Integer> map = new HashMap<>();
			for (int j = 0; j < points.length; j++) {
				if (i == j) {
					continue;
				}
				if (seed.x == points[j].x && seed.y == points[j].y) {
					same++;
				} else if (seed.x == points[j].x) {
					sameX++;
				}
				double slope = (seed.y - points[j].y) * 1.0 / (seed.x - points[j].x);
				if (!map.containsKey(slope)) {
					map.put(slope, 1);
				} else {
					map.put(slope, map.get(slope) + 1);
				}
				most = Math.max(most, map.get(slope));
			}
			most = Math.max(most, sameX) + same;
			globalMax = Math.max(most, globalMax);
		}
		return globalMax;
	}
}
