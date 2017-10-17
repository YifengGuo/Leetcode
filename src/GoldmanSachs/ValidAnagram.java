package GoldmanSachs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author guoyifeng 
 * 		   Given two strings s and t, write a function to determine if
 *         t is an anagram of s.
 * 
 *         For example, s = "anagram", t = "nagaram", return true. s = "rat", t
 *         = "car", return false.
 * 
 *         Note: You may assume the string contains only lowercase alphabets.
 * 
 *         Follow up: What if the inputs contain unicode characters? How would
 *         you adapt your solution to such case?
 */
/*
 * similar idea of finding first unique character in a string
 * use an array to record occurrence of a letter in string
 * time = O(n)
 * space = O(1)
 */
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		if (s.length() == 0 && t.length() == 0) {
			return true;
		}
		int[] table = new int[26];
		for (int i = 0; i < s.length(); i++) {
			table[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			table[t.charAt(i) - 'a']--;
		}
		for (int i = 0; i < table.length; i++) {
			if (table[i] != 0) {
				return false;
			}
		}
		return true;
	}
	/*
	 * if consider s and t contains unicode character
	 * we can use hash_map to record each character 
	 * occurrence
	 * time = O(n)
	 * space = O(n)
	 */
	public boolean isAnagramWithUnicode(String s, String t) {
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		if (s.length() == 0 && t.length() == 0) {
			return true;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), 1);
			} else {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			}
		}
		for	(int i = 0; i < t.length(); i++) {
			if (!map.containsKey(t.charAt(i))) {
				map.put(t.charAt(i), 1);
			} else {
				map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
			}
		}
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			if (e.getValue() != 0) {
				return false;
			}
		}
		return true;
	}
}
