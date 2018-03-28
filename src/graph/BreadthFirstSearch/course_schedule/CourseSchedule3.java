package graph.BreadthFirstSearch.course_schedule;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CourseSchedule3 {
	public int scheduleCourse(int[][] courses) {
		if (courses == null || courses.length == 0 || courses[0].length == 0) {
			return 0;
		}
		// sort the courses by its deadline
		Arrays.sort(courses, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		// pq is to store the courses from long last time to short last time
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
		int time = 0;
		for (int[] course : courses) {
			time += course[0];
			pq.offer(course[0]);
			if (time > course[1]) { // if time exceeds, drop the course with longest last time
				time -= pq.poll();
			}
		}
		return pq.size(); // by this strategy, we can choose as many as possible courses
		
	}
	// dfs is not suitable for this problem
//	public int scheduleCourse(int[][] courses) {
//		if (courses == null || courses.length == 0 || courses[0].length == 0) {
//			return 0;
//		}
//		int time = 0;
//		int level = 0;
//		int globalMax = Integer.MIN_VALUE;
//		List<Integer> res = new ArrayList<>();
//		boolean[] chosen = new boolean[courses.length];
////		for (int i = 0; i < courses.length; i++) {
////			chosen[i] = true;
////			dfs(res,courses, time, level, 0, chosen);
////			chosen[i] = false;
////		}
//		//chosen[0] = true;
//		dfs(res, courses, time, level, 0, chosen);
//		Collections.sort(res, Collections.reverseOrder());
//		return res.get(0);
//	}
//
//	private void dfs(List<Integer> res, int[][] courses, int time, int level, int i, boolean[] chosen) {
//		//System.out.println(time);
//		//System.out.println(level);
//		// base case
//		List<Integer> unchosenCourses = getUnchosenCourses(chosen);
//		if (time <= courses[i][1]) {
//			res.add(level);
//		}
//		if (unchosenCourses.size() == 0 || level == courses.length) {
//			return;
//		}
//		
//		for (int j : unchosenCourses) {
//			chosen[j] = true;
//			time += courses[j][0];
//			//if (time <= courses[j][1]) {
//				dfs(res, courses, time, level + 1, j, chosen);
//			//}
//			time -= courses[j][0];
//			chosen[j] = false;
//		}
//		return;
//	}
//
//	private List<Integer> getUnchosenCourses(boolean[] chosen) {
//		List<Integer> res = new ArrayList<>();
//		for (int i = 0; i < chosen.length; i++) {
//			if (chosen[i] == false) {
//				res.add(i);
//			}
//		}
////		for (int i : res) {
////			System.out.print(i + " ");
////		}
////		System.out.println();
//		return res;
//	}
	
	public static void main(String[] args) {
		int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
//		int[][] courses = {{1, 2}, {2, 3}};
		int res = new CourseSchedule3().scheduleCourse(courses);
		System.out.println(res);
	}
}
