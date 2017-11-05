package array.Sort;

/**
 * 
 * @author guoyifeng Given an array of integers, sort the elements in the array
 *         in ascending order. The merge sort algorithm should be used to solve
 *         this problem.
 * 
 *         Examples
 * 
 *         {1} is sorted to {1} {1, 2, 3} is sorted to {1, 2, 3} {3, 2, 1} is
 *         sorted to {1, 2, 3} {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
 *         Corner Cases
 * 
 *         What if the given array is null? In this case, we do not need to do
 *         anything. What if the given array is of length zero? In this case, we
 *         do not need to do anything.
 */
/*
 * time = O(nlogn)
 * space = O(n)
 * https://stackoverflow.com/questions/10342890/merge-sort-time-and-space-complexity
 */
public class MergeSort {
	public class Solution {
		public int[] mergeSort(int[] array) {
			if (array == null || array.length == 0) {
				return array;
			}
			int[] aux = new int[array.length];
			mergeSort(array, aux, 0, array.length - 1);
			return array;
		}

		private void mergeSort(int[] array, int[] aux, int lo, int hi) {
			// base case
			// cannot further divide
			if (lo >= hi) {
				return;
			}
			int mid = lo + (hi - lo) / 2;
			mergeSort(array, aux, lo, mid);
			mergeSort(array, aux, mid + 1, hi);
			merge(array, aux, lo, mid, hi);
		}

		private void merge(int[] array, int[] aux, int lo, int mid, int hi) {
			int left = lo;
			int right = mid + 1;
			for (int i = lo; i <= hi; i++) {
				aux[i] = array[i];
			}
			while (left <= mid && right <= hi) {
				if (aux[left] <= aux[right]) {
					array[lo++] = aux[left++];
				} else {
					array[lo++] = aux[right++];
				}
			}
			while (left <= mid) {
				array[lo++] = aux[left++];
			}
		} 
	}
}
