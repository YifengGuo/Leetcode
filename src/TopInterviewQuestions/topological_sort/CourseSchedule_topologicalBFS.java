package TopInterviewQuestions.topological_sort;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author guoyifeng 
 *         There are a total of n courses you have to take, labeled
 *         from 0 to n - 1.
 * 
 *         Some courses may have prerequisites, for example to take course 0 you
 *         have to first take course 1, which is expressed as a pair: [0,1]
 * 
 *         Given the total number of courses and a list of prerequisite pairs,
 *         is it possible for you to finish all courses?
 * 
 *         For example:
 * 
 *         2, [[1,0]] There are a total of 2 courses to take. To take course 1
 *         you should have finished course 0. So it is possible.
 * 
 *         2, [[1,0],[0,1]] There are a total of 2 courses to take. To take
 *         course 1 you should have finished course 0, and to take course 0 you
 *         should also have finished course 1. So it is impossible.
 * 
 *         Note: The input prerequisites is a graph represented by a list of
 *         edges, not adjacency matrices. Read more about how a graph is
 *         represented. You may assume that there are no duplicate edges in the
 *         input prerequisites. click to show more hints.
 * 
 *         Hints: This problem is equivalent to finding if a cycle exists in a
 *         directed graph. If a cycle exists, no topological ordering exists and
 *         therefore it will be impossible to take all courses. Topological Sort
 *         via DFS - A great video tutorial (21 minutes) on Coursera explaining
 *         the basic concepts of Topological Sort. Topological sort could also
 *         be done via BFS.
 */
/*
 * basic idea: BFS + topological sort property
 */
public class CourseSchedule_topologicalBFS {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0) {
			return true;
		}
		int[] indegree = new int[numCourses]; // record indegree of course i
		int index = 0;
		for (int i = 0; i < prerequisites.length; i++) {
			indegree[prerequisites[i][0]]++; // prerequisites[i][0] is post-fix course of prerequisites[i][1]
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) { // safely add those without prerequisite courses into queue and topological order
				queue.offer(i);
				index++;
			}
		}
		while (!queue.isEmpty()) {
			int pre = queue.poll();
			for (int i = 0; i < prerequisites.length; i++) {
				if (prerequisites[i][1] == pre) {
					indegree[prerequisites[i][0]]--; // decrement by 1 for one of its prerequisite has been into queue
					if (indegree[prerequisites[i][0]] == 0) { // if all prerequisites are satisfied, add it into queue and topologial order
						index++;
						queue.offer(prerequisites[i][0]); // add it to queue for future process of its post-fix courses
					}
				}
			}
		}
		return index == numCourses; // if index(topological order) == numCourses, meaning we have valid topological order of graph
									// otherwise, the graph has cycle and cannot have topological order
									// we can impossibly find start node which has 0 indegree, so index is always 0
	}
}
