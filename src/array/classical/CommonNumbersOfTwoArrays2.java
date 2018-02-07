package array.classical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author yifengguo
  Find all numbers that appear in both of two unsorted arrays.

	Assumptions
	
	Both of the two arrays are not null.
	In any of the two arrays, there could be duplicate numbers.
	Examples
	
	A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B)
 */
/*
 * sort and two pointers
 * time = O(nlogn)
 * space = O(1)
 */
public class CommonNumbersOfTwoArrays2 {
	public List<Integer> common(List<Integer> A, List<Integer> B) {
		List<Integer> res = new ArrayList<>();
		if (A == null || B == null || A.size() == 0 || B.size() == 0) {
			return res;
		}
		Collections.sort(A);
		Collections.sort(B);
		int a = 0;
		int b = 0;
		while (a < A.size() && b < B.size()) {
			if (A.get(a) == B.get(b)) {
				res.add(A.get(a));
				a++;
				b++;
			} else if (A.get(a) < B.get(b)) {
				a++;
			} else {
				b++;
			}
		}
		return res;
	}
}
