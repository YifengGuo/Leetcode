package dp;
/**
 * 
 * @author @Yifeng
  Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
	
	For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */

/*
 * time = O(n)
 * space = O(1)
 */
public class MaxmiumSubarray {
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int preSum = nums[0];
		int globalMax = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (preSum + nums[i] >= nums[i]) {
				preSum += nums[i];
			} else {
				preSum = nums[i];
			}
			globalMax = Math.max(globalMax, preSum);
		}
		return globalMax;
	}
}
