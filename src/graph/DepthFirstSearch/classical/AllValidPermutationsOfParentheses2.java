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
	Examples
	
	l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
 */

/* Basic idea:
 * for this problem, we have three kinds of parenthesis.
 * to construct valid parenthesis combination of three kinds of parenthesis:
 * 		1. For the same type of parenthesis, left_add_so_far > right_add_so_far
 * 		2. For the same type of parenthesis, its left parenthesis must match right parenthesis,
 * 		   no matter how many other parenthesis in between
 *             eg: [ xxxxxxxx ]  guarantee linear scan to check if stack top matches new added parenthesis
 *             	     all valid parentheses between the [] can be counteracted via stack pop and offer
 *             			eg: [{()}    <-]   {()} can be popped out from stack
 *             
 *       recursion tree:
 *       	                          root(empty)
 *                            /         |    |    |   |    \
 *    level 0                 (          )    [    ]    {   }
 *                           stack|| (
 *                        /    |   |   |   |   \    
 *                       (     )   [   ]   {   }         only ( ) [ { can be added, others are invalid 
 *                       															(] is invalid 
 *                       
 *    level 1
 *    ...
 */                      

/*
 * Solution detail:
 * 	case1: whenever add one kind of left parenthesis, add this left parenthesis to the path_prefix(StringBuilder), 
 * 		   and push to the stack. Update the reminder
 *  case2: whenever add one kind of right parenthesis, first check whether it matches the top of stack
 *  			case2.1 if matches, stack.pop() and path_prefix.add(right_parenthesis). Update reminder
 *  			case2.2 if not matches, then prune this branch(do nothing,NOT calling recursion function) 
 *  Don't forget recovering added parenthesis on last layer of recursion tree! 
 *  
 *  remember stack is only to store latest added left type parenthesis
 */

/*
 * time : O(6 ^ (l + m + n))
 * space : O(l + m + n)
 */
public class AllValidPermutationsOfParentheses2 {
	private final char[] PS = new char[]{'(', ')', '[', ']', '{', '}'};
	public List<String> permute(int l, int m, int n) {
		List<String> res = new ArrayList<>();
		int[] remain = new int[] {l, l, m, m, n, n};
		int maxLength = 2 * l + 2 * m + 2 * n;
		Deque<Character> stack = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		helper(res, maxLength, stack, remain, sb);
		
		return res;
	}
	
	private void helper(List<String> res, int maxLength, Deque<Character> stack, int[] remain, StringBuilder sb) {
		// base case
		if (sb.length() == maxLength) {
			res.add(sb.toString());
			return;
		}
		// run dfs on each kind of parenthesis and determine which one can be added into sb
		for (int i = 0; i < PS.length; i++) {
			if (i % 2 == 0) {  // if current parenthesis type is left, we could add it to sb directly as long as it has remaining
				if (remain[i] > 0) {  // current parenthesis must have remaining or we cannot run further process
					sb.append(PS[i]);
					stack.offerFirst(PS[i]);
					remain[i]--;
					helper(res, maxLength, stack, remain, sb); // dfs
					sb.deleteCharAt(sb.length() - 1); // backtracking
					stack.pollFirst(); // backtracking
					remain[i]++; // backtracking
				}
			} else {  // if current parenthesis type is right, we need to check
					  // if stack is empty and if stack.top() is its matched left one
				if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
					sb.append(PS[i]);
					stack.pollFirst(); // pop out latest left parenthesis for it is matched by new added right type parenthesis
					remain[i]--;
					helper(res, maxLength, stack, remain, sb);
					sb.deleteCharAt(sb.length() - 1); // backtracking
					stack.offerFirst(PS[i - 1]);  // backtracking, add polled left!! parenthesis back again
					remain[i]++; // backtracking
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int l = 1;
		int m = 1;
		int n = 1;
		List<String> res = new AllValidPermutationsOfParentheses2().permute(l, m, n);
		for (String s : res) {
			System.out.println(s);
		}
	}
}
