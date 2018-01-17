package recursion.twodarray;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author yifengguo
 * Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. 
 * Return the list of traversal sequence.

	Assumptions
	
	The 2D array is not null and has size of N * N where N >= 0
	Examples
	
	{ {1,  2,  3},
	
	  {4,  5,  6},
	
	  {7,  8,  9} }
	
	the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]


 */
public class SpiralOrderTraverse1 {
	/*
	 * method 1: recursion
	 * base case: reach the inner most layer (size <= 1)
	 * recursion rule: size - 2 , offset + 1
	 * time = O(n^2)
	 * space = O(n)
	 */
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if (matrix == null) {
			return res;
		}
		helper(res, 0, matrix.length, matrix);
		return res;
	}
	
	private void helper(List<Integer> res, int offset, int size, int[][] matrix) {
		// base case
		// reach the inner most layer
		if (size == 1) {
			res.add(matrix[offset][offset]);
			return;
		} else if (size < 1) {
		  return;
		}
		// top
		for (int i = 0; i < size - 1; i++) {
			res.add(matrix[offset][i + offset]);
		}
		// right
		for (int i = 0; i < size - 1; i++) {
			res.add(matrix[i + offset][size - 1 + offset]);
		}
		// bottom
		for (int i = size - 1 + offset; i > offset; i--) {
			res.add(matrix[size - 1 + offset][i]);
		}
		// left
		for (int i = size - 1 + offset; i > offset; i--) {
			res.add(matrix[i][offset]);
		}
		helper(res, offset + 1, size - 2, matrix);
	}
}
