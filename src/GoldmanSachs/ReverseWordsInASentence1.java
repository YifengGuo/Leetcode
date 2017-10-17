package GoldmanSachs;
/**
 * 
 * @author guoyifeng
 	Given an input string, reverse the string word by word.
	
	For example,
	Given s = "the sky is blue",
	return "blue is sky the".
	
	What constitutes a word?
	A sequence of non-space characters constitutes a word.
	Could the input string contain leading or trailing spaces?
	Yes. However, your reversed string should not contain leading or trailing spaces.
	How about multiple spaces between two words?
	Reduce them to a single space in the reversed string.
 */
public class ReverseWordsInASentence1 {
	public String reverseWords(String s) { 
		if (s == null || s.length() == 0) {
			return s;
		}
		char[] arr = s.toCharArray();
		// reverse whole sentence
		reverse(arr, 0, arr.length - 1);
		// reverse each word in the sentence
		int start = 0; // start char of a word
		for (int i = 0; i < arr.length; i++) {
			// find the index of start of word
			if (arr[i] != ' ' && (i == 0 || arr[i - 1] == ' ')) {
				start = i;
			}
			if (arr[i] != ' ' && (i == arr.length - 1 || arr[i + 1] == ' ')) {
				// current i is end of a word, reverse from start and end
				reverse(arr, start, i);
			}
		}
		// now words are in right order, then need to remove extra spaces
		return cleanSpaces(arr);
	}
	
	private void reverse(char[] arr, int left, int right) {
		while (left < right) {
			swap(arr, left++, right--);
		}
	}
	
	private void swap(char[] arr, int a, int b) {
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	private String cleanSpaces(char[] arr) {
		int i = 0; // real index of result array
		int j = 0; // to remove spaces
		int n = arr.length;
		while (j < n) {
			while (j < n && arr[j] == ' ') { // skip a word's leading spaces
				j++;
			}
			while (j < n && arr[j] != ' ') { // update characters of a word in array
				arr[i++] = arr[j++];
			}
			while (j < n && arr[j] == ' ') { // skip a word's tailing spaces
				j++;
			}
			if (j < n) {
				arr[i++] = ' '; // add only one space after a word
			}
		}
		return new String(arr).substring(0, i);
	}
}
