package array.majority_number;
/**
 * 
 * @author yifengguo
 * Given an integer array of length L, find the number that occurs more than 0.5 * L times.

	Assumptions
	
	The given array is not null or empty
	It is guaranteed there exists such a majority number
	Examples
	
	A = {1, 2, 1, 2, 1}, return 1
 */
/*
 * basic idea: maintain a pair <candidate, count>
 * initially set candidate as array[0], count = 1
 * and traverse on the array, if candidate == array[i], count++
 *                            if candidate != array[i]: 
 *                                if count = 0, reset candidate as array[i], count = 1
 *                                if count != 0, count--
 *                                
 * time = O(1)
 * space = O(n)
 */
public class MajorityNumber1 {
	public int majority(int[] array) {
		// Write your solution here.
		if (array == null || array.length == 0) {
			return 0;
		}
		int candidate = array[0];
		int count = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == candidate) {
				count++;
			} else {
				if (count == 0) {
					candidate = array[i];
					count++;
				} else {
					count--;
				}
			}
		}
		return candidate;
	}
}

