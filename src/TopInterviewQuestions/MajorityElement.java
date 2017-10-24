package TopInterviewQuestions;

import java.util.Arrays;

/**
 * 
 * @author guoyifeng 
 * 		   Given an array of size n, find the majority element. The
 *         majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * 
 *         You may assume that the array is non-empty and the majority element
 *         always exist in the array.
 */
public class MajorityElement {
	/*
	 * better solution: Time = O(n) for one pass on array
	 * 					Space = O(1)
	 */
	public int majorityElement(int[] nums) {
		if (nums == null || nums.length == 0) {
            return -1;
        }
		int count = 1;  // current major occurring times
						// initially we have a major nums[0] occurring 1 times
		int major = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (count == 0) { // flop major to current nums[i]
				major = nums[i];
				count++;
			} else if (major == nums[i]) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}
	

	
	/*
	 * solution 1: Time = O(nlogn) for sort the array
	 * 			   Space = O(1)
	 */
    public int majorityElementSol2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
