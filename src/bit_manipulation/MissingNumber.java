package bit_manipulation;
/**
 * 
 * @author guoyifeng
 	Given an integer array of size N - 1, containing all the numbers from 1 to N except one, 
 	find the missing number.

	Assumptions
	
	The given array is not null, and N >= 1
	Examples
	
	A = {2, 1, 4}, the missing number is 3
	A = {1, 2, 3}, the missing number is 4
	A = {}, the missing number is 1
 */
/*
 * basic idea: XOR operation
 * 	for the array is n - 1 size and its elements are no duplicate and each is ranging from 0 - n
	assume array {A1, A2, A4}, A3 is missing
	X1 = A1 ^ A2 ^ A4 (run XOR on each element in array)
	X2 = A1 ^ A2 ^ A3 ^ A4 (run XOR on 1 to n)
	
	// x1 ^ x2 gives the missing number
	X1 ^ X2 = (A1 ^ A2 ^ A3) ^ (A1 ^ A2 ^ A3 ^ A4)
	        = (A1 ^ A1) ^ (A2 ^ A2) ^ (A4 ^ A4) ^ A3
	        = 0 ^ 0 ^ 0 ^ A3
	        = 0 ^ A3
	        = A3
	        
time = O(n)
 */
public class MissingNumber {
	public int missing(int[] array) {
		if (array == null || array.length == 0) {
			return 1;
		}
		int x1 = 0;
		int x2 = 0;
		for (int i = 0; i < array.length; i++) { // run XOR on each element in array
			x1 = x1 ^ array[i];
		}
		for (int i = 1; i <= array.length + 1; i++) { // run XOR on 1 to n
			x2 = x2 ^ i;
		}
		return x1 ^ x2; // x1 ^ x2 gives the missing number
	}
}

// sol 2: sum formula: 
// 			1. sum 1 to n
// 			2. sum - sum(array) = missing number
//  time = O(n)

// sol 3: HashMap
// Time = O(n)
// Space = O(n)
