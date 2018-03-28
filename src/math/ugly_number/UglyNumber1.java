package math.ugly_number;
/**
 * 
 * @author guoyifeng
 * Write a program to check whether a given number is an ugly number.

	Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
	For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
	
	Note:
	
	1 is typically treated as an ugly number.
	Input is within the 32-bit signed integer range.

 */
public class UglyNumber1 {
	// more concise way
	public boolean isUgly(int num) {
		if (num <= 0) {
			return false;
		}
		if (num == 1) {
			return true;
		}
		int[] divisors = {2, 3, 5};
		for (int d : divisors) {
			while (num % d == 0) {
				num /= d;
			}
		}
		return num == 1;
	}
	
	// method 2: divide factor one by one and check if final result == 1 or not
//	public boolean isUgly(int num) {
//		if (num <= 0) {
//			return false;
//		}
//		if (num == 1) {
//			return true;
//		}
//		while (num % 2 == 0) num /= 2;
//		while (num % 3 == 0) num /= 3;
//		while (num % 5 == 0) num /= 5;
//		return num == 1; // 
//	}
}
