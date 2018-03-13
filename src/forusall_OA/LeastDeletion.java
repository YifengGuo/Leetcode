package forusall_OA;

import java.util.Arrays;

/**
 * 
 * @author yifengguo
 * given a string, how to make characters in the string are in lexicographically ascending order by 
 * deleting as less as possible characters.
 * challenge: Time: O(n)
 *            Space: O(1) 
 */
public class LeastDeletion {
	public int least(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		s = s.toLowerCase();
		// dp[c] represents the longest ascending sequence of characters ended at c
		int[] dp = new int[26];
		Arrays.fill(dp, 1);
		int longest = 0;
//		for (int i = 1; i < s.length(); i++) {
//			for (int j = 0; j < i; j++) {
//				if (s.charAt(i) - 'a' > s.charAt(j) - 'a') {
//					dp[s.charAt(i) - 'a'] = Math.max(dp[s.charAt(j) - 'a'] + 1, dp[s.charAt(i) - 'a']);
//				}
//			}
//			longest = Math.max(dp[s.charAt(i) - 'a'], longest);
//		}
		for (int i = 0; i < s.length(); i++) {
			if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
				dp[i] = dp[i - 1];
			}
			int index = s.charAt(i) - 'a';
			if (i > 0 && s.charAt(i) - 'a' > s.charAt(i - 1) - 'a') {
				dp[index] = Math.max(dp[index - 1] + 1, dp[index]);
			} else {
				if (index == 0) {
					dp[index] = 1;
				} else if (dp[index - 1] > 1) {
					dp[index] = Math.max(dp[index - 1] + 1, dp[index]);
				} else {
					dp[index] = 1;
				}
			}
			longest = Math.max(dp[index], longest);
		}
		return (s.length() - longest);
	}
	
	public static void main(String[] args) {
		String s = "abbbbbbada";
		int res = new LeastDeletion().least(s);
		System.out.println(res);
	}
}
