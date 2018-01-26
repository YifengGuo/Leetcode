package dynamicprogramming._2d;
/**
 * 
 * @author yifengguo
 	Given two strings of alphanumeric characters, determine the minimum number of Replace, 
 	Delete, and Insert operations needed to transform one string into the other.

	Assumptions
	
	Both strings are not null
	Examples
	
	string one: “sigh”, string two : “asith”
	
	the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).


 */
/*
 * basic idea:
 * 		s1 = xxxxxxx   size = m
 *      s2 = yyyy      size = n         
 *      			smaller problem: 
 *      						case 1: m - 1, n
 *      						case 2: m, n - 1
 *      						case 3: m - 1, n - 1
 *      
 *  base case: dp[0][0] = 0, dp[m][0] = m, dp[0][n] = n
 *  induction rule: dp[i][j]: represents minimal actions to transform  first i characters in s1 to first j characters in s2
 *  
 *  dp[i][j]
 *  	case 1: do nothing if s1[i] = s2[j], dp[i][j] = dp[i - 1][j - 1]
 *  	case 2: dp[i][j] = dp[i - 1][j] + 1     for delete
 *  	case 3: dp[i][j] = dp[i][j + 1] + 1     for insert
 *  	case 4: dp[i][j] = dp[i - 1][j - 1] + 1 for replace
 *  
 *  	then find min(case1, case2, case3, case4)
 *  	return dp[m + 1][n + 1] for first place is for empty, where m is the length of s1 and n is the length of s2
 *  	
 *  	when fill the 2d matrix, only have to look at the grow rule, (case1, case2, case3, case4)
 *  
 *  DEMO:    0 s g h j
 *        0  0 1 2 3 4 
 *        a  1 1 2 3 4 
 *        s  2 1 2 3 4
 *        d  3 2 2 3 4 
 *        f  4 3 3 3 4 
 *        
 *    time = O(m * n)
 *    space = O(m * n)
 */
public class EditDistance {
	public int editDistance(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return -1;
		}
		int m = s1.length();
		int n = s2.length();
		if (m == 0)
			return n;
		if (n == 0)
			return m;
		int[][] dp = new int[s1.length() + 1][s2.length() + 1]; // index 0 is for empty case
		// base case
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 && j == 0) { // both empty string
					dp[i][j] = 0;
				} else if (i == 0) { // s2 is empty
					dp[i][j] = j;
				} else if (j == 0) { // s1 is empty
					dp[i][j] = i;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // case 1: current two characters are identical, inherit original result
					dp[i][j] = dp[i - 1][j - 1];
				} else { // find minimum among other 3 cases and + 1
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
}
