package array.classical;
/**
 * 
 * @author yifengguo
 * Given an array of non-negative integers, each of them representing the height of a board perpendicular to the horizontal line, 
 * the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. 
 * Find the volume of the largest such container.

	Assumptions
	
	The given array is not null and has size of at least 2
	Examples
	
	{ 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, the volume of the container is 2 * 4 = 8.
 */
/*
 * time = O(n ^ 2)
 * space = O(1)
 * 
 *    |
 *    |    |
 *    |__|_|_|    find largest area of trapped container
 */
public class LargestContainer {
	public int largest(int[] array) {
		if (array == null || array.length < 2) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		int height = 0;
		int width = 0;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				height = Math.min(array[i], array[j]);
				width = i - j;
				res = Math.max(height * width, res);
			}
		}
		return res;
	}
}
