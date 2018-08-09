package math;

import java.util.Arrays;
/**
 * 
 * @author guoyifeng
 * Given an array nums of n integers where n > 1,  
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

	Example:
	
	Input:  [1,2,3,4]
	Output: [24,12,8,6]
	Note: Please solve it without division and in O(n).
	
	Follow up:
	Could you solve it with constant space complexity? (The output array does not count as extra 
	space for the purpose of space complexity analysis.)
 */
/*
 * time = O(n)
 * space = O(1)
 */
public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new int[] {};
		}
		int[] res = new int[nums.length];

		int count = countZero(nums);
		if (count >= 2) {
			return res;
		}

		int product = 1;
		int product_zero = 1;
		for (int i : nums) {
			product *= i;
			if (i != 0) {
				product_zero *= i;
			}
		}
		Arrays.fill(res, product);
		for (int i = 0; i < res.length; i++) {
			if (nums[i] == 0) {
				res[i] = product_zero;
			} else {
				res[i] = res[i] / nums[i];
			}
		}
		return res;
	}

	private int countZero(int[] nums) {
		int count = 0;
		for (int i : nums) {
			if (i == 0)
				count++;
		}
		return count;
	}
}
