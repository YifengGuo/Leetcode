package array.duplicate_distance;

import java.util.TreeSet;

/**
 * 
 * @author guoyifeng
 * Given an array of integers, find out whether there are two distinct indices i and j in the 
 * array such that the absolute difference between nums[i] and nums[j] is at most t and the 
 * absolute difference between i and j is at most k.

	Example 1:
	
	Input: nums = [1,2,3,1], k = 3, t = 0
	Output: true
	Example 2:
	
	Input: nums = [1,0,1,1], k = 1, t = 2
	Output: true
	Example 3:
	
	Input: nums = [1,5,9,1,5,9], k = 2, t = 3
	Output: false
 */
/*
 *  demo:
 *             treeset:                        x    x        x x x x x x x x     x 
 *                                               ceiling                         floor
 *             new element comes                 
 *             find floor and ceiling
 *             whose range is [-t, t]
 *             
 *             if nums[idx] can be in this range, it means "t" can be satisfied
 *             
 *             time = O(n * log(k))
 *             space = O(k)
 */
public class ContainsDuplicate3 {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0)
			return false;
		TreeSet<Long> treeset = new TreeSet<>();

		for (int idx = 0; idx < nums.length; idx++) {
			Long floor = treeset.floor((long) nums[idx] + t); // greatest element <= nums[idx] + t
			Long ceiling = treeset.ceiling((long) nums[idx] - t); // least element >= nums[idx] - t
			if ((floor != null && floor >= nums[idx]) || (ceiling != null && ceiling <= nums[idx])) {
				return true;
			}
			treeset.add((long) nums[idx]);

			if (idx >= k) { // maintain a size-k treeset to guarantee i and j 's absolute difference is at most k
				treeset.remove((long) nums[idx - k]);
			}
		}
		return false;
	}
}