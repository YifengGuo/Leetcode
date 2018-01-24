package dynamicprogramming.cutting;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author guoyifeng
 *	Given a word and a dictionary, determine if it can be composed by concatenating words 
 *from the given dictionary.

	Assumptions
	
	The given word is not null and is not empty
	The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
	Examples
	
	Dictionary: {“bob”, “cat”, “rob”}
	
	Word: “robob” return false
	
	Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
 */
/*
 * time = O(n ^ 3) for substring() == O(n)
 * space = O(n)
 */
public class DictionaryWord1 {
	public boolean canBreak(String input, String[] dict) {
		if (input == null || dict == null) {
			return false;
		}
		Set<String> set = new HashSet<>();
		for (String s : dict) {
			set.add(s);
		}
		boolean[] dp = new boolean[input.length() + 1];
		for (int i = 1; i < dp.length; i++) { // O(n)
			if (set.contains(input.substring(0, i))) {
				dp[i] = true;
				continue;
			}
			for (int j = 0; j < i; j++) { // O(n)
				if (dp[j] && set.contains(input.substring(j, i))) { // O(n)
					dp[i] = true;
					break;
				}
			}
		}
		return dp[input.length()];
	}
}
