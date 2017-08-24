package dp;
/**
 * 
 * @author @Yifeng
 * 	You are climbing a stair case. It takes n 
 * 	steps to reach to the top.
	Each time you can either climb 1 or 2 steps. In how many 
	distinct ways can you climb to the top?
 */
/*
 * basic idea: actually a Fibonacci problem
 * time = O(n)
 * space = O(n)
 */
public class ClimbingStairs {
	public int climbStairs(int n) {
		if (n <= 2) {
			return n;
		}
		int[] M = new int[n+1];
        // base case
		M[0] = 0;
		M[1] = 1;
		M[2] = 2;
		for (int i = 3; i <= n; i++) {
			M[i] = M[i - 1] + M[i - 2];
		}
		return M[n];
    }
}
