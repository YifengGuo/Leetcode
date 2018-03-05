package graph.DepthFirstSearch.classical;

public class DisjointWhiteObjects {
	public static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public int whiteObjects(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 1) {
					continue;
				} else {
					if (!visited[i][j]) {
						visited[i][j] = true;
						if (dfs(matrix, i, j, visited)) {
							count++;
						}
					} else {
						continue;
					}
				}
			}
		}
		return count;
	}
	
	// no base case for this recursion
	// what we need is to traverse all the 0 elements in the matrix and set corresponding visited[i][j] to true
	// and the count of dfs invoke times is the result
	private boolean dfs(int[][] matrix, int i, int j, boolean[][] visited) {
		for (int[] dir : DIRS) {
			int nextX = i + dir[0];
			int nextY = j + dir[1];
			if (nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length && !visited[nextX][nextY]
					&& matrix[nextX][nextY] == 0) {
				visited[nextX][nextY] = true;
				dfs(matrix, nextX, nextY, visited);
			}
		}
		return true;
	}
}
