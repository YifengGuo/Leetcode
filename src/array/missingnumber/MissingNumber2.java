package array.missingnumber;
/**
 * 
 * @author yifengguo
 * Given an integer array of size N - 1 sorted by ascending order, 
 * containing all the numbers from 1 to N except one, find the missing number.

	Assumptions
	
	The given array is not null, and N >= 1
	Examples
	
	A = {1, 2, 4}, the missing number is 3
	A = {1, 2, 3}, the missing number is 4
	A = {}, the missing number is 1
 */
/*
 * basic idea:
 *  	for the array is sorted and the range of element is from 1 to N
 *      idea 1: sum 1 to N and subtract the sum of the array   =====> not quite good when N is very huge => may cause overflow
 *      idea 2: for the array is sorted, so for example    1 2 4 5
 *                                the array is missing 3
 *                                do exclusive or on each element with corresponding number
 *                                1 2 4 5
 *                                1 2 3 4
 *                          1 ^ 1 = 0
 *                          2 ^ 2 = 0
 *                          3 ^ 4 != 0
 *                          so missing number is 3
 *                          time = O(n)
 *                          space = O(1)
 */
public class MissingNumber2 {
	public int missing(int[] array) {
		if (array == null || array.length == 0) {
			return 1;
		}
		// Write your solution here
		int a = 1;
		int res = 0;
		for (int i = 0; i < array.length; i++) {
			res = array[i] ^ a;
			if (res != 0) {
				return a;
			}
			a++;
		}
		return a;
	}
}
