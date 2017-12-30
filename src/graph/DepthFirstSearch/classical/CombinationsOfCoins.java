package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author yifengguo
 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), 
 * get all the possible ways to pay a target number of cents.

	Arguments
	
	coins - an array of positive integers representing the different denominations of coins, 
	there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
	target - a non-negative integer representing the target number of cents, eg. 99
	Assumptions
	
	coins is not null and is not empty, all the numbers in coins are positive
	target >= 0
	You have infinite number of coins for each of the denominations, you can pick any number of the coins.
	Return
	
	a list of ways of combinations of coins to sum up to be target.
	each way of combinations is represented by list of integer, the number at each index means the number of 
	coins used for the denomination at corresponding index.
	Examples
	
	coins = {2, 1}, target = 4, the return should be
	
	[
	
	  [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)
	
	  [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)
	
	  [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)

]
 */
/*
 * basic idea: DFS
 *    recursion tree: e.g target = 99 , coins = [1, 5, 10, 25]
 *                                                                 99
 *    level 0(25):                        /                 |               |              \
 *                                     0 * 25(rem=99)    1*25(rem = 74)   2*25(rem=49)     3*25(rem=24)
 *                                  /||||||\              /|||\
 *    level 1 (10)               0*10 1*10 ...9*10       0*10..7*10..       ...                 ...
 *    
 *    level 2 (5)               ...                      ...                ...                 ...
 *     
 *    level 3 (1)               ...                      ...                ...                 ...
 *    
 *    
 *    How many levels does recursion tree have?  --->  4 levels, each level represents one type of coin
 *    How many different states are there on each level ?  --->   current layer target / coins[level], means at this 
 *                                                                level, at most we can choose from this type coin
 *                                                                
 *                                                                
 *                                                                
 *    If we still use basic DFS idea that each layer we choose from 4 types of coins, and choose at most 99 times
 *    in this recursion tree, each node has 4 branches, and the layers of the tree will be 99.
 *    In time, we need 4 ^ 99 times computation, and if target is larget, then the space (height) of the tree will
 *    be quite large which may cause stack overflow.
 *    
 *    
 *    Comparison:
 *              better solution                                                        intuitive solution
 *    Time         O(99^4) at most 99 branches, 4 layers in total                        O(4^99) branch factor is 4 and 99 layers in total
 *    Space        O(4)                                                                  O(99)
 *    										     
 */
public class CombinationsOfCoins {
	public List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> res = new ArrayList<>();
		if (target < 0 || coins == null || coins.length == 0) {
			return res;
		}
		List<Integer> plan = new ArrayList<>();
		helper(res, plan, target, coins, 0);
		return res;
	}
	
	private void helper(List<List<Integer>> res, List<Integer> plan, int target, int[] coins, int level) {
		// base case
		// last level coins can make up the target
		if (level == coins.length - 1) { // reach last level
			if (target % coins[level] == 0) {
				plan.add(target / coins[level]);
				res.add(new ArrayList<>(plan));
				plan.remove(plan.size() - 1); // do not forget recover here!!
			}
			return;
		}
		// process on current layer
		int potentialNum = target / coins[level];
		for (int i = 0; i <= potentialNum; i++) { // 99 / 25 = 3, so choices are 0, 1, 2, 3, potentialNum is inclusive
			plan.add(i);
			helper(res,plan, target - coins[level] * i, coins, level + 1);
			plan.remove(plan.size() - 1);
		}
	}
}
