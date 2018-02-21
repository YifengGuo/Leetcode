package dynamicprogramming._1d.buy_stock;
/**
 * 
 * @author guoyifeng
 * Given an array of positive integers representing a stock’s price on each day. 
 * On each day you can only make one operation: either buy or sell one unit of stock 
 * and you can make at most 1 transaction. Determine the maximum profit you can make.

Assumptions
	
	The given array is not null and is length of at least 2.
	Examples
	
	{2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4


 */
//2 3 2 1 4 5
//0 1 1 1 3 4
/*
 * time = O(n ^ 2)
 * space = O(1)
 */
public class BuyStock1 {
	public int maxProfit(int[] array) {
		// maxDiff represents the max difference of highest price
		// and historical min_price
		int globalMax = 0;
		int max_diff = 0;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				int tmp = array[i] - array[j];
				max_diff = Math.max(tmp, max_diff);
			}
			globalMax = Math.max(globalMax, max_diff);
		}
		return globalMax;
	}
}
