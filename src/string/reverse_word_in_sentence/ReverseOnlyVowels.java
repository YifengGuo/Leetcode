package string.reverse_word_in_sentence;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author yifengguo
 * Only reverse the vowels('a', 'e', 'i', 'o', 'u') in a given string, the other characters should not be moved or changed.

	Assumptions:
	
	The given string is not null, and only contains lower case letters.
	Examples:
	
	"abbegi" --> "ibbega"


 */
public class ReverseOnlyVowels {
	// e i t h e r b a s e i p h o n e
	// e o t h i r b e s a e p h i n e
	public static final char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

	public String reverse(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] arr = input.toCharArray();
		int start = 0;
		int end = arr.length - 1;
		Set<Character> set = new HashSet<>();
		for (Character c : vowels) {
			set.add(c);
		}
		while (end >= start) {
			if (set.contains(arr[start]) && set.contains(arr[end])) {
				swap(arr, start, end);
				end--;
				start++;
			} else if (set.contains(arr[start])) {
				end--;
			} else if (set.contains(arr[end])) {
				start++;
			} else {
				end--;
				start++;
			}
		}
		return String.valueOf(arr);
	}

	private void swap(char[] arr, int a, int b) {
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
