package dynamicprogramming.LAS;
/**
 * 
 * @author yifengguo
 * Description
	Find the length of longest common subsequence of two given strings.
	
	Assumptions
	
	The two given strings are not null
	Examples
	
	S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.
 */

//     0 1 2 3 4 5
//       a b c d e
// 0   0 0 0 0 0 0   index == 0 represents empty case
// 1 c 0 0 0 1 1 1
// 2 b 0 0 1 1 1 1
// 3 a 0 1 1 1 1 1
// 4 b 0 1 2 2 2 2
// 5 d 0 1 2 2 3 3
// 6 f 0 1 2 2 3 3
// 7 e 0 1 2 2 3 4
/*
 * basic idea: DP 
 * time = O(n ^ 2)
 * space = O(n ^ 2)
 */
public class LongestCommonSubsequence {
	public int longest(String s, String t) {
		if (s == null || t == null || s.length() == 0 || t.length() == 0) {
			return 0;
		}
		// M[i][j] represents the length of longest common subsequence
		// of s[0, i] and t[0,j]
		// base case: if one of string is empty, M[i][j] = 0
		// induction rule:
		// if a[i] == b[j]  -> M[i][j] = M[i - 1][j - 1] + 1
		// else              -> M[i][j] = Math.max(M[i][j - 1], M[i - 1][j])
		int[][] M = new int[s.length() + 1][t.length() + 1];
		for (int i = 1; i < M.length; i++) {
			for (int j = 1; j < M[0].length; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1] + 1;
				} else {
					M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
				}
			}
		}
		return M[M.length - 1][M[0].length - 1];
	}
}
