package TopInterviewQuestions;

/**
 * 
 * @author guoyifeng 
 * 		   Given a string, determine if it is a palindrome,
 *         considering only alphanumeric characters and ignoring cases.
 * 
 *         For example, "A man, a plan, a canal: Panama" is a palindrome. "race
 *         a car" is not a palindrome.
 * 
 *         Note: Have you consider that the string might be empty? This is a
 *         good question to ask during an interview.
 * 
 *         For the purpose of this problem, we define empty string as valid
 *         palindrome.
 */
/*
 * pay attention to null and empty string corner case
 * remember lower case and upper case relationship in ASCII
 */
public class IsPalindromeString {
	public boolean isPalindrome(String s) {
		if (s == null) {
			return false;
		}
		if (s.length() == 0) {
			return true;
		}
		int left = 0;
		int right = s.length() - 1;
		while (left < right) {
			if (isValid(s.charAt(left)) && isValid(s.charAt(right))) {
				if (toLowerCase(s.charAt(left)) == toLowerCase(s.charAt(right))) {
					left++;
					right--;
				} else {
					return false;
				}
			} else if (!isValid(s.charAt(left))) {
				left++;
			} else if (!isValid(s.charAt(right))) {
				right--;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean isValid(char c) {
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
			return true;
		}
		return false;
	}

	private char toLowerCase(char c) {
		if (c >= 'A' && c <= 'Z') {
			return (char) (c + 32);
		}
		return c;
	}

	public static void main(String[] args) {
		String s = "ab";
		System.out.println(new IsPalindromeString().isPalindrome(s));
		char c = 'A';
		System.out.println(new IsPalindromeString().toLowerCase(c));
	}
}
