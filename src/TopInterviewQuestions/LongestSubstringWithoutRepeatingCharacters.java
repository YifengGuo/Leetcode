package TopInterviewQuestions;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author yifengguo
 	
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


 */
public class LongestSubstringWithoutRepeatingCharacters {
	/*
	 * my basic idea solution which needs optimization
	 * time = O(n ^ 2)
	 * space = O(n)
	 */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 1;
        int res = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(start));
        while (end < s.length()) {
            if (set.add(s.charAt(end))) {
                res = Math.max(res, end - start + 1);
                end++;
            } else {
                set.clear();
                start++;
                set.add(s.charAt(start));
                end = start + 1;
            }
        }
        return res;
    }
    /*(
     * better solution using hashSet
     * time = O(n)
     * space = O(n)
     */
    public int lengthOfLongestSubstring2(String s) {
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	int res = 0;
    	Set<Character> set = new HashSet<>();
    	int start = 0;
    	int end = 0;
    	while (end < s.length()) {
    		if (set.add(s.charAt(end))) {
    			end++;
    			res = Math.max(res, set.size()); // compare res with current set.size()
    		} else {
    			set.remove(s.charAt(start++)); // keep removing from s.charAt(start) 
    			                               // until there is no duplicate element
    										   // this could keep potential character
    			                               // like case of "dvdf"
    		}
    	}
    	return res;
    }
}