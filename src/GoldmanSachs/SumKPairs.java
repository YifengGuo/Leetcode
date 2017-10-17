package GoldmanSachs;

import java.util.Arrays;
/**
 * 
 * @author guoyifeng
 *	find number of pairs whose sum is k in the given array
 */
/*
 * basic idea: similar to two sum
 * time = O(nlogn) for sort process
 * space = O(1)
 */
public class SumKPairs {
	public int pairs(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int count = 0;
		Arrays.sort(nums);
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			if (nums[left] + nums[right] == k) {
				count++;
				left++;
				right--;
			} else if (nums[left] + nums[right] < k) {
				left++;
			} else {
				right--;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{4,1,6,12,7,2,8,13,21,5,3,6};
		int k = 9;
		int count = new SumKPairs().pairs(nums, k);
		System.out.println(count);
	}
}
