package array.majority_number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yifengguo
 * Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.

	Assumptions
	
	The given array is not null
	Examples
	
	A = {1, 2, 1, 2, 1}, return [1, 2]
	A = {1, 2, 1, 2, 3, 3, 1}, return [1]
	A = {1, 2, 2, 3, 1, 3}, return []
 */

public class MajorityNumber2 {
	/*
	 * method 2: voting algorithm
	 * time = O(n)
	 * space = O(1)
	 *   
	 */
	public List<Integer> majorityElement(int[] array) {
		List<Integer> res = new ArrayList<>();
		if (array == null || array.length == 0) {
			return res;
		}
        if (array.length == 1) {
            res.add(array[0]);
            return res;
        }
		int candidate1 = 0; // candidate1 and candidate2 must be initialized with different values
		int candidate2 = 1;
		int count1 = 0;
		int count2 = 0;
        // this for loop is to find two possible candidate, but still not guarantee candidate1 equals to candidate2 or not
		// and still cannot handle count1 == 1/3 len and count2 == 1/3 len
		// so we need to iterate on the array again to dedup and filter out equal 1/3 cases
		for (int i = 0; i < array.length; i++) {
			if (array[i] == candidate1) {
				count1++;
			} else if (array[i] == candidate2) {
				count2++;
            } else if (count1 == 0) {
                candidate1 = array[i];
                count1++;
            } else if (count2 == 0) {
                candidate2 = array[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
		}
		// reset count1
		// and iterate on the array again to find right count
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == candidate1) {
                count1++;
            } else if (array[i] == candidate2) {
                count2++;
            }
        }
		if (count1 > array.length / 3) {
			res.add(candidate1);
		}
		if (count2 > array.length / 3) {
			res.add(candidate2);
		}
		return res;
	}
	/*
	 * method 1:
	 * basic idea: hashtable
	 * time = O(n)
	 * space = O(n)
	 */
	public List<Integer> majority(int[] array) {
		List<Integer> res = new ArrayList<>();
		if (array == null || array.length == 0) {
			return res;
		}
		// key: element, value: count
		Map<Integer, Integer> map = new HashMap<>();
		int threshold = array.length / 3;
		for (int i : array) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			if (e.getValue() > threshold) {
				res.add(e.getKey());
			}
		}
		return res;
	}
}
