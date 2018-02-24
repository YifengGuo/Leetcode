package string.reverse_word_in_sentence;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author yifengguo
 * Reverse the words in a sentence and truncate all heading/trailing/duplicate space characters.

	Examples
	
	“ I  love  Google  ” → “Google love I”
	
	Corner Cases
	
	If the given string is null, we do not need to do anything.
 */
public class ReverseWordsInASentence2 {
	public String reverseWords(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		input = input.trim(); // remove head and tail white spaces
		List<Character> list = new ArrayList<>();
		int idx = 0;
		// remove duplicate white spaces within the sentence, only add one into list
		while (idx < input.length()) {
			if (input.charAt(idx) != ' ') {
				list.add(input.charAt(idx));
				idx++;
			} else {
				list.add(input.charAt(idx));
				idx++;
				while (idx < input.length() && input.charAt(idx) == ' ') {
					idx++;
				}
			}
		}
		char[] arr = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		// reverse whole string
		// elgooG evol I
		// s
		// e
		reverse(arr, 0, arr.length - 1);
		// reverse each word in the reversed string
		int start = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != ' ' && (i == 0 || arr[i - 1] == ' ')) { // find word start
				start = i;
			}
			if (arr[i] != ' ' && (i == arr.length - 1 || arr[i + 1] == ' ')) { // find word end
				reverse(arr, start, i);
			}
		}
		return new String(arr);
	}

	private void reverse(char[] arr, int start, int end) {
		while (end > start) {
			swap(arr, start, end);
			end--;
			start++;
		}
	}

	private void swap(char[] arr, int a, int b) {
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
