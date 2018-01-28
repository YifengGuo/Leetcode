package dynamicprogramming._2d;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yifengguo
	 * Given a matrix that contains integers, find the submatrix with the largest sum.
	
	Return the sum of the submatrix.
	
	Assumptions
	
	The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
	Examples
	
	{ {1, -2, -1, 4},
	
	  {1, -1,  1, 1},
	
	  {0, -1, -1, 1},
	
	  {0,  0,  1, 1} }
	
	the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
 */
/*
 * idea 1: brute force
 *         given a matrix, the total possibilities of submatrices are Cn_2 * Cn_2 = n ^ 4
 *                   Cn_2 to determine the top and bottom side
 *                   Cn_2 to determine the left and right side     ===> n ^ 4
 *                   
 *                   or there are n ^ 2 choices for top-left vertex and there are
 *                   m choices for length and n choices for width   ===> totally n ^ 4 choices
 *                   
 *         So step 1: run a for for for for (O(n ^ 4)) to determine the submatrix
 *            step 2: for current submatrix, run a for for loop to calculate its sum O(n ^ 2)
 *            
 *         so time for BF is O(n ^ 6)
 *         
 *         
 *         
 * idea 2: DP with prefix_sum
 *         everything is the same as BF except when we try to calculate the sum of current matrix,
 *         we can cache the prefix_sum on each entry of each row beforehand (implemented in QueryPresumGivenTwoIndices.java)
 *         so we calculate the sum of submatrix, simply for loop on each row and sum = M1[i...j] + M2[i...j] +...+ Mn[i...j]
 *         n is the length index of submatrix, i -> j is the width index
 *         
 *         This solution reduce one complexity ===> O(n ^ 5)
 *         
 *         
 * idea 3: 2-d prefixSum calculation O(n ^ 4)
 * 
 * 
 * idea 4: dp prefix_sum + largest subarray
 * 		   step 1: per-processing, for for loop to calculate the prefix_sum for each column, this is in order to
 *                 get sum of each column between chosen two rows in O(1) time
 *         step 2: for for loop to choose two rows from matrix (O(n ^ 2)) time 
 *                 step 2.1: use a SUM[] to store sum of each column between chosen two rows
 *                 step 2.2: apply SUM[] as input, call largest subarray function to get largest subarray in SUM
 *                           the value of largest subarray in SUM[] is the result of this problem
 */
public class LargestSubMatrixSum {
	public int largest(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		// step 1: calculate the prefix_sum array of each column in matrix
		List<List<Integer>> prefixSumCollection = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			List<Integer> prefix_sum = calculatePrefixSum(matrix, i);
			prefixSumCollection.add(prefix_sum);
		}
		
		// step 2: 
		int[] SUM = new int[n];
		int globalMax = Integer.MIN_VALUE;
		for (int i = 0; i < m; i++) { // O(n ^ 2) to choose two rows in matrix
			for (int j = m - 1; j >= i; j--) {
				for (int k = 0; k < n; k++) { // query partial prefix sum on each column and store them in SUM[]
					int query_sum = queryPrefixSum(matrix, prefixSumCollection.get(k), i, j, k); // get prefix_sum between row i and row j using queryPresumGivenIndex tricks
					SUM[k] = query_sum;
				}
				globalMax = Math.max(globalMax, getLargestSubArray(SUM)); //try to update globalMax on each possible SUM
			}
		}
		return globalMax;
	}
	
	private List<Integer> calculatePrefixSum(int[][] matrix, int columnIdx) { // calculate prefix_sum on each column
		List<Integer> res = new ArrayList<>();
		int prefix_sum = 0;
		for (int j = 0; j < matrix.length; j++) {
			prefix_sum += matrix[j][columnIdx];
			res.add(prefix_sum);
		}
		return res;
	}
	
	private int queryPrefixSum(int[][] matrix, List<Integer> prefix_sum, int i, int j, int k) {
		return prefix_sum.get(j) - prefix_sum.get(i) + matrix[i][k];
	}
	
	private int getLargestSubArray(int[] SUM) {
		int preSum = SUM[0];
		int globalMax = SUM[0];
		for (int i = 1; i < SUM.length; i++) {
			if (preSum + SUM[i] > 0) {
				preSum += SUM[i];
			} else {
				preSum = SUM[i];
			}
			globalMax = Math.max(globalMax, preSum);
		}
		return globalMax;
	}
}





























