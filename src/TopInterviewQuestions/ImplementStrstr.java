package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng
 	Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.


 */
public class ImplementStrstr {
	/*
	 * time = O(n ^ 2) for string.substring cost O(n) time
	 * space = O(1)
	 */
	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null) {
			return 0;
		}
		
		// needle cannot longer than haystack
		if (needle.length() > haystack.length()) {
			return -1;
		}
		
		// if one of strings length is 0, the onlu possible substring is ""
		// and its index must be 0
		if (haystack.length() == 0 || needle.length() == 0) {
			return 0;
		}
		
		// i <= s1.length - s2.length is limit
		for (int i = 0; i <= haystack.length() - needle.length(); i++) {
			if (haystack.substring(i, i + needle.length()).equals(needle)) {
				return i;
			}
		}
		return -1;
	}
}