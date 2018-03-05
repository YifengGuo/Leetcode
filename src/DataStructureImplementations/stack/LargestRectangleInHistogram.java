package DataStructureImplementations.stack;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * 
 * @author guoyifeng
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 * find the area of largest rectangle in the histogram.


	Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
	
	
	The largest rectangle is shown in the shaded area, which has area = 10 unit.

	For example,
	Given heights = [2,1,5,6,2,3],
	return 10.
 */
/*
 * idea: https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 *       try to calculate each area with each bar as the smallest bar to form this area
 *       stack stores the indices of ascending order of bar length
 *       right pointer as i is the current traversing height
 *       left pointer is the previous element in the stack if it is not empty
 *       
 *       (left   idx = s.pop()   ...  right)
 *       
 *       
 *       
 *         
 * time = O(n) for each element will be push and pop into and from stack once and only once 
 */
public class LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}
		int len = heights.length;
		// stack stores the index of bar in the heights
		// we maintain the stack in the way which it stores indices in the ascending way 
		// so that heights[stack.peek()] is larger than any other in the stack
		Deque<Integer> stack = new ArrayDeque<>();
		int maxArea = 0;
		int i = 0;
		while (i < len) {
			if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
				stack.push(i++);
			} else {
				int rm_bar_idx = stack.pop();
				int area = 0;
				if (stack.isEmpty()) {
					area = heights[rm_bar_idx] * i; // either the heights[rm_bar_idx] is the smallest for the stack is empty
					                                // or it is the first height and is larger than the second height, so the right limit must be i
				} else {
					area = heights[rm_bar_idx] * (i - stack.peek() - 1); // consider current heights[rm_bar_idx] as smallest bar
					                                                     // previous one in the stack is smaller than it so filter it out   -> left
					                                                     // current i is smaller than heights[rm_bar_idx] so filter it out  -> right
					                                                     // then the width is the number of bars between (left, right)
				}
				maxArea = Math.max(area, maxArea);
			}
		}
		// || 1, 4, 5 is the indices remained in the stack after traversal on the heights
		// i == len right now
		// this is must be an ascending order and we do the same logic to calculate the max area with pop() bar as the smallest bar
		while (!stack.isEmpty()) {
			int rm_bar_idx = stack.pop();
			int area = 0;
			if (stack.isEmpty()) {
				area = heights[rm_bar_idx] * i;
			} else {
				area = heights[rm_bar_idx] * (i - stack.peek() - 1);
			}
			maxArea = Math.max(area, maxArea);
		}
		return maxArea;
	}
}
