package dynamicprogramming.cutting;
/**
 * 
 * @author yifengguo
  Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with 
  length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? m is 
  determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.

	Assumptions
	
	n >= 2
	Examples
	
	n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
 */
/*
 * demo:
 * 		 _ | _ _ _ _ _
		 _ _ | _ _ _ _ 
		 _ _ _ | _ _ _ 
		 _ _ _ _ | _ _ 
		 _ _ _ _ _ | _ 
		 
		 | means all the possible cut positions
		 right part varies from 1 to 5 and it exists objectively
		 so we do not have to mind this right part
		 we just need to find   argmax(value = max(dp[j], j) * (i - j))
		 when find largest value, this is max product
		 
		 outer loop is to grow i which is the cutting rope length
		 inner loop is to iterate all the possible cutting positions
		 and try to find max product under current rope length
 */
public class MaxProductOfCuttingRope {
	public int maxProduct(int length) {
		if (length <= 1) {
			return 0;
		}
		int[] dp = new int[length + 1];
		dp[0] = 0;
		dp[1] = 1;
		// for for loop is to iterate all the cases
		// dp[i] means the max possible product we can get with cutting i length rope
		// and to calculate dp[i], need another pointer j to iterate all the possible cut positions
		for (int i = 2; i < length + 1; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(Math.max(dp[j], j) * (i - j), dp[i]);
			}
		}
		return dp[length];
	}
}
