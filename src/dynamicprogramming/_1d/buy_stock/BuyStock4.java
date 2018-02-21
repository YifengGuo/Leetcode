package dynamicprogramming._1d.buy_stock;

import java.util.Arrays;

public class BuyStock4 {
	/*
	 * method 1: DP
	 * similar idea with buy stock3
	 * time = O(n * k)
	 * space = O(k)
	 */
	public int maxProfit(int[] array, int k) {
		if (array == null || array.length == 0 || k <= 0) {
			return 0;
		}
		int[] buyCost = new int[k]; // buyCost[i] represents ith time buy cost
		int[] profit = new int[k + 1]; // profit[i] represents ith time profit after the ith buy
		// if k >= array.length / 2, then we can even buy and sell every 2 days, so do the accumulation addition directly
		if (k >= array.length / 2) {
			int max_profit = 0;
			for (int i = 1; i < array.length; i++) {
				if (array[i] >= array[i - 1]) {
					max_profit += array[i] - array[i - 1];
				}
			}
			return max_profit;
		}
		Arrays.fill(buyCost, Integer.MIN_VALUE);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < k; j++) {
				buyCost[j] = Math.max(buyCost[j], profit[j] - array[i]); // jth buy cost
				profit[j + 1] = Math.max(profit[j + 1], array[i] + buyCost[j]); // next time sell's profit after jth buy
			}
		}
		return profit[k];
	}
}