package GoldmanSachs;
/**
 * 
 * @author @Yifeng
 * 	You are climbing a stair case. It takes n 
 * 	steps to reach to the top.
	Each time you can either climb 1, 2 or 3 steps. In how many 
	distinct ways can you climb to the top?
 */
/*
 * time = O(n) for iterating n times
 * space = O(n) for dp[]
 */
public class ClimbingStairs3Steps {
	public int countWays(int n) {
		// base case
		if (n <= 2) {
			return n;
		}
		if (n == 3) {
			return 4;
		}
		// dp[i] means given i steps, there are dp[i] distinct ways to climb the stairs
		// ways could be quite huge so 
		// use long to store the element
		long[] dp = new long[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		return (int)dp[n];
	}
	
	public static void main(String[] args) {
		int n = 4;
		long res = new ClimbingStairs3Steps().countWays(n);
		System.out.println(res);
	}
}
