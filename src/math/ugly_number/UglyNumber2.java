package math.ugly_number;

/**
 * 
 * @author guoyifeng 
 *         Write a program to find the n-th ugly number.
 * 
 *         Ugly numbers are positive numbers whose prime factors only include 2,
 *         3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of
 *         the first 10 ugly numbers.
 * 
 *         Note that 1 is typically treated as an ugly number, and n does not
 *         exceed 1690.
 */
/*
 *   The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
     because every number can only be divided by 2, 3, 5, one way to look at 
     the sequence is to split the sequence to three groups as below:
     (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5. 
     Then we use similar merge method as merge sort, to get every ugly number from the three subsequence. 
     Every step we choose the smallest one, and move one step after.
 */
public class UglyNumber2 {
	//    1x2 2x2 3x2 4x2 5x2
	//    2   4   6  8   10
	// i1
	//    1x3 2x3 3x3 4x3 5x3
	//    3    6   9   12  15
	// i2
	//    1x5 2x5 3x5 4x5 5x5
	//     5   10  15  20  25
	// i3
	//  1 2 3 4 5 6 8 9 10 12
	 public int nthUglyNumber(int n) {
		 // sanity check
		 if (n <= 0) {
			 
		 }
		 if (n == 1) {
			 return 1;
		 }
		 int[] ugly = new int[n];
		 int index2 = 0, index3 = 0, index5 = 0;
		 int next_multiple_of_2 = 2, next_multiple_of_3 = 3, next_multiple_of_5 = 5;
		 int next_ugly_num = 0;
		 for (int i = 1; i < n; i++) {
			 next_ugly_num = Math.min(Math.min(next_multiple_of_2, next_multiple_of_3), next_multiple_of_5);
			 ugly[i] = next_ugly_num;
			 // make sure use multiple if instead of else if because there are duplicates in 3 sequences (2 * 6 == 3 * 4)
			 if (next_multiple_of_2 == next_ugly_num) {
				 index2++;
				 next_multiple_of_2 = 2 * ugly[index2]; // ugly[index2] is always factor of 2
			 }
			 if (next_multiple_of_3 == next_ugly_num) {
				 index3++;
				 next_multiple_of_3 = 3 * ugly[index3];
			 }
			 if (next_multiple_of_5 == next_ugly_num) {
				 index3++;
				 next_multiple_of_5 = 5 * ugly[index5];
			 }
		 }
		 return ugly[n - 1];
	 }
}
