package recursion.tree;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafNumbers {
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		List<List<Integer>> allPaths = new ArrayList<>();
		List<Integer> plan = new ArrayList<>();

		helper(allPaths, plan, root);

		int sum = 0;

		for (List<Integer> path : allPaths) {
			sum += calculatePathSum(path);
		}

		return sum;
	}

	private void helper(List<List<Integer>> res, List<Integer> plan, TreeNode root) {
		if (root == null) {
			return;
		}
		plan.add(root.key);
		// when reaching at leaf node, add plan into res
		if (root.left == null && root.right == null) {
			res.add(new ArrayList<>(plan));
			plan.remove(plan.size() - 1); // recover
			return;
		}
		helper(res, plan, root.left);
		helper(res, plan, root.right);
		plan.remove(plan.size() - 1); // recover
	}

	private int calculatePathSum(List<Integer> path) {
		int res = 0;
		for (int i = 0; i < path.size(); i++) {
			res = res * 10 + path.get(i);
		}
		return res;
	}

}
