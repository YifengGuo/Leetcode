package dynamicprogramming.cutting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author yifengguo
 	Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 	add spaces in s to construct a sentence where each word is a valid dictionary word. 
 	You may assume the dictionary does not contain duplicate words.

	Return all such possible sentences.
	
	For example, given
	s = "catsanddog",
	dict = ["cat", "cats", "and", "sand", "dog"].
	
	A solution is ["cats and dog", "cat sand dog"].
 */
/*
 * dfs + memorization
 * time = O(n ^ 3)
 * space = O(n ^ 3)
 */
public class DictionaryWord2 {
	public List<String> wordBreak(String s, List<String> wordDict) {
    	if (s == null || s.length() == 0) {
			return new ArrayList<>();
		}
        Set<String> set = new HashSet<>();
        for (String string : wordDict) {
            set.add(string);
        }
    	return helper(s, set, 0);
	}
	
	Map<Integer, List<String>> map = new HashMap<>();
	
	private List<String> helper(String s, Set<String> wordDict, int start) {
		// check memorization
		if (map.containsKey(start)) {
			return map.get(start);
		}
		List<String> res = new ArrayList<>();
		// base case for recursion
		if (start == s.length()) {
			res.add(""); // add "" as end flag
		}
		for (int end = start + 1; end <= s.length(); end++) { // O(n) time
			if (wordDict.contains(s.substring(start, end))) { // O(n) for substring()
				List<String> tmp = helper(s, wordDict, end);
				for (String string : tmp) { // O(n)
					res.add(s.substring(start, end) + (string.equals("") ? "" : " ") + string);
				}
			}
		}
		map.put(start, res); // memorize all the strings found when starting index is current start on original input string
		return res;
	}
	/*
	 * dfs is time exceeded if string is like "aaaaaaaaaa" and wordDict is like {"a", "aa", "aaa"}
	 * then the recursion branch factor will be so large and there are many redundant computations
	 */
//	public List<String> wordBreak(String s, List<String> wordDict) {
//    	if (s == null || s.length() == 0) {
//			return new ArrayList<>();
//		}
//    	List<String> res = new ArrayList<>();
//    	Set<String> set = new HashSet<>();
//    	for (String string : wordDict) {
//    		set.add(string);
//    	}
//    	boolean[] dp = new boolean[s.length() + 1];
//    	StringBuilder sb = new StringBuilder();
//    	helper(res, s, set, sb, 0);
//    	return res;
//    }
//    
//    private void helper(List<String> res, String s, Set<String> set, StringBuilder sb, int start) {
//    	if (s.length() <= 0) {
//    		res.add(sb.toString());
//    		return;
//    	}
//    	for (int i = 0; i < s.length(); i++) {
//    		if (set.contains(s.substring(0, i + 1))) {
//    			sb.append(s.substring(0, i + 1));
//    			if (i < s.length() - 1) {
//    				sb.append(" ");
//    			}
//    			helper(res, s.substring(i + 1, s.length()), set, sb, sb.length());
//    			sb.delete(start, sb.length());
//    		}
//    	}
//    }
    
    public static void main(String[] args) {
    	String s = "catsanddog";
    	List<String> wordDict = new ArrayList<>();
    	String[] dict = {"cat", "cats", "and", "sand", "dog"};
    	for (String s1 : dict) {
    		wordDict.add(s1);
    	}
    	List<String> res = new DictionaryWord2().wordBreak(s, wordDict);
    	for (String string : res) {
    		System.out.println(string);
    	}
    	System.out.println("end");
    }
}
