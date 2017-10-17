package GoldmanSachs;

/**
 * 
 * @author guoyifeng 
 * 		   Given a string, find the first non-repeating character in
 *         it and return it's index. If it doesn't exist, return -1.
 * 
 *         Examples:
 * 
 *         s = "leetcode" return 0.
 * 
 *         s = "loveleetcode", return 2. Note: You may assume the string contain
 *         only lowercase letters.
 * 
 * 
 */
/*
 * time = O(n)
 * space =O(1) for constant size of array
 */
public class FirstUniqueCharacterInAString {
	public int first(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}
		// record each lower case letter's occurrence in the string
		int[] occur = new int[26];
		// calculate each letter occurrence
		for (int i = 0; i < s.length(); i++) {
			occur[s.charAt(i) - 'a']++;
		}
		// find the first unique character in the string
		for (int i = 0; i < s.length(); i++) {
			if (occur[s.charAt(i) - 'a'] == 1) {
				return i; 
			}
		}
		return -1;
	}
}
