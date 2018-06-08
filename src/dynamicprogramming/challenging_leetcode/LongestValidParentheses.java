package dynamicprogramming.challenging_leetcode;
/**
 * 
 * @author guoyifeng
 * Given a string containing just the characters '(' and ')', 
 * find the length of the longest valid (well-formed) parentheses substring.

	Example 1:
	
	Input: "(()"
	Output: 2
	Explanation: The longest valid parentheses substring is "()"
	Example 2:
	
	Input: ")()())"
	Output: 4
	Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		int max = 0;
		if (s == null || s.length() <= 1) {
			return 0;
		}
		char[] arr = s.toCharArray();
		// dp[i] represents count of the current valid parentheses from arr[0] to arr[i];
		// so dp[i] is like (...)  {()()() or ((())) are both valid}
		int[] dp = new int[arr.length];
		int left = 0; // count of left parenthesis
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(') {
				left++;
			}
			if (arr[i] == ')' && left > 0) { // if there are left parenthesis exists before
				// dp[i-1] is its previous consecutive parentheses
				// if previous is '(', dp[i - 1] == 0
				// if previous is ')', dp[i - 1] is the longest valid parentheses count
				// i-2-dp[i-1] is the position right outside this big "( ... )" part. 
				// So check if previous big "(...)" is a valid one.
				dp[i] = 2 + dp[i - 1] + (i - 2 - dp[i - 1] > 0 ? dp[i - 2 - dp[i - 1]] : 0); 
				left--;
			}
			max = Math.max(dp[i], max);
		}
		return max;
	}
}
