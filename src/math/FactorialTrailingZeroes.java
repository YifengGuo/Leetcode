package math;
/**
 * 
 * @author guoyifeng
 * Given an integer n, return the number of trailing zeroes in n!.

	Example 1:
	
	Input: 3
	Output: 0
	Explanation: 3! = 6, no trailing zero.
	Example 2:
	
	Input: 5
	Output: 1
	Explanation: 5! = 120, one trailing zero.
	Note: Your solution should be in logarithmic time complexity.


 */
/*
 * actually a base_5 problem for 5! = 1
 */
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        if (n < 0) {
            return 0;
        }
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}