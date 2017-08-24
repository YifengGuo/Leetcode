package dp;

import java.util.List;

/**
 * 
 * @author @Yifeng
	Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s 
	can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary 
	does not contain duplicate words.

	For example, given
	s = "leetcode",
	dict = ["leet", "code"].
	
	Return true because "leetcode" can be segmented as "leet code".
 */

/*
 * time = O(n ^ 3) for substring() will cost O(n) and for for loop is O(n ^ 2)
 * space = O(n)
 */
public class WordBreak {
	public boolean wordBreak(String s, List<String> dict) {
		if (s == null || s.length() == 0) {
			return false;
		}
		// dp represents if substring[0,i] can be separated words or not
		boolean[] dp = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (dict.contains(s.substring(0, i + 1))) {
				dp[i] = true;
				continue;
			}
			for (int j = 0; j < i; j++) {
				if (dp[j] && dict.contains(s.substring(j + 1, i + 1))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length() - 1];
	}
}