package dynamicprogramming.challenging_leetcode;

import java.util.Arrays;
/**
 * 
 * @author guoyifeng
 * Given an integer array with all positive numbers and no duplicates, 
 * find the number of possible combinations that add up to a positive integer target.

	Example:
	
	nums = [1, 2, 3]
	target = 4
	
	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)
	
	Note that different sequences are counted as different combinations.
	
	Therefore the output is 7.
	Follow up:
	What if negative numbers are allowed in the given array?
	How does it change the problem?
	What limitation we need to add to the question to allow negative numbers?
	
	Credits:
	Special thanks to @pbrother for adding this problem and creating all test cases.
 */

/*
 * demo:
 *     given nums = {1, 2, 3}
 *     dp[1] = 1    {1}
 *     dp[2] = 2    {1, 1} , {2}
 *     dp[3] = 3    {1, 1, 1}, {1, 2}, {2, 1}, {3}
 *     dp[4] = dp[3] + dp[2] + dp[1] = 7
 *     
 *     so dp[target] == dp[target - 1] + dp[target - 2] + ... + dp[1]
 *     
 *     time = O(target * n)
 *     space = O(target)
 */
public class CombinationSum4 {
    // dp[i] represents the sum of combinations given target == i
    private int[] dp;
    public int combinationSum4(int[] nums, int target) {
        if (nums == null ||  nums.length == 0 || target <= 0) {
            return 0;
        }
        dp = new int[target + 1];
        // use -1 to represent that current target dp[i] has not been calculated before
        Arrays.fill(dp, -1);
        // base case for i == 0
        dp[0] = 1;
        helper(nums, target);
        return dp[target];
    }
    
    // recursion function with dp memorization to calculate the sum of combination given target
    private int helper(int[] nums, int target) {
        // base case
        // prune if current target has been cached in the memorization
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res; // cache the dp[i] for current target on recursion layer
        return res;
    }
}