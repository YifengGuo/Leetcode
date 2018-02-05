package graph.BestFirstSearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author yifengguo
 * Given a dictionary containing many words, find the largest product of two words’ lengths, such that the two words do not share any common characters.

	Assumptions
	
	The words only contains characters of 'a' to 'z'
	The dictionary is not null and does not contains null string, and has at least two strings
	If there is no such pair of words, just return 0
	Examples
	
	dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)


 */
/*
 * time = O(n ^ 3)
 * space = O(n ^ 2)
 */
public class LargestProductOfLength {
	class Pair {
		int idx1;
		int idx2;
		int product;

		public Pair(int idx1, int idx2, int product) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.product = product;
		}
	}

	public int largestProduct(String[] dict) {
		if (dict == null || dict.length == 0) {
			return 0;
		}
		// sort the dict so that the longer string can be set in the beginning
		// which can satisfy the expansion and generation rule of maxHeap
		// what we need is to find the pair with largest product
		Arrays.sort(dict, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return 0;
				}
				return s1.length() > s2.length() ? -1 : 1;
			}
		});
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				if (p1.product == p2.product) {
					return 0;
				}
				return p1.product > p2.product ? -1 : 1;
			}
		});
		// step 1: initial state
		Pair start = new Pair(0, 1, dict[0].length() * dict[1].length());
		maxHeap.offer(start);
		// step 2: expansion / generation rule
		while (!maxHeap.isEmpty()) {
			Pair cur = maxHeap.poll();
			// step 3 termination condition
			if (hasNoCommon(dict[cur.idx1], dict[cur.idx2])) { // O(n ^ 2)
				return cur.product;
			}
			// generation
			if (cur.idx1 + 1 < dict.length) {
				maxHeap.offer(new Pair(cur.idx1 + 1, cur.idx2, dict[cur.idx1 + 1].length() * dict[cur.idx2].length()));
			}

			if (cur.idx2 + 1 < dict.length) {
				maxHeap.offer(new Pair(cur.idx1, cur.idx2 + 1, dict[cur.idx1].length() * dict[cur.idx2 + 1].length()));
			}
		}
		return 0; // cannot find the pair for each string in dict has common character
	}
	
	private boolean hasNoCommon(String s1, String s2) {
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
}
