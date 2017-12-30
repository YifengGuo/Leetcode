package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
	 Given N pairs of parentheses "()", return a list with all the valid permutations.
	
	Assumptions
	
	N >= 0
	Examples
	
	N = 1, all valid permutations are ["()"]
	N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
	N = 0, all valid permutations are [""]
 *
 */
/*
 * basic idea: DFS 
 * 		on each layer we must add one parenthesis
 * 		what we only focus is if we can add left or right one
 * 		constrains: 1. before addition, left must < n
 * 					2. before addition, right must < left
 * 		both constrains guarantee the permutation of parenthesis is valid
 * 
 * 	Time = O(2 ^ n)
 * 	Space = O(n)
 * 		
 */
public class AllValidPermutationsOfParentheses1 {
	public List<String> validParentheses(int n) {
		List<String> res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		helper(res, n, 0, 0, sb);
		return res;
	}

	private void helper(List<String> res, int n, int left, int right, StringBuilder sb) {
		// base case
		if (left == n && right == n) {
			res.add(new String(sb));
			return;
		}
		if (left < n) {
			sb.append('(');
			helper(res, n, left + 1, right, sb);
			sb.deleteCharAt(sb.length() - 1);
		}

		if (right < left) {
			sb.append(')');
			helper(res, n, left, right + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		int n =3;
		List<String> res = new AllValidPermutationsOfParentheses1().validParentheses(n);
		for (String s : res) {
			System.out.println(s);
		}
	}

}
