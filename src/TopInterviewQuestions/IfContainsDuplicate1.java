package TopInterviewQuestions;

import java.util.Arrays;

/**
 * 
 * @author yifengguo 
 *         Given an array of integers, find if the array contains any
 *         duplicates. Your function should return true if any value appears at
 *         least twice in the array, and it should return false if every element
 *         is distinct.
 * 
 * 
 */
/*
 * time = O(nlogn)
 * space = O(1)
 */
public class IfContainsDuplicate1 {
	public boolean containsDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i + 1] == nums[i]) {
				return true;
			}
		}
		return false;
	}
}
