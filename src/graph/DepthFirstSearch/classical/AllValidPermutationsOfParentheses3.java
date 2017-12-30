package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author yifengguo
  Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.
	
	Assumptions
	
	l, m, n >= 0
	valid pair must be like {[()]}
	priority () > [] > {}
 */
/*
 * follow up of AllValidPermutationsOfParentheses2
 * and remember stack is only to store left type parentheses
 * 
 * if parenthesis has priority, we only need to compare its priority with stack.pop() when adding left type parenthesis
 * so we just need to write a getPriority() to count each type of left parenthesis's priority
 * 
 * time = O(6 ^ (l + m + n))
 * space = O(l + m + n)
 */
public class AllValidPermutationsOfParentheses3 {
	private final char[] PS = new char[] {'(', ')', '[', ']', '{', '}'};
	
	public List<String> permute(int l, int m, int n) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		Deque<Character> stack = new LinkedList<>();
		int[] remain = new int[] {l, l, m, m, n, n};
		int maxLength = 2 * l + 2 * m + 2 * n;
		helper(res, remain, maxLength, stack, sb);
		return res;
	}
	
	private void helper(List<String> res, int[] remain, int maxLength, Deque<Character> stack, StringBuilder sb){
		// base case
		if (sb.length() == maxLength) {
			res.add(sb.toString());
			return;
		}
		
		for (int i = 0; i < PS.length; i++) {
			if (i % 2 == 0) {  // left type parenthesis
				if (remain[i] > 0) {
					if (stack.isEmpty()) {  // if stack is empty, we just add any kind of left parenthesis, validity will 
									        // be guaranteed when stack is not empty
						sb.append(PS[i]);
						stack.offerFirst(PS[i]);
						remain[i]--;
						helper(res, remain, maxLength, stack, sb);
						sb.deleteCharAt(sb.length() - 1);
						stack.pollFirst();
						remain[i]++;
					} else {
						if (getPriority(PS[i]) < getPriority(stack.peekFirst())) {  // only when current parenthesis's priority < stack.top() can we
							                                                        // have right to add it. {[()]} is valid
							sb.append(PS[i]);
							stack.offerFirst(PS[i]);
							remain[i]--;
							helper(res, remain, maxLength, stack, sb);
							sb.deleteCharAt(sb.length() - 1);
							stack.pollFirst();
							remain[i]++;
						}
					}
				}
			} else {  // right type parenthesis
				if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
					sb.append(PS[i]);
					stack.pollFirst(); // pop out the matched left type parenthesis
					remain[i]--;
					helper(res, remain, maxLength, stack, sb);
					sb.deleteCharAt(sb.length() - 1); // backtracking
					stack.offerFirst(PS[i - 1]); // backtracking, push again this left type parenthesis
					remain[i]++;
				}
			}
		}
	}
	
	private int getPriority(Character c) {
		if (c == '(') {
			return 0;
		}
		if (c == '[') {
			return 1;
		}
		if (c == '{') {
			return 2;
		}

		return -1;
	}
	
	public static void main(String[] args) {
		int l = 1;
		int m = 1;
		int n = 1;
		List<String> res = new AllValidPermutationsOfParentheses3().permute(l, m, n);
		for (String s : res) {
			System.out.println(s);
		}
	}
}
