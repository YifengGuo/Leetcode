package array.Deduplication;

import java.util.Arrays;

/**
 * 
 * @author yifengguo 
 *         Given a sorted integer array, remove duplicate elements.
 *         For each group of elements with the same value keep only one of them.
 *         Do this in-place, using the left side of the original array and
 *         maintain the relative order of the elements of the array. Return the
 *         array after deduplication.
	 * 	Assumptions
	
	The array is not null
	Examples
	
	{1, 2, 2, 3, 3, 3} → {1, 2, 3}
 * 
 */
// 1 2 2 3 3 3 
// s
//   f
public class ArrayDeduplication1 {
	public int[] dedup(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		int slow = 0;
		int fast = 0;
		while (fast < array.length) {
			if (array[fast] == array[slow]) {
				fast++;
			} else {
				array[++slow] = array[fast++];
			}
		}
		return Arrays.copyOfRange(array,0,slow + 1); // [from, to), from index is inclusive 
		                                             // while to index is exclusive
  }
}
