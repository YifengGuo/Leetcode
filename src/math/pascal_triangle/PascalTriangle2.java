package math.pascal_triangle;

import java.util.Arrays;
import java.util.List;

// i=0: [1]
// i=1: [1,1]
// i=2: [1,1,1] -> [1,2,1]
// i=3: [1,2,1,1] -> [1,2,3,1] -> [1,3,3,1]
// i=4: [1,3,3,4,1] -> [1,3,6,4,1] -> [1,4,6,4,1]
/**
 * 
 * @author guoyifeng
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
   Note that the row index starts from 0.
   In Pascal's triangle, each number is the sum of the two numbers directly above it.

	Example:
	
	Input: 3
	Output: [1,3,3,1]
	Follow up:
	
	Could you optimize your algorithm to use only O(k) extra space?


 */
/*
 * generate the current row elements based on last iteration generation based on pascal triangle property
 * time = O(n ^ 2)
 * space = O(k)
 */
public class PascalTriangle2 {
	public List<Integer> getRow(int k) {
		Integer[] arr = new Integer[k + 1]; // kth row has k + 1 elements
		Arrays.fill(arr, 1);
		for (int i = 0; i <= k; i++) {
			for (int j = i - 1; j >= 1; j--) {
				arr[j] += arr[j - 1];
			}
		}
		return Arrays.asList(arr);
	}
}
