package dynamicprogramming.LAS;

//c d f 
//a b c d e
//    0 1 2 3 4 5
// 0    a b c d e
// 1 c  0 0 1 0 0 
// 2 d  0 0 0 2 0
// 3 f  0 0 0 0 0
/*
 * basic idea: dp
 * time = O(n ^ 2)
 * space = O(n ^ 2)
 */
public class LongestCommonSubstring {
	public String longestCommon(String s, String t) {
		if (s == null || t == null || s.length() == 0 || t.length() == 0) {
			return "";
		}
		int len1 = s.length();
		int len2 = t.length();
		String[][] M = new String[len1][len2];
		String res = "";
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
				if (s.charAt(i) == t.charAt(j)) {
					if (i - 1 < 0 || j - 1 < 0 || M[i - 1][j - 1] == null) {
						M[i][j] = String.valueOf(s.charAt(i));
					} else {
						M[i][j] = M[i - 1][j - 1] + String.valueOf(s.charAt(i));
					}
				}
				if (M[i][j] != null && M[i][j].length() > res.length()) {
					res = M[i][j];
				}
			}
		}
		return res;
	}
}
