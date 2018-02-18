package array.Sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author guoyifeng
 * Use the least number of comparisons to get the largest and 2nd largest number 
 * in the given integer array. Return the largest number and 2nd largest number.

	Assumptions
	
	The given array is not null and has length of at least 2
	Examples
	
	{2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
 */
public class LargestAndSecondLargest {
	/*
	 * method 1: heap
	 * compare time = n
	 * time = O(nlogn)
	 * space = O(n)
	 */
	public int[] largestAndSecond1(int[] array) {
		int[] res = new int[2];
		if (array == null || array.length < 2) {
			return res;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				if (a == b)
					return 0;
				return a < b ? 1 : -1;
			}
		});
		for (int i = 0; i < array.length; i++) {
			maxHeap.offer(array[i]);
		}
		res[0] = maxHeap.poll();
		res[1] = maxHeap.poll();
		return res;
	}
	
	/*
	 * method 2
	 *  two time traversal on array
	 *  space = O(1)
	 *  time = O(n)
	 *  compare time = 2n
	 */
	public int[] largestAndSecond2(int[] array) {
		int[] res = new int[2];
		if (array == null || array.length < 2) {
			return res;
		}
		int first_max = Integer.MIN_VALUE;
		int second_max = Integer.MIN_VALUE;
		int first_max_index = -1; // record first_max position for max value could have more than 3 duplicates
		for (int i = 0; i < array.length; i++) {
			if (array[i] > first_max) {
				first_max = array[i];
				first_max_index = i;
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] > second_max && i != first_max_index) {
				second_max = array[i];
			}
		}
		res[0] = first_max;
		res[1] = second_max;
		return res;
	}
	
	/*
	 * optimized method 2
	 * time = O(n)
	 * space = O(1)
	 * compare time = O(n)
	 */
	public int[] largestAndSecond3(int[] array) {
		int[] res = new int[2];
		if (array == null || array.length < 2) {
			return res;
		}
		int first_max = Integer.MIN_VALUE;
		int second_max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= first_max) {
				second_max = first_max;
				first_max = array[i];
			} else if (array[i] < first_max && array[i] >= second_max) {
				second_max = array[i];
			}
		}
		res[0] = first_max;
		res[1] = second_max;
		return res;
	}
	
}
