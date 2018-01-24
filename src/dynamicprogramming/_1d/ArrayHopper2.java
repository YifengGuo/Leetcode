package dynamicprogramming._1d;
/**
 * 
 * @author yifengguo
  Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
  A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). 
  Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.

Assumptions

The given array is not null and has length of at least 1.
Examples

{3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)

{2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
 */
/*
 * basic idea: dp
 * dp[0] = 0
 * dp[i] means minimum move to reach ith element in the array
 * 
 * time = O(n ^ 2)
 * space = O(n)
 */
public class ArrayHopper2 {
	public int minJump(int[] array) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int[] dp = new int[array.length];
		// base case
		dp[0] = 0;
		for (int i = 1; i < array.length; i++) {
			dp[i] = -1; // initialize dp[i] as -1
			for (int j = 0; j < i; j++) {
				if (dp[j] != -1 && array[j] + j >= i) { // if jth is reachable and ith can be reached from j
					if (dp[i] == -1 || dp[j] + 1 < dp[i]) { // if dp[i] has not been accessed or current dp[j] + 1 is smaller, update dp[i]
						dp[i] = dp[j] + 1;
					}
				}
			}
		}
		return dp[array.length - 1];
	}
}
