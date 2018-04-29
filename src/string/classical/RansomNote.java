package string.classical;
/**
 * 
 * @author yifengguo
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, 
 * write a function that will return true if the ransom note can be constructed from the magazines ; 
 * otherwise, it will return false.

	Each letter in the magazine string can only be used once in your ransom note.
	
	Note:
	You may assume that both strings contain only lowercase letters.
	
	canConstruct("a", "b") -> false
	canConstruct("aa", "ab") -> false
	canConstruct("aa", "aab") -> true
 */
/*
 * basic idea:
 * 	26 character table to record character occurrence in each string
 * time = O(n)
 */
public class RansomNote {
	public boolean canConstruct(String ransomNote, String magazine) {
		if (ransomNote == null && magazine == null) {
			return true;
		}
		if (ransomNote == null || magazine == null) {
			return false;
		}
		int[] arr = new int[26];
		for (char c : ransomNote.toCharArray()) {
			arr[c - 'a']++;
		}
		for (char c : magazine.toCharArray()) {
			arr[c - 'a']--;
		}
		for (int i : arr) {
			if (i > 0) {
				return false;
			}
		}
		return true;
	}
}
