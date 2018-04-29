package math;

/**
 * 
 * @author yifengguo
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
 * which together with x-axis forms a container, such that the container contains the most water.

	Note: You may not slant the container and n is at least 2.

  y ^
    |
    |     a2
    |     |  a3          an
    |  a1 |  |     a5    | 
    |  |  |  |  a4 |     |
    |  |  |  |  |  | ..  |
    --------------------------->
   0   1  2  3  4  5 ..  n     x

 */
public class ContainerWithMostWater {
	/*
	 * basic idea: two pointer
	 * time = O(n)
	 * left and right pointer
	 * because the distance between left and right pointer is inevitably decreasing
	 * so we need to keep the higher one (height[left], height[right])
	 * and move the pointer based on this principle
	 */
	public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int dist = right - left;
            if (Math.min(height[left], height[right]) * dist > max) {
                max = Math.min(height[left], height[right]) * dist;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

	// method 1 intuitive thinking
	// cannot pass total test cases
	// for its time complexity is O(n ^ 2)
//	public int maxArea(int[] height) {
//		if (height == null || height.length == 0) {
//			return 0;
//		}
//		int max = Integer.MIN_VALUE;
//		for (int i = 0; i < height.length; i++) {
//			for (int j = i + 1; j < height.length; j++) {
//				max = Math.max(Math.min(height[i], height[j]) * Math.abs(i - j), max);
//			}
//		}
//		return max;
//	}
}
