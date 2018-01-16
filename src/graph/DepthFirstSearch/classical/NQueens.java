package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yifengguo 
 * 		   Get all valid ways of putting N Queens on an N * N
 *         chessboard so that no two Queens threaten each other.
 * 
 *         Assumptions
 * 
 *         N > 0 Return
 * 
 *         A list of ways of putting the N Queens Each way is represented by a
 *         list of the Queen's y index for x indices of 0 to (N - 1) 
 *         
 *         Example:
 * 
 *         N = 4, there are two ways of putting 4 queens:
 * 
 *         [1, 3, 0, 2] --> the Queen on the first row is at y index 1, the
 *         Queen on the second row is at y index 3, the Queen on the third row
 *         is at y index 0 and the Queen on the fourth row is at y index 2.
 * 
 *         [2, 0, 3, 1] --> the Queen on the first row is at y index 2, the
 *         Queen on the second row is at y index 0, the Queen on the third row
 *         is at y index 3 and the Queen on the fourth row is at y index 1.
 */
/*
 * time = O(n! * n)
 * space = O(n)
 */
public class NQueens {
	public List<List<Integer>> nqueens(int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (n <= 0) {
			return res;
		}
		List<Integer> plan = new ArrayList<>();
		helper(res, plan, n, 0);
		return res;
	}
	
	private void helper(List<List<Integer>> res, List<Integer> plan, int n, int level) {
		// base case
		if (level == n) {
			res.add(new ArrayList<>(plan));
			return;
		}
		// current layer process
		for (int i = 0; i < n; i++) {  // look for position on each column
			if (isValid(plan, i, level)) { // validity check
				plan.add(i);
				helper(res, plan, n, level + 1); // dfs, move to next row
				plan.remove(plan.size() - 1); // backtracking
			}
		}
	}
	
	private boolean isValid(List<Integer> plan, int i, int level) {
		// check vertical
		if (plan.contains(i)) {
			return false;
		}
		// check orthogonal
		for (int row = 0; row < plan.size(); row++) { // O(n) time for validity check here
			int vertical_dis = level - row;
			int horizontal_dis = Math.abs(i - plan.get(row));
			if (vertical_dis == horizontal_dis) {
				return false;
			}
		}
		return true;
	}
}
