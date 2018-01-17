package recursion.twodarray;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yifengguo
 * Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. 
 * Return the list of traversal sequence.

	Assumptions
	
	The 2D array is not null and has size of M * N where M, N >= 0
	Examples
	
	{ {1,  2,  3,  4},
	
	  {5,  6,  7,  8},
	
	  {9, 10, 11, 12} }
	
	the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
 */
public class SpiralOrderTraverse2 {
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int left = 0;
		int right = n - 1;
		int top = 0;
		int bottom = m - 1;

		while (left < right && top < bottom) {
			// top row
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
			// right column
			for (int i = top + 1; i <= bottom - 1; i++) {
				res.add(matrix[i][right]);
			}
			// bottom row
			for (int i = right; i >= left; i--) {
				res.add(matrix[bottom][i]);
			}
			// left column
			for (int i = bottom - 1; i >= top + 1; i--) {
				res.add(matrix[i][left]);
			}
			// move 4 pointers
			left++;
			right--;
			top++;
			bottom--;
		}
		// post-process
		// 1. cannot form valid rectangle matrix
		if (top > bottom || left > right) {
			return res;
		} else if (left == right) { // 2. a column left
			for (int i = top; i <= bottom; i++) {
				res.add(matrix[i][left]);
			}
		} else { // 3. a row left
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
		}
		return res;
	}
}

