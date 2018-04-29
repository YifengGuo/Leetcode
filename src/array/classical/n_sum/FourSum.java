package array.classical.n_sum;
/**
 * 
 * @author yifengguo
  Determine if there exists a set of four elements in a given array that sum to the given target number.

	Assumptions
	
	The given array is not null and has length of at least 4
	Examples
	
	A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)
	
	A = {1, 2, 2, 3, 4}, target = 12, return false
 */
/*
 * basic idea: only to determine if four sum exists, so do not need to de-duplicate elements
 */
public class FourSum {
	public boolean exist(int[] array, int target) {
		// sanity check
		if (array == null || array.length < 4) {
			return false;
		}
		// iterate on each element in the array
		for (int i = 0; i < array.length; i++) {
			int threeSum = target - array[i];
			// apply three sum logic from the next element to the last element
			for (int j = i + 1; j < array.length; j++) {
				int twoSum = threeSum - array[j];
				// apply two sum logic from the next element to the last element
				int left = j + 1;
				int right = array.length - 1;
				while (left < right) {
					int tmp = array[left] + array[right];
					if (tmp == twoSum) {
						return true;
					} else if (tmp < target) {
						left++;
					} else {
						right--;
					}
				}
			}
		}
		return false;
	}
}