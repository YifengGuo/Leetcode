package array.Sort;

/**
 * 
 * @author yifengguo 
 *         Given an array of balls, where the color of the balls can
 *         only be Red, Green or Blue, sort the balls such that all the Red
 *         balls are grouped on the left side, all the Green balls are grouped
 *         in the middle and all the Blue balls are grouped on the right side.
 *         (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by
 *         1).
 */
/*
 * basic idea: left and right pointers divide search space into three parts
 * 			   [0, left] are -1
 * 			   [left + 1, right - 1] 0
 * 			   [right, array.length - 1] are 1
 * 			   we use cur pointer to check current position value
 * time = O(n)
 * space = O(1)
 */
public class RainbowSort {
	public int[] rainbowSort(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		int left = 0;
		int right = array.length - 1;
		int cur = left;
		while (cur <= right) {
			if (array[cur] == -1) {
				swap(array, cur++, left++);
			} else if (array[cur] == 0) {
				cur++;
			} else {
				swap(array, cur, right--); // for we do not know will be swapped from arr[right], so do not move cur
			}
		}
		return array;
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
