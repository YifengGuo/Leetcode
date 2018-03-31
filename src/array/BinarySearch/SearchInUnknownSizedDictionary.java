package array.BinarySearch;

interface Dictionary {
	public Integer get(int index);
}

// You do not need to implement the Dictionary interface.
// You can use it directly, the implementation is provided when testing your
// solution.
/*
 * basic idea: try to shrink search space into a smaller range
 *     step 1: move forward right 2 times each step until we found either dict.get(right) is out
 *             of range or dict.get(right) > target
 *             
 *             so it would be like  [left,.... target..., right]
 *             
 *             and we do not know right is out of dictionary or not because we
 *             advance right 2 times each step
 *             
 *             then run classical binary search on range[left, right]
 *             to find the target
 */
public class SearchInUnknownSizedDictionary {
	public int search(Dictionary dict, int target) {
		if (dict == null) {
			return -1;
		}
		int left = 0;
		int right = 1;
		while (dict.get(right) != null && dict.get(right) < target) {
			left = right;
			right = 2 * right;
		}
		return binarySearch(dict, target, left, right);
	}

	private int binarySearch(Dictionary dict, int target, int left, int right) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (dict.get(mid) == null || dict.get(mid) > target) { // mid maybe out of dict
				right = mid - 1;
			} else if (dict.get(mid) < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
