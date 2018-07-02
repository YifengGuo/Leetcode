package graph.DepthFirstSearch.leetcode;
/**
 * 
 * @author guoyifeng
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally 
 * or vertically. You may assume all four edges of the grid are all surrounded by water.

	Example 1:
	
	Input:
	11110
	11010
	11000
	00000
	
	Output: 1
	Example 2:
	
	Input:
	11000
	11000
	00100
	00011
	
	Output: 3
 */
public class NumberOfIslands {
	public static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public int numIslands(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '0') {
					continue;
				} else if (!visited[i][j]) {
					visited[i][j] = true;
					if (dfs(matrix, i, j, visited)) {
						count++;
					}
				} else {
					continue;
				}
			}
		}
		return count;
	}
	
	/*
	 * dfs to traverse an island and mark all the traversed part visited
	 */
	private boolean dfs(char[][] matrix, int i, int j, boolean[][] visited) {
		for (int[] dir : DIRS) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];
			if (nextX >= 0 && nextX < matrix.length && nextY >= 0 
					&& nextY < matrix[0].length && !visited[nextX][nextY]
					&& matrix[nextX][nextY] == '1') {
				visited[nextX][nextY] = true;
				dfs(matrix, nextX, nextY, visited);
			}
		}
		return true;
	}
}
