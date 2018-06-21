package dynamicprogramming.challenging_leetcode;
/**
 * 
 * @author guoyifeng
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
 * which minimizes the sum of all numbers along its path.

	Note: You can only move either down or right at any point in time.
	
	Example:
	
	Input:
	[
	  [1,3,1],
	  [1,5,1],
	  [4,2,1]
	]
	Output: 7
	Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

/*
 * method 2: Dynamic Programming
 */
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        // dp[i][j] represents the minimum cost from start to current position
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}


/*
 * first method: simple dfs
 * however will exceed time limit when input grid is large 
 */
//public class MinimumPathSum {
//	public static final int[][] DIRS = { { 1, 0 }, { 0, 1 } };
//
//	public int minPathSum(int[][] grid) {
//		if (grid == null || grid.length == 0 || grid[0].length == 0) {
//			return 0;
//		}
//		int[] minCost = new int[1];
//		minCost[0] = Integer.MAX_VALUE;
//		boolean[][] visited = new boolean[grid.length][grid[0].length];
//		visited[0][0] = true;
//		helper(grid, visited, 0, 0, minCost, grid[0][0]);
//		return minCost[0];
//	}
//
//	private void helper(int[][] grid, boolean[][] visited, int x, int y, int[] minCost, int cost) {
//		// base case
//		if (x == grid.length - 1 && y == grid[0].length - 1) {
//			if (minCost[0] >= cost) {
//				minCost[0] = cost;
//			}
//			return;
//		}
//		// cost += grid[x][y];
//		for (int[] dir : DIRS) {
//			int nextX = x + dir[0];
//			int nextY = y + dir[1];
//			if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && !visited[nextX][nextY]) {
//				visited[nextX][nextY] = true;
//				cost += grid[nextX][nextY];
//				helper(grid, visited, nextX, nextY, minCost, cost);
//				cost -= grid[nextX][nextY];
//				visited[nextX][nextY] = false;
//			}
//		}
//	}
//}