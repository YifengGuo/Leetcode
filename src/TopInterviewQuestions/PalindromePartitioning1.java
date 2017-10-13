package TopInterviewQuestions;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning1 {
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return res;
		}
		boolean[][] isP = new boolean[s.length()][s.length()];
		// construct boolean matrix cache if [j,i] is palindrome or not
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || isP[j + 1][i - 1])) {
					isP[j][i] = true;
				}
			}
		}
		List<String> cur = new ArrayList<>();
		helper(res, cur, isP, 0, s);
		return res;
	}

	// dfs helper function
	private void helper(List<List<String>> res, List<String> cur, boolean[][] isP, int start, String s) {
		// base case
		if (start == s.length()) {
			res.add(new ArrayList<String>(cur));
			return;
		}
		for (int i = start; i < s.length(); i++) {
			if (isP[start][i]) {
				cur.add(s.substring(start, i + 1)); // [start, i] is palindrome, add it to cur
				helper(res, cur, isP, i + 1, s); // move start to next position
// System.out.println(cur.size());
				cur.remove(cur.size() - 1);  // backtracking
// System.out.println(cur.size());
			}
		}
	}
	
	public static void main(String[] args) {
		String a = "aab";
		List<List<String>> res = new PalindromePartitioning1().partition(a);
		for (List<String> list : res) {
			for (String s : list) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}	
}
