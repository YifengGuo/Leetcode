package recursion.tree.uniqueBST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * Given a string of numbers and operators, return all possible results from computing 
 * all the different possible ways to group numbers and operators. The valid operators are +, - and *.

	Example 1:
	
	Input: "2-1-1"
	Output: [0, 2]
	Explanation: 
	((2-1)-1) = 0 
	(2-(1-1)) = 2
	Example 2:
	
	Input: "2*3-4*5"
	Output: [-34, -14, -10, -10, 10]
	Explanation: 
	(2*(3-(4*5))) = -34 
	((2*3)-(4*5)) = -14 
	((2*(3-4))*5) = -10 
	(2*((3-4)*5)) = -10 
	(((2*3)-4)*5) = 10
 */
/*
 * divide and conquer and memorization
 */
public class DifferentWaysToAddParentheses {
	// to cache processed string -> integer in the history
	Map<String, List<Integer>> map = new HashMap<>();

	public List<Integer> diffWaysToCompute(String input) {
		if (map.containsKey(input)) {
			return map.get(input);
		}
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '-' || c == '+' || c == '*') {
				// divide and conquer
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i + 1));
				for (Integer v1 : left) {
					for (Integer v2 : right) {
						if (c == '-') {
							res.add(v1 - v2);
						} else if (c == '+') {
							res.add(v1 + v2);
						} else {
							res.add(v1 * v2);
						}
					}
				}
			}
		}
		// current substring is just a number
		if (res.isEmpty()) {
			res.add(Integer.parseInt(input));
		}
		// memorization
		map.put(input, res);
		return res;
	}
}
