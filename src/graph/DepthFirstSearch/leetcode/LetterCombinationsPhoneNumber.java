package graph.DepthFirstSearch.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yifengguo 
 *         Given a string containing digits from 2-9 inclusive, return
 *         all possible letter combinations that the number could represent.
 * 
 *         A mapping of digit to letters (just like on the telephone buttons) is
 *         given below. Note that 1 does not map to any letters.
 *         
 *         Input: "23"
 *         Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsPhoneNumber {
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return res;
		}
		char[] tmp = digits.toCharArray();
		int[] arr = new int[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			arr[i] = charToInt(tmp[i]);
		}
		Map<Integer, List<Character>> map = setupMap();
		List<List<Character>> lists = new ArrayList<>();
		for (int i : arr) {
			lists.add(map.get(i));
		}
		StringBuilder sb = new StringBuilder();
		helper(res, lists, 0, sb);
		return res;
	}

	private int charToInt(char c) {
		return c - '0';
	}
	
	/**
	 * create a map that mapping phone number with letters on it
	 * @return
	 */
	private Map<Integer, List<Character>> setupMap() {
		Map<Integer, List<Character>> map = new HashMap<>();
		int count = 0;
		for (int i = 2; i <= 9; i++) {
			List<Character> list = new ArrayList<>();
			if (i == 7 || i == 9) {
				for (int j = 0; j < 4; j++) {
					list.add((char) (count + 'a'));
					count++;
				}
			} else {
				for (int k = 0; k < 3; k++) {
					list.add((char) (count + 'a'));
					count++;
				}
			}
			map.put(i, list);
		}
		return map;
	}
	

	private void helper(List<String> res, List<List<Character>> lists, int level, StringBuilder sb) {
		// base case
		if (level == lists.size()) {
			res.add(sb.toString());
			return;
		}
        
		/* 
		 * loop on each row of lists
		 * a b c
		 * d e f
		 * g h i
		 */
		for (int i = 0; i < lists.get(level).size(); i++) {
			sb.append(lists.get(level).get(i));
			helper(res, lists, level + 1, sb);
			sb.deleteCharAt(sb.length() - 1); // backtracking
		}

	}
}
