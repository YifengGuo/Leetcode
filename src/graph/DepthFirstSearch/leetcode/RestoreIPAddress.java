package graph.DepthFirstSearch.leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

	Example:
	
	Input: "25525511135"
	Output: ["255.255.11.135", "255.255.111.35"]

 */
public class RestoreIPAddress {
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		helper(res, sb, 0, 0, s);
		return res;
	}

	private void helper(List<String> res, StringBuilder sb, int index, int blockId, String s) {
		// base case
		if (blockId > 4) {
			return;
		}
		if (index == s.length() && blockId == 4) {
			res.add(sb.toString().substring(0, sb.length() - 1)); // delete last "." in the end
			return;
		}
		// 255.255.11.135
		// a valid ip address has 4 blocks
		// append each possible block combination one by one
		for (int i = index; i <= index + 2 && i < s.length(); i++) {
			String number = s.substring(index, i + 1);
			if (!isValid(number)) {
				continue;
			}
			sb.append(number).append(".");
			helper(res, sb, i + 1, blockId + 1, s); // current index is i, so next index is i + 1
			sb.setLength(sb.length() - (i + 1 - index + 1));
		}
	}

	private boolean isValid(String number) {
		// all number digits should be used and cannot be started with 0 if the block length > 1
		if ((number.length() > 1 && number.startsWith("0")) || Integer.parseInt(number) > 255) {
			return false;
		}
		return true;
	}
}