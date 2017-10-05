package TopInterviewQuestions;

/**
 * 
 * @author yifengguo 
 * 			Reverse digits of an integer.
 * 
 *         Example1: x = 123, return 321 Example2: x = -123, return -321
 * 
 *         click to show spoilers.
 * 
 *         Note: The input is assumed to be a 32-bit signed integer. Your
 *         function should return 0 when the reversed integer overflows.
 */

public class ReverseInteger {
	public int reverse(int x) {
		int remainder = 0;
		int res = 0;
		while (x != 0) {
			remainder = x % 10;
			int newRes = res * 10 + remainder;
			/*
			 * if newRes is overflow, (newRes - remainder) / 10 cannot equal to original res
			 */
			if ((newRes - remainder) / 10 != res) {
				return 0;
			}
			res = newRes;
			x /= 10;
		}
		return res;
	}

	public static void main(String[] args) {
		int a = 1298492033;
		System.out.println(new ReverseInteger().reverse(a));
	}
}
