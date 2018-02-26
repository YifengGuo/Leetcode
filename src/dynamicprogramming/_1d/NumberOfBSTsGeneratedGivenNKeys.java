package dynamicprogramming._1d;
/**
 * 
 * @author yifengguo
 * Find the number of different Binary Search Trees generated from 1-n.

	Example:
	
	Input: 3, Return: 5
 */
/*
 * basic idea: DP to cache historical combination result
 * DEMO
 *      n = 1:   1                                         1
 *      n = 2    1          2                              2
 *                \        /
 *                2       1
 *                
 *                
 *      n = 3     1, 2, 3
 *              1 as root:      1
 *                                \
 *                                dp[2]
 *                             
 *              2 as root:        2
 *                             /      \         
 *                            dp[1] x  dp[2]             left and right substree are independent cases to each other, so we use multiplication 
 *                            
 *              3 as root:          3
 *                                 /
 *                                dp[2]
 *                                
 *         so induction rule is:
 *                             dp[i] += dp[j] * dp[i - j - 1]   for all j ranges from [0, i) and dp[0] we initialize as 1
 *                             
 *         time = O(n ^ 2)
 *         space = O(n)
 *                                                        
 */
public class NumberOfBSTsGeneratedGivenNKeys {
	public int numOfTrees(int n) {
		// sanity check and corner case
		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		// dp[i] represents the number of generated BSTs given i keys
		// dp[i] indcution rule works for n >= 3
		int[] dp = new int[n + 1];
		// base case
		dp[0] = 1; // for null key
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}
		return dp[n];
	}
}
