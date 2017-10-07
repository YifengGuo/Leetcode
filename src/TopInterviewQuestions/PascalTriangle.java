package TopInterviewQuestions;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 	Given numRows, generate the first numRows of Pascal's triangle.

	For example, given numRows = 5,
	Return
	
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
]
 */
public class PascalTriangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if (numRows <= 0) {
			return res;
		}
		List<Integer> first = new ArrayList<>();
		first.add(1);
		res.add(first);
		if (numRows == 1) {
			return res;
		}
		for (int i = 1; i < numRows; i++) {
			res.add(helper(res.get(res.size() - 1)));
		}
		return res;
	}

	private List<Integer> helper(List<Integer> last) {
		List<Integer> cur = new ArrayList<>();
		cur.add(1);
		for (int i = 1; i <= last.size() - 1; i++) {
			cur.add(last.get(i - 1) + last.get(i));
		}
		cur.add(1);
		return cur;
	}
	
	public static void main(String[] args) {
		int n = 10;
		List<List<Integer>> res = new PascalTriangle().generate(n);
		for (List<Integer> list : res) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}