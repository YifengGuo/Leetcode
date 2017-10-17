package GoldmanSachs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author yifengguo
 *	Given a string, find out all the non-duplicate substrings with length of k
 *	For example: given caaab, k = 2, then return "aa", "ab","ca"
 */
/*
 * time = O(n ^ 2) 
 * space = O(n)
 */
public class AllSubstringsWithLengthK {
	public String[] allSubstrings(String s, int k) {
		if (s == null || s.length() == 0 || k <= 0) {
			return new String[] {};
		}
		Set<String> set = new HashSet<>();
		for (int i = 0; i < s.length() - 1; i++) {
			if (!set.contains(s.substring(i, i + 2))) {
				set.add(s.substring(i, i + 2)); // s.substring() cost O(n) time after java 7
												// before the behaviour of created new object refers to original one O(1)
			}
		}
		Iterator<String> it = set.iterator();
		String[] res = new String[set.size()];
		int count = 0;
		while (it.hasNext()) {
			res[count++] = it.next();
		}
		return res;
	}
	
	public static void main(String[] args) {
		String s = "caaabasreafasda";
		int k = 2;
		String[] res = new AllSubstringsWithLengthK().allSubstrings(s, k);
		for (String s1 : res) {
			System.out.print(s1 + " ");
		}
	}
}
