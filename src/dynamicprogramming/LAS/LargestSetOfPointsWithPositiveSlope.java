package dynamicprogramming.LAS;

import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {
	public int largest(Point[] points) {
		// Write your solution here
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
		int M[] = new int[points.length];
		Arrays.fill(M, 0);
		int globalMax = 0;
		for (int i = 1; i < M.length; i++) {
			int same = 0;
			int most = 0;
			for (int j = 0; j < i; j++) {
				if (points[i].x == points[j].x && points[i].y == points[j].y) {
					same++;
				} else if (points[j].y < points[i].y) {
					if (M[j] == 0) {
						M[i] = 2;
					} else {
						M[i] = Math.max(M[j] + 1, M[i]);
					}
				}
				if (M[i] != 0) {
					M[i] += same;
				}
				most = Math.max(M[i], most);
			}
			globalMax = most != 0 ? Math.max(globalMax, most) + same : Math.max(globalMax, most);
		}
		return globalMax;
	}
}
