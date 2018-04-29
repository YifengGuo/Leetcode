package string.classical;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yifengguo
 * Write a function to find the longest common prefix string amongst an array of strings.

	If there is no common prefix, return an empty string "".
	
	Example 1:
	
	Input: ["flower","flow","flight"]
	Output: "fl"
	Example 2:
	
	Input: ["dog","racecar","car"]
	Output: ""
	Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {
	private static final int count = 1; // to fix the size of set. if size of set is not 1, then return sb
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Set<Character> set = new HashSet<>(); // to check if current position's characters are all identical
		int index = 0;

		while (true) {
			for (int i = 0; i < strs.length; i++) {
				if (index >= strs[i].length()) { // if index is out of one of strings in the array, then return sb
					return sb.toString();
				}
				Character c = strs[i].charAt(index);
				set.add(c);
			}
			if (set.size() != count) {
				return sb.toString();
			}
			set.clear(); // remove all the characters of previous position
			sb.append(strs[0].charAt(index++));
		}
	}
}
