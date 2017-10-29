package TopInterviewQuestions.topological_sort;

import java.util.ArrayList;
import java.util.List;

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
 *
 */
/*
 * basic idea: topological by dfs
 * 			   find all the edges to adjacent nodes of each node via 2d array
 *             run dfs on each node and determine if there is any cycle exsited
 *             among edges
 * time = O(V + E)
 * space = O(V + E)
 */
public class CourseSchedule_topologicalDFS {
    public boolean canFinish(int numCourses, int[][]prerequisites) {
		if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
			return true;
		}
		boolean[] visited = new boolean[numCourses]; // record current course has been visited or not
		@SuppressWarnings("unchecked")
		// each course as start node of a graph, and its adjacent nodes 
		// are stored by an ArrayList
		List<Integer>[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<>();
		}
		// prerequisites.length means the num of edges in the complete graph
		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}
		// now graph[i] is the list which contains all the adjacent nodes of courseId == i
		// run dfs
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i)) {
				return false;
			}
		}
		return true;
    }
	
	// for any graph[i], if it is acyclic, all the adjacent nodes will be accessed only once
    // else, it is not a DAG
	public boolean dfs(List<Integer>[] graph, boolean[] visited, int courseId) {
		if (visited[courseId] == true) {
			return false;
		}
		visited[courseId] = true;
		for (int i = 0; i < graph[courseId].size(); i++) {
			if (!dfs(graph, visited, (int)graph[courseId].get(i))) {
                return false;
            }
		}
		visited[courseId] = false; // set current course as unvisited for next course to traverse
		return true;
	}

	public static void main(String[] args) {
		int  numCourses = 2;
		int[][] pre = new int[][]{{1,0},{0,1}};
		System.out.println(new CourseSchedule_topologicalDFS().canFinish(numCourses, pre));
	}
}
