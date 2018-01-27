package dynamicprogramming._2d;
/**
 * 
 * @author yifengguo
  Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, 
  with the same arm lengths and the four arms joining at the central point.

	Return the arm length of the largest cross.
	
	Assumptions
	
	The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
	Examples
	
	{ {0, 0, 0, 0},
	
	  {1, 1, 1, 1},
	
	  {0, 1, 1, 1},
	
	  {1, 0, 1, 1} }
	
	the largest cross of 1s has arm length 2.
 */
/*
 * idea 1: for each entry in the matrix, run 4 for loops (on each of four directions), to find 
 *         minimum value of consecutive 1s. And then find globalMax length
 *         time = O(2n * n^2) = O(n ^ 3)
 *         space = O(1)
 *         
 * idea 2: DP
 * 			recall classical Longest Consecutive 1s
 *    
 *           0 1 0 0
 *           1 1 1 1
 *           0 1 0 0
 *           0 1 0 0
 *           
 *           
 *           
 *           
 *           use another 2D matrix M1[i][j] to cache the longest consecutive 1s from left to right on each entry
 *           
 *        M1:
 *        	 0 1 0 0
 *           1 2 3 4
 *           0 1 0 0 
 *           0 1 0 0 
 *           
 *           
 *           use another 2D matrix M2[i][j] to cache the longest consecutive 1s from right to left on each entry
 *           
 *           
 *        M2:
 *        	 0 1 0 0
 *       	 4 3 2 1
 *           0 1 0 0
 *           0 1 0 0
 *           
 *   		use another 2D matrix M3[i][j] to cache the longest consecutive 1s from top to bottom on each entry
 *           
 *           
 *        M3:
 *        	 0 1 0 0
 *       	 1 2 1 1
 *           0 3 0 0
 *           0 4 0 0
 *           
 * *   		use another 2D matrix M4[i][j] to cache the longest consecutive 1s from bottom to top on each entry
 *           
 *           
 *        M4:
 *        	 0 4 0 0
 *       	 1 3 1 1
 *           0 2 0 0
 *           0 1 0 0
 *           
 *     And for each entry in the M1, M2, M3 and M4, calculate the size of the largest cross centered at [i][j]
 *     M[i][j] = min(M1[i][j] M2[i][j], M3[i][j], M4[i][j)
 *           
 *     time = O(n ^ 2)
 *     space = O(n ^ 2)
 */
public class LongestCrossOf1s {
	public int largest(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;

		// calculate longest consecutive 1s from left to right
		int[][] M1 = new int[m][n];
		for (int i = 0; i < m; i++) {
			int preSum = 0;
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 1) {
					if (j == 0 || matrix[i][j - 1] == 0) {
						preSum = 1;
					} else {
						preSum++;
					}
				}
				M1[i][j] = Math.max(preSum, M1[i][j]);
			}
		}

		// calculate longest consecutive 1s from right to left
		int[][] M2 = new int[m][n];
		for (int i = 0; i < m; i++) {
			int preSum = 0;
			for (int j = n - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					if (j == n - 1 || matrix[i][j + 1] == 0) {
						preSum = 1;
					} else {
						preSum++;
					}
				}
				M2[i][j] = Math.max(preSum, M2[i][j]);
			}
		}

		// calculate longest consecutive 1s from top to bottom
		int[][] M3 = new int[m][n];
		for (int i = 0; i < n; i++) {
			int preSum = 0;
			for (int j = 0; j < m; j++) {
				if (matrix[j][i] == 1) {
					if (j == 0 || matrix[j - 1][i] == 0) {
						preSum = 1;
					} else {
						preSum++;
					}
				}
				M3[j][i] = Math.max(preSum, M3[j][i]);
			}
		}

		// calculate longest consecutive 1s from top to bottom
		int[][] M4 = new int[m][n];
		for (int i = 0; i < n; i++) {
			int preSum = 0;
			for (int j = m - 1; j >= 0; j--) {
				if (matrix[j][i] == 1) {
					if (j == m - 1 || matrix[j + 1][i] == 0) {
						preSum = 1;
					} else {
						preSum++;
					}
				}
				M4[j][i] = Math.max(preSum, M4[j][i]);
			}
		}

		int globalMax = 0;
		int[][] M = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				M[i][j] = Math.min(Math.min(Math.min(M1[i][j], M2[i][j]), M3[i][j]), M4[i][j]);
				globalMax = Math.max(globalMax, M[i][j]);
			}
		}
		return globalMax;
	}
}

