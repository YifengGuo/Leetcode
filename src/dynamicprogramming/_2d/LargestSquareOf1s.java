package dynamicprogramming._2d;
/**
 * 
 * @author yifengguo
 * Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

	Assumptions
	
	The given matrix is not null and guaranteed to be of size N * N, N >= 0
	Examples
	
	{ {0, 0, 0, 0},
	
	  {1, 1, 1, 1},
	
	  {0, 1, 1, 1},
	
	  {1, 0, 1, 1}}
	
	the largest square of 1s has length of 2
 */
/*
 * basic idea: dp
 * 	for a square matrix, there are n ^ 3 possibilities of different squares
 *  reason: 
 *  		idea 1: Cn_2 to determine the side-length, and there are other n possibilities to determine the whole square (another direction) like sliding window
 *  		idea 2: there are n^2 points to choose for the vertex of square, after determining the vertex, there are n choices for the side-length
 *  
 *  solution 1: brute force, for each square, check its sum == area time = O(n ^ 3 * n ^ 2) = O(n ^ 5)
 *  
 *  solution 2: DP
 *  	
 *  	base case:
 *  		1     ->  dp[0][0] = matrix[0][0]
 *  			  ->  dp[i][0] = matrix[i][0]
 *  			  ->  dp[0][j] = matrix[0][j]
 *          0   -> dp[i][j] = 0 (if current element is 0)
 *          induction rule: dp[i][j] represents the largest square which can built by matrix[i][j] as the right-bottom element of the square
 *  
 *  		1 1
 *  		1 1   -> dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1 if matirx[i][j] != 0
 *  						  = 0              if matrix[i][j] = 0
 *  		1 1 1
 *  		1 1 1
 *  		1 1 1  -> dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1 if matirx[i][j] != 0
 *                             = 0              if matrix[i][j] = 0
 *                             
 *          time = O(n ^ 2)
 *          space = O(n ^ 2)
 */
public class LargestSquareOf1s {
	public int largest(int[][] matrix) {
		if (matrix == null) {
			return -1;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int globalMax = 0;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = matrix[i][j];
				} else {
					if (matrix[i][j] == 0) { // reset to 0 if current element is 0
						dp[i][j] = 0;
					} else {
						dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1; // else grow from by induction rule
					}
				}
				globalMax = Math.max(globalMax, dp[i][j]);
			}
		}
		return globalMax;
	}
}
