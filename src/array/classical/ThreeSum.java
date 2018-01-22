package array.classical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author yifengguo
 * Determine if there exists three elements in a given array that 
 * sum to the given target number. Return all the triple of values that sums to target.

	Assumptions
	
	The given array is not null and has length of at least 3
	No duplicate triples should be returned, order of the values in the tuple does not matter
	Examples
	
	A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]


 */
/*
 * time = O(n ^ 2)
 * space = O(n)
 */
public class ThreeSum {
	public List<List<Integer>> allTriples(int[] array, int target) {
		if (array == null || array.length == 0) {
			return new ArrayList<>();
		}
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(array);
		// iterate on each element of array
		for (int i = 0; i < array.length; i++) {
		  // dedup on current element
		  if (i > 0 && array[i] == array[i - 1]) {
		    continue;
		  } 
		    // from the next element to the last element of array
		    // apply two sum logic
			int left = i + 1;
			int right = array.length - 1;
			while (left < right) {
  			if (left > i + 1 && array[left] == array[left - 1]) {
  				left++;
  				continue;
  			}
  			if (right < array.length - 1 && array[right] == array[right + 1]) {
  				right--;
  				continue;
  			}
  			int tmp = array[left] + array[right];
  			if (tmp == target - array[i]) {
  				List<Integer> sub = new ArrayList<>();
  				sub.add(array[left]);
  				sub.add(array[right]);
  				sub.add(array[i]);
  				res.add(sub);
  				left++;
  				right--;
  			} else if (tmp < target - array[i]) {
  				left++;
  			} else {
  				right--;
  			}
  		}
		}
		return res;
	}
}

