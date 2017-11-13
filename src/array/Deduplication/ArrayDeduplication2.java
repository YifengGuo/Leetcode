package array.Deduplication;

import java.util.Arrays;

/**
 * 
 * @author yifengguo 
 *         Given a sorted integer array, remove duplicate elements.
 *         For each group of elements with the same value keep at most two of
 *         them. Do this in-place, using the left side of the original array and
 *         maintain the relative order of the elements of the array. Return the
 *         array after deduplication.
 * 
 *         Assumptions
 * 
 *         The given array is not null Examples
 * 
 *         {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
 */
/*
 * [0, slow) result
 * [slow, fast] useless duplications
 * (fast, array.length - 1] unkown area need to discover
 * 
 * compare array[fast] with array[slow - 2]
 */
public class ArrayDeduplication2 {
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		int slow = 2;
		for (int fast = 2; fast < array.length; fast++) {
			if (array[fast] == array[slow - 2]) {
				continue;
			} else {
				array[slow++] = array[fast];
			}
		}
		return Arrays.copyOfRange(array, 0, slow);
	}	
}