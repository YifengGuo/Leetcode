package TopInterviewQuestions;
/**
 * 
 * @author yifengguo
 	Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

	Example:
	
	Input: "babad"
	
	Output: "bab"
	
	Note: "aba" is also a valid answer.
	Example:
	
	Input: "cbbd"
	
	Output: "bb"
 */
/*
 * basic idea:
 * 	classical 2d boolean matrix to record s.substring(j,i + 1) is palindrome or not
 * 	based on DP idea
 * 	this trick is also used in palindrome partitioning 2 MinCut DP
 * 
 * time = O(n ^ 2)
 * space = O(n ^ 2)
 */
public class LongestPalindromeSubstring {
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		boolean[][] res = new boolean[s.length()][s.length()];
		int globalMax = 0;
		int start = 0;
		int end = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if ((j + 1 > i - 1 || res[j + 1][i - 1]) && s.charAt(j) == s.charAt(i)) {
					res[j][i] = true;
					if (i - j >= globalMax) {
						globalMax = i - j;
						start = j;
						end = i;
					}
				}
			}
		}
		return s.substring(start, end + 1);
	}
}
