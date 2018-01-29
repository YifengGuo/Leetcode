package dynamicprogramming.cutting;
/**
 * 
 * @author yifengguo
  Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition 
  is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.

	Assumptions
	
	The given string is not null
	Examples
	
	“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
	
	The minimum number of cuts needed is 3.


 */
/*
 * time = O(n ^ 2)
 * space = O(n ^ 2)
 */
public class MinimumCutsForPalindromes {
	public int minCuts(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// M[i] represents minimum cut by the end of i - 1(inclusive) to
		// make each part is palindrome
		int[] M = new int[s.length() + 1];
		M[0] = 0;
		boolean[][] isPalindrome = checkPalindrome(s);
		for (int i = 1; i <= s.length(); i++) {
			M[i] = i; // initialize M[i] as i for at most to cut i times
			if (isPalindrome[0][i - 1]) { // if first i substring is palindrome, do not need
				                      // move on, do not need to cut
				M[i] = 0;
				continue;
			} else {
				for (int j = 0; j < i; j++) {
					if (isPalindrome[j][i - 1]) { // [j, i-1] is right smaller section
						M[i] = Math.min(M[j] + 1, M[i]); // we can directly get M[j] from memory
					}
				}
			}
		}
		return M[s.length()];
	}
	
	/*
	 * to check if s.substring[j, i] is palindrome
	 */
	private boolean[][] checkPalindrome(String s) {
		boolean[][] dict = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || dict[j + 1][i - 1])) {
					dict[j][i] = true;
				}
			}
		}
		return dict;
	}
	/*
	 * solution 2 : exclusive border
	 */
//	public int minCuts(String s) {
//		if (s == null || s.length() == 0) {
//			return 0;
//		}
//		// M[i] represents minimum cut by the end of i - 1 to
//		// make each part is palindrome
//		int[] M = new int[s.length() + 1];
//		boolean[][] isPalindrome = checkPalindrome(s);
//		for (int i = 1; i <= s.length(); i++) {
//			M[i] = i;
//			for (int j = 1; j <= i; j++) {
//				if (isPalindrome[j][i]) {
//					M[i] = Math.min(M[j - 1] + 1, M[i]);
//				}
//			}
//		}
//		return M[s.length()] - 1; // because [0][0] is always a parlindrome and is empty, so 
//		                          // we add 1 at first inevitably, so we need to subtract by 1 on result 
//	}
//	
//	/*
//	 * to check if s.substring(j - 1, i - 1] is palindrome
//	 */
//	private boolean[][] checkPalindrome(String s) {
//		boolean[][] dict = new boolean[s.length() + 1][s.length() + 1];
//		for (int i = 1; i <= s.length(); i++) {
//			for (int j = 1; j <= i; j++) {
//				if (s.charAt(i - 1) == s.charAt(j - 1) && (j + 1 > i - 1 || dict[j + 1][i - 1])) {
//					dict[j][i] = true;
//				}
//			}
//		}
//		return dict;
//	}
	
	public static void main(String[] args) {
		String s = "abcbd";
		MinimumCutsForPalindromes test = new MinimumCutsForPalindromes();
//		boolean[][] dict = test.checkPalindrome(s);
//		for (int i = 0; i < dict.length; i++) {
//			for (int j = 0; j < dict[0].length; j++) {
//				System.out.print(dict[i][j] + " ");
//			}
//			System.out.print("\n");
//		}
		int minCuts = test.minCuts(s);
		System.out.println(minCuts);
	}
}