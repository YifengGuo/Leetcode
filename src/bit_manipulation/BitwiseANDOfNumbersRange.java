package bit_manipulation;
/**
 * 
 * @author guoyifeng
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, 
 * return the bitwise AND of all numbers in this range, inclusive.

	Example 1:
	
	Input: [5,7]
	Output: 4
	Example 2:
	
	Input: [0,1]
	Output: 0

 */
/*
 * demo:
 *  [26 - 30] 
 *  11010
 *  11011
 *  11100
 *  11101
 *  11110
 *  
 *  so until 11xxxx can we make m == n
 *  so 11000 is the AND pattern between 26 and 30
 *  
 *  we keep right bit move on the m and n until they are equal
 *  and then left bit move to complement 0s on the right to find the AND number
 *  
 */
public class BitwiseANDOfNumbersRange {
	public int rangeBitwiseAnd(int m, int n) {
		if (m == 0) {
			return 0;
		}
		int i = 0; // i is the count of 0s on the right
		while (m != n) {
			m = m >> 1;
			n = n >> 1;
			i++;
		}
		return m << i;
	}
}
