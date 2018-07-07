package array.duplicate_distance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author guoyifeng
 * Given an array of integers and an integer k, find out whether there are two distinct 
 * indices i and j in the array such that nums[i] = nums[j] and the absolute difference between 
 * i and j is at most k.

	Example 1:
	
	Input: nums = [1,2,3,1], k = 3
	Output: true
	Example 2:
	
	Input: nums = [1,0,1,1], k = 1
	Output: true
	Example 3:
	
	Input: nums = [1,2,3,1,2,3], k = 2
	Output: false
 */
public class ContainsDuplicate2 {
	/*
	 * better method: sliding window and hashset 
	 * maintain a hashset whose size cannot exceed k
	 * once we found another number which is in the set and index is in range of k
	 * return true
	 */
	public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }
	/*
	 * method 1: HashMap
	 * time = O(n) - O(n ^ 2) it depends on the duplicate count of the array
	 * space = O(n)
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return false;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.get(nums[i]).add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(nums[i], list);
			}
		}

		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<Integer> tmp = entry.getValue();
			if (tmp.size() <= 1) {
				continue;
			} else {
				for (int i = 0; i < tmp.size(); i++) {
					for (int j = i + 1; j < tmp.size(); j++) {
						if (Math.abs(tmp.get(i) - tmp.get(j)) <= k) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
