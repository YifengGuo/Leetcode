package dynamicprogramming._1d.buy_stock;
/**
 * 
 * @author guoyifeng
  Given an array of positive integers representing a stock’s price on each day. 
  On each day you can only make one operation: either buy or sell one unit of stock, 
  at any time you can only hold at most one unit of stock, and you can make at most
  2 transactions in total. Determine the maximum profit you can make.

	Assumptions
	
	The give array is not null and is length of at least 2
	Examples
	
	{2, 3, 2, 1, 4, 5, 2, 11}, the maximum profit you can make is (5 - 1) + (11 - 2) = 13
 */
/*
 * basic idea: DP
 *             traverse on each price and maintain lowest buy price and largest profit of 2 transactions
 */
public class BuyStock3 {
	public int maxProfit(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int firstTimeBuyCost = Integer.MIN_VALUE; 
		int firstTimeSellProfit = 0;
		int secondTimeBuyCost = Integer.MIN_VALUE;
		int secondTimeSellProfit = 0;

		for (int i = 0; i < array.length; i++) {
			firstTimeBuyCost = Math.max(firstTimeBuyCost, -array[i]); // set first time buy cost as a negative value
			                                                          // to get max profit, we are aiming to buy stocks when its price is as low as it can be
			                                                          // so Math.max(a, b) to find larger negative value is to find lowest buy price of stock
			firstTimeSellProfit = Math.max(firstTimeSellProfit, array[i] + firstTimeBuyCost); // calculate first time profit
			secondTimeBuyCost = Math.max(secondTimeBuyCost, firstTimeSellProfit - array[i]);  // when buy second stock, we have some profit, it should be 
			                                                                                  // removed by the second time cost
			secondTimeSellProfit = Math.max(secondTimeSellProfit, array[i] + secondTimeBuyCost); // no matter secondTimeBuyCost is negative or positive
			                                                                                     // array[i] + secondTimeBuyCost is right logic
		}
		return secondTimeSellProfit;
	}
}

