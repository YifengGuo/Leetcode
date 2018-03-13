package forusall_OA;
/**
 * 
 * @author guoyifeng
 * Given an array, find the largest sum of two subarrays with contiguous elements of given array
 * Each element can only appear in either subarray
 * Time O(n)
 * SpaceO(n) 
 * eg [3,0,4,6,3,2,7,4],target subarrays are [4,6,3] 和 [7,4],  
 * largest sum is 13 + 11 = 24
 */
// 3     0     4     6    3    2    7    4
//
//
public class LargestSumOfTwoSubarrays {
	/**
	 * 
	 * @param arr given array
	 * @param a size of target subarray1
	 * @param b size of target subarray2
	 * @return the largest sum of two subarrays
	 */
	public int largestSum(int[] arr, int a, int b) {
		int leftLargest1 = Integer.MIN_VALUE;		
		int rightLargest1 = Integer.MIN_VALUE;
		int leftLargest2 = Integer.MIN_VALUE;		
		int rightLargest2 = Integer.MIN_VALUE;
		// left to right
		for (int i = a - 1; i < arr.length - b; i++) {
			int tempLeft = 0;
			int tempRight = 0;
			for (int j = 0; j < a; j++) {
				tempLeft += arr[i - j];
			}
			for (int k = 1; k <= b; k++) {
				tempRight += arr[i + k];
			}
			leftLargest1 = Math.max(tempLeft, leftLargest1);
			rightLargest1 = Math.max(tempRight, rightLargest1);
		}
		
		// right to left
		for (int i = arr.length - a; i >= b; i--) {
			int tempLeft = 0;
			int tempRight = 0;
			for (int j = 0; j < a; j++) {
				tempLeft += arr[i + j];
			}
			for (int k = 1; k <= b; k++) {
				tempRight += arr[i - k];
			}
			leftLargest2 = Math.max(tempLeft, leftLargest2);
			rightLargest2 = Math.max(tempRight, rightLargest2);
		}
		return Math.max(leftLargest2 + rightLargest2, leftLargest1 + rightLargest1);
	}
	
	public static void main(String[] args) {
		int[] arr = {3,0,4,6,3,2,7,4};
		int a = 3, b = 2;
		System.out.println(new LargestSumOfTwoSubarrays().largestSum(arr, a, b));
	}
}
