package dynamicprogramming._2d;
/*
 * solution 1: bf
 * 	for for loop (n ^ 2 for each entry)
 * 		for each possible [i][j] as its top left corner, try edge == k (1<= k <= n)
 *      to see whether it is a valid square with edge == k
 *      	remember, for each k, we need to run 4 for loops to check all 4 edges
 *    
 *     total time for solution 1: O(n ^ 2 * n * 4n) = O(n ^ 4)
 *     
 *     
 * Solution 2: DP
 * 	Step 1: pre-processing, fill in two 2D matrices
 * 				M1[n][n] (right -> left), M2[n][n] (bottom -> up)
 *          	same tricks in longest cross of 1s, to find the longest consecutive
 *          	1s in two directions (because it is a square, only two direction edges can determine)
 *          	treat matrix[i][j] as the top left corner for the subsquare
 *          	M[i][j] represents the long the longest consecutive 1s from right to left
 *              and from bottom to top 
 *              Then find the minimum among two M[][] and update globalMax each time
 *              
 *        	 1 1 1 0
 *           1 0 1 1
 *           1 1 1 0
 *           0 1 0 1
 *              
 *      totoal time 
 *      	for for loop (for each entry) O(n ^ 2)
 *      	check each possible edge between 1 and n by each entry as top left corner O(n)
 *          check if possible edge can build a valid subsquare (O(4)) = O(1)
 *          
 *          ====> O(n ^ 3)
 */
public class LargestSubsquareSurroundingBy1s {
	public int largest(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int globalMax = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] M1 = new int[m][n];
		int[][] M2 = new int[m][n];
		
		// fill in the matrix from right to left to cache longest consecutive 1s by the end of current entry
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
				M1[i][j] = Math.max(M1[i][j], preSum);
			}
		}
		
		// fill in the matrix from bottom to top to cache longest consecutive 1s by the end of current entry
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
				M2[j][i] = Math.max(M2[j][i], preSum);
			}
		}
		
		int maxLen = Math.min(m, n);
		int[][] M = new int[m][n];
		// for for loop to check each entry in the matrix as the top left entry of subsquare
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// try possible side length from 1 to maxLen
				for (int k = 1; k < maxLen; k++) {
					if (i + k < m && j + k < n) { // sanity check
						// M1[i][j] is to check top side of subsquare
						// M2[i][j] is to check left side of subsquare
						// M1[i + k][j] is to check bottom side of subsquare
						// M2[i][j + k] is to check right side of subsquare
						// time = O(4) = O(1)
						M[i][j] = Math.min(Math.min(Math.min(M1[i][j], M2[i][j]),M1[i + k][j]),M2[i][j + k]);
						globalMax = Math.max(globalMax, M[i][j]);
					}
				}
			}
		}
		
		return globalMax;
 	}
	
	public static void main(String[] args) {
		// int[][] matrix = {{1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 0, 1, 0}, {1, 1, 1, 1, 1}, {1, 1, 1, 0, 0}};
//		int[][] matrix = { { 1, 1, 1, 0 }, { 1, 0, 1, 1 }, { 1, 1, 1, 0 }, { 0, 1, 0, 1 } };
		int[][] matrix = { { 1, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 0, 1 }, { 1, 1, 1, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 1, 0 } };
		int res = new LargestSubsquareSurroundingBy1s().largest(matrix);
		System.out.println(res);
	}
}
