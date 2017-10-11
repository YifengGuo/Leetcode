package TopInterviewQuestions;

/**
 * 
 * @author yifengguo 
 *         Given an input string, reverse the string word by word. A
 *         word is defined as a sequence of non-space characters.
 * 
 *         The input string does not contain leading or trailing spaces and the
 *         words are always separated by a single space.
 * 
 *         For example, Given s = "the sky is blue", return "blue is sky the".
 * 
 *         Could you do it in-place without allocating extra space?
 */
/*
 * basic idea:	I love Yahoo problem
 * 			reverse whole sentence
 *          locate start and end of a word and reverse the word
 *      time = O(n ^ 2)
 *      space = O(1)
 */
public class ReverseWordsInAString {
	public void reverseWords(char[] s) {
		if (s == null || s.length <= 1) {
			return;
		}
		reverse(s, 0, s.length - 1);
		int start = 0;
		for (int i = 0; i < s.length; i++) {
			// find the start of a word
			if (s[i] != ' ' && (i == 0 || s[i - 1] == ' ')) {
				start = i;
			}
			// find the end of a word
			else if (s[i] != ' ' && (i == s.length - 1 || s[i + 1] == ' ')) {
				reverse(s, start, i);
			}
		}
	}

	private void reverse(char[] s, int start, int end) {
		while (start < end) {
			swap(s, start, end);
			start++;
			end--;
		}
	}

	private void swap(char[] s, int a, int b) {
		char tmp = s[a];
		s[a] = s[b];
		s[b] = tmp;
	}
}
