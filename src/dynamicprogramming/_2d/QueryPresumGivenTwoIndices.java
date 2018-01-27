package dynamicprogramming._2d;
/**
 * 
 * @author yifengguo
 * Given an integer array A[n], there are repeated queries asking for the sum between A[i] and
 * A[j], then what should we do in order to speed up the query  (i <= j)
 */
/*
 * basic idea: DP
 * 				 use a 2d matrix to cache all the query possibilities M[i][j]
 *               represents the sum between i and j
 *               
 *                3, 2, 1, 4, 5, 3, 2, 6
 *                3  5  6  10 15 18 20 26
 */
public class QueryPresumGivenTwoIndices {
	/*
	 * improved solution: 1d array to cache prefix_sum
	 * 	M[i] represents the prefix_sum from index 0 to index i
	 * 	so sum between i and j (i <= j) = M[j] - M[i] + array[i]
	 * 	time = O(n)
	 * 	space = O(n)
	 */
	public int getSum1(int[] array, int a, int b) {
		if (array == null || array.length == 0 || a >= b) {
			return 0;
		}
		int[] M = new int[array.length];
		int prefix_sum = 0;
		for (int i = 0; i < array.length; i++) {
			prefix_sum += array[i];
			M[i] = prefix_sum;
		}
		return M[b] - M[a] + array[a];
	}
	
	
	/*
	 * solution 1: 2d dp matrix
	 * 	time = O(n ^ 2)
	 * 	space = O(n ^ 2)
	 * 	when calculate sum between given two indices, 
	 *  it is usually set end first, and then linear scan
	 *  backwards and calculate presum one step by one step
	 */
	public int getSum2(int[] array, int a, int b) {
		if (array == null || array.length == 0 || a >= b) {
			return 0;
		}
		int[][] M = new int[array.length][array.length];
		for (int i = 0; i < array.length; i++) { // sum ended at index i
			int preSum = array[i];
			for (int j = i - 1; j >= 0; j--) { // linear scan to back
				preSum += array[j];
				M[j][i] = preSum;
			}
		}
		return M[a][b];
	}
	
	public static void main(String[] args) {
		int[] array = { 3, 2, 1, 4, 5, 3, 2, 6 };
		int a = 0;
		int b = array.length - 1;
		int sum = new QueryPresumGivenTwoIndices().getSum1(array, a, b);
		System.out.println(sum);
	}
}
