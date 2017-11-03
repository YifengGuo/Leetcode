package array.Sort;
/**
 * 
 * @author guoyifeng
	 Given an array of integers, sort the elements in the array in ascending order. 
	 The quick sort algorithm should be used to solve this problem.
	
	Examples
	
	{1} is sorted to {1}
	{1, 2, 3} is sorted to {1, 2, 3}
	{3, 2, 1} is sorted to {1, 2, 3}
	{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
	Corner Cases
	
	What if the given array is null? In this case, we do not need to do anything.
	What if the given array is of length zero? In this case, we do not need to do anything.

 */
/*
 * time = O(nlogn) for average, can degrade to O(n ^ 2) if pivot choosing is not good and order of given array is not good
 * space = O(logn)
 */
public class QuickSort {
	public int[] quickSort(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		quickSort(array, 0, array.length - 1);
		return array;
	}

	private void quickSort(int[] array, int lo, int hi) {
		// base case
		// cannot further divide
		if (lo >= hi) {
			return;
		}
		int pivot = partition(array, lo, hi);
		// divide and conquer
		quickSort(array, lo, pivot - 1);
		quickSort(array, pivot + 1, hi);
	}
	/**
	 * this function is to make array with given range [lo, hi] and 
	 * randomly choose pivot order in the way that all the elements
	 * smaller than pivot are on its left side and all the elements
	 * greater than pivot are on its right side.
	 */
	private int partition(int[] array, int lo, int hi) {
		int pivotIndex = getPivotIndex(lo, hi); 		// randomly choose pivot index
		int pivot = array[pivotIndex]; // assign pivot
		swap(array, pivotIndex, hi); // move pivot to position hi
		int left = lo;
		int right = hi - 1;
		// operate range [left, right] and make all the elements smaller
		// than pivot are on the left side of pivot, and all the elements
		// greater than pivot are on the right side of pivot
		while (left <= right) {
			if (array[left] <= pivot) {
				left++;
			} else if (array[right] >= pivot) {
				right--;
			} else {  // if arr[left] > pivot && arr[right] < pivot
				swap(array, left, right);
				left++;
				right--;
			}
		}
		// relocate pivot to the position it should be
		// array[left] is the first element which greater than pivot
		// so it is safe to put it to hi
		swap(array, hi, left);
		return left;
		
	}
	
	private int getPivotIndex(int lo, int hi) {
		double rand = Math.random();
		return lo + (int)rand * (hi - lo + 1);
	}

	private void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}

