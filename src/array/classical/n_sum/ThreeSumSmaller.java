package array.classical.n_sum;

import java.util.Arrays;

/**
 * 
 * @author guoyifeng
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k 
 * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

	Example:
	
	Input: nums = [-2,0,1,3], and target = 2
	Output: 2 
	Explanation: Because there are two triplets which sums are less than 2:
	             [-2,0,1]
	             [-2,0,3]
	Follow up: Could you solve it in O(n2) runtime?
 */
/*
 * time = O(n ^ 2)
 */
public class ThreeSumSmaller {
	public int threeSumSmaller(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			return 0;
		}
		if (nums.length == 3) {
			int sum = 0;
			for (int i : nums) {
				sum += i;
			}
			return sum < target ? 1 : 0;
		}
		int res = 0;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int tmp = nums[right] + nums[left];
				if (target > tmp + nums[i]) {
					res += right - left; // core part which is to get all combinations with O(n) time
					left++;              // [-2 -1 0 1 2]  target = 4, for [-1, 0, 1, 2]
				} else {                 // if -1 and 2 cannot work, then [-1, 1], [-1,0] can also not work -> right - left
					right--;
				}
			}
		}
		return res;
	}
}