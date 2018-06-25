package math.pascal_triangle;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle1 {
	/*
	 * method 1: generate pascal triangle based on the relation between current row and last row
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if (numRows <= 0) {
			return res;
		}
		List<Integer> first = new ArrayList<>();
		first.add(1);
        res.add(first);
		for (int i = 1; i < numRows; i++) {
			List<Integer> cur = new ArrayList<>(i + 1);
            cur.add(1);
			for (int j = 1; j < res.get(i - 1).size(); j++) {
				cur.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
			}
            cur.add(1);
            res.add(cur);
		}
		return res;
	}
	
	/*
	 * method 1: bad solution by recursion
	 * large cost on time and space, not recommended
	 */
//	public List<List<Integer>> generate(int numRows) {
//		List<List<Integer>> res = new ArrayList<>();
//		if (numRows <= 0) {
//			return res;
//		}
//		List<Integer> first = new ArrayList<>();
//		first.add(1);
//		res.add(first);
//		if (numRows == 1) {
//			return res;
//		}
//		for (int i = 1; i < numRows; i++) {
//			res.add(helper(res.get(res.size() - 1)));
//		}
//		return res;
//	}
//
//	private List<Integer> helper(List<Integer> last) {
//		List<Integer> cur = new ArrayList<>();
//		cur.add(1);
//		for (int i = 1; i <= last.size() - 1; i++) {
//			cur.add(last.get(i - 1) + last.get(i));
//		}
//		cur.add(1);
//		return cur;
//	}
}
