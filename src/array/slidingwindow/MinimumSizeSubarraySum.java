package array.slidingwindow;

/**
 * 
 * @author guoyifeng 
 * 	       Given an array of n positive integers and a positive
 *         integer s, find the minimal length of a contiguous subarray of which
 *         the sum ≥ s. If there isn't one, return 0 instead.
 * 
 *         Example:
 * 
 *         Input: s = 7, nums = [2,3,1,2,4,3] Output: 2 
 *         Explanation: the
 *         subarray [4,3] has the minimal length under the problem constraint.
 */
// 2 3 1 2 4 3
//         f
// s
/*
 * basic idea: when sum meets s, then try to remove from the head to find a smaller length
 * time = O(n)
 * space = O(1)
 */
public class MinimumSizeSubarraySum {
	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length == 0 || s <= 0) {
			return 0;
		}
		int fast = 0, slow = 0, sum = 0, min = Integer.MAX_VALUE;
		while (fast < nums.length) {
			sum += nums[fast++];

			while (sum >= s) {
				min = Math.min(min, fast - slow);
				sum -= nums[slow++];
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
}