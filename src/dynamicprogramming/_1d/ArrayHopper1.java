package dynamicprogramming._1d;
/**
 * 
 * @author yifengguo
 	Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
 	A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). 
 	Determine if you are able to reach the last index.

	Assumptions
	
	The given array is not null and has length of at least 1.
	Examples
	
	{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)
	
	{2, 1, 1, 0, 2}, we are not able to reach the end of array


 */
public class ArrayHopper1 {
	// method 3: greedy algorithm
	public static boolean canJump3(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		int reachable = 0; // furtherest position can be reached by previous positions
		for (int i = 0; i < array.length; i++) {
			if (i > reachable) {  // current position i cannot be reached by any way from previous positions
				return false;
			}
			reachable = Math.max(reachable, array[i] + i);
		}
		return true;
	}
	//method 1: dp
	// 1 3 2 0 3
	//     i
	//   j
	// dp[0] = true
	// dp[i] means at if ith position of array can be reached or not
	public static boolean canJump1(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		boolean[] dp = new boolean[array.length];
		dp[0] = true;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				// to determine if we can go to i from j
				// 1. j is reachable, 2. from j to i is feasible
				if (dp[j] && array[j] + j >= i) {
				  dp[i] = true;
				}
			}
		}
		return dp[array.length - 1];
	}
	
	
	
	
	
	// method 2: recursion
	public static boolean flag;
	public static boolean canJump2(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		helper(array, 0);
		return flag;
	}

	public static void helper(int[] array, int cur_idx) {
		// base case
		if (cur_idx >= array.length) {
			return;
		}
		if (cur_idx == array.length - 1) {
			flag = true;
			return;
		}
		for (int i = 1; i <= array[cur_idx]; i++) {
			helper(array, cur_idx + i);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{0, 1};
		System.out.println(canJump2(arr));
	}
}

