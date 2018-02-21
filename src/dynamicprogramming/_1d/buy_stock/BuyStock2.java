package dynamicprogramming._1d.buy_stock;
/**
 * 
 * @author guoyifeng
 * Given an array of positive integers representing a stock’s price on each day. 
 * On each day you can only make one operation: either buy or sell one unit of stock, 
 * you can make as many transactions you want, but at any time you can only hold at most one unit of stock. 
 * Determine the maximum profit you can make.

	Assumptions
	
	The give array is not null and is length of at least 2
	Examples
	
	{2, 3, 2, 1, 4, 5}, the maximum profit you can make is (3 - 2) + (5 - 1) = 5


 */
// 2 3 4 5 6 2 1 4 5
//               s
//                 e
public class BuyStock2 {
	/*
	 * method 1: two pointers
	 *        start and end
	 *        to find ascending subarray and accumulate the total profit
	 *        
	 *        time = O(n)
	 *        space = O(1)
	 */
	public int maxProfit(int[] array) {
		// Write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		int s = 0; // start pointer
		int e = 1; // end pointer
		int max = 0;
		while (e < array.length) {
			// if not ascending, move to pointers directly
			if (array[e] < array[s]) {
				e++;
				s++;
			} else { // if ascending 
				if (e + 1 < array.length && array[e + 1] >= array[e]) { // if next is still non-descending, keep moving e
					e++;
				} else if (e + 1 < array.length && array[e + 1] < array[e]) { // if next starts descend, accumulate max
					max += array[e] - array[s];
					s = e;
					e++;
				} else if (e + 1 >= array.length) { // if e is at the last element of array
					if (array[e] >= array[e - 1]) { // if current subarray is ascending, accumulate max
						max += array[e] - array[s];
						e++;
					} else { // else, move e and terminal the loop
						e++;
					}
				}
			}
		}
		return max;
	}
}
