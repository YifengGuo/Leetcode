package math;
/**
 * 
 * @author yifengguo
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

	Example 1:
	
	Input: 121
	Output: true
	Example 2:
	
	Input: -121
	Output: false
	Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
	Example 3:
	
	Input: 10
	Output: false
	Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
	Follow up:
	
	Coud you solve it without converting the integer to a string?


 */
/*
 * basic idea:
 * 	mod original x and multiply  and rebuild it
 */
public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int x1 = x;
		int tmp = 0;
		while (x > 0) {
			int mod = x % 10;
			x = x / 10;
			tmp = tmp * 10 + mod;
		}
		return tmp == x1;
	}
}
