package TopInterviewQuestions;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 * Find all common elements in 3 sorted arrays.

	Assumption s
	
	The 3 given sorted arrays are not null
	There could be duplicate elements in each of the arrays
	Examples
	
	A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
 */
/*
 * basic idea: since three arrays are already sorted, so 
 *             we can use 3 pointers to track current element
 *             and decide which one needs to advance
 */
public class CommonElementsInThreeSortedArrays {
	public List<Integer> common(int[] a, int[] b, int[] c) {
		List<Integer> res = new ArrayList<>();
		if (a == null || b == null || c == null) {
			return res;
		}
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < a.length && j < b.length && k < c.length) {
			if (a[i] == b[j] && b[j] == c[k]) {
				res.add(a[i]);
				i++;
				j++;
				k++;
			} else if (a[i] < b[j]) {
				i++;
			} else if (b[j] < c[k]) {
				j++;
			} else { // a[i] >= [bj] && b[j] >= c[k], so move k
				k++;
			}
		}
		return res;
	}
}
