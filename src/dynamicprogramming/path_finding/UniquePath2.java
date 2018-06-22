package dynamicprogramming.path_finding;

/**
 * 
 * @author guoyifeng
 *         A robot is located at the top-left corner of a m x n grid
 *         (marked 'Start' in the diagram below).
 * 
 *         The robot can only move either down or right at any point in time.
 *         The robot is trying to reach the bottom-right corner of the grid
 *         (marked 'Finish' in the diagram below).
 * 
 *         Now consider if some obstacles are added to the grids. How many
 *         unique paths would there be?
 * 
 * 
 * 
 *         An obstacle and empty space is marked as 1 and 0 respectively in the
 *         grid.
 * 
 *         Note: m and n will be at most 100.
 * 
 *         Example 1:
 * 
 *         Input: [ [0,0,0], [0,1,0], [0,0,0] ] Output: 2 
 *         Explanation: There is
 *         one obstacle in the middle of the 3x3 grid above. There are two ways
 *         to reach the bottom-right corner: 
 *         1. Right -> Right -> Down -> Down
 *         2. Down -> Down -> Right -> Right
 * 
 */
public class UniquePath2 {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid == null || m == 0 || n == 0) {
            return 0;
        }
        // sanity check for start and end 
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return 0;
        }
        // dp[i][j] represents total possible ways to get gird[i][j] from the start gird[0][0]
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) { // top line
                    if (grid[i][j - 1] == 1 || grid[i][j] == 1) { // left cell or itself is obstacle
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                } else if (j == 0 ) { // left-most line
                    if (grid[i - 1][j] == 1 || grid[i][j] == 1) { // top cell or itself is obstacle
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {   // middle part cells
                    if (grid[i][j] == 1) {  // obstacle cases
                        dp[i][j] = 0;
                    } else if (grid[i - 1][j] == 1) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (grid[i][j - 1] == 1) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
