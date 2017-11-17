package bit_manipulation;
/**
 * 
 * @author guoyifeng
 	Determine the number of bits that are different for two given integers.

	Examples
	
	5(“0101”) and 8(“1000”) has 3 different bits


 */
public class NumberOfDifferentBits {
	/*
	 * solution 1:
	 * 	1. get the XOR result of a and b
	 *  2. check the bit of this result bit by bit, 1 means the original bits are different
	 *  time = O(n)
	 */
	public int diffBits(int a, int b) {
		int c = a ^ b; 
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((c & (1 << i)) != 0) {
				count++;
			}
		}
		return count;
	}
	
	/*
	 * solution 2:
	 * time = O(n)
	 */
	public class Solution {
		public int diffBits(int a, int b) {
			int count = 0;
			for (int i = 0; i < 32; i++) {
				count += (a & 1) ^ (b & 1);
				a = a >> 1;
				b = b >> 1;
			}
			return count;
		}
	}
}