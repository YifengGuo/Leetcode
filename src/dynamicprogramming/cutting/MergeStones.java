package dynamicprogramming.cutting;
/**
 * 
 * @author yifengguo
  We have a list of piles of stones, each pile of stones has a certain weight, 
  represented by an array of integers. In each move, we can merge two adjacent piles into one larger pile, 
  the cost is the sum of the weights of the two piles. We merge the piles of stones until we have only one pile left. 
  Determine the minimum total cost.

Assumptions

stones is not null and is length of at least 1
Examples

{4, 3, 3, 4}, the minimum cost is 28

merge first 4 and first 3, cost 7

merge second 3 and second 4, cost 7

merge two 7s, cost 14

total cost = 7 + 7 + 14 = 28
 */
/* 
 *        index   0   1   2   3
 *                4   3   3   4
 *                
 *    induction rule:    M[i][j] represents the minimum cost by merging from index i to index j
 *    base case: merge size = 1
 *    				 M[0][0] == M[1][1] == ... == M[i][i] = 0 which cannot be merged any further
 *    
 *    merge size = 2:
 *                   M[0][1] = A[0] + A[1] = 3 + 4 = 7
 *                   M[1][2] = A[1] + A[2] = 3 + 3 = 6
 *                   M[2][3] = A[2] + A[3] = 3 + 4 = 7
 *                   
 *    merge size = 3:
 *    				 M[0][2]: 
 *                          case 1: merge 0, 1 then merge 2 = M[0][1] + A[2] = 7 + 7 + 3 = 17
 *                          case 2: merge 1, 2 then merge 0 = M[1][2] + A[0] = 6 + 6 + 4 = 16
 *                          
 *    merge size = 4: 
 *    
 *    
 *    fill the 2d matrix:
 *             0  1  2  3
 *           0 0  7 16  28
 *           1 7  0  6  16
 *           2 16 6  0   7
 *           3 28 16 7   0
 *         
 */
public class MergeStones {
	/*
	 * solution 2: more elegant implementation
	 */
	public int minCost2(int[] stones) {
		if (stones == null) {
			return 0;
		}
		// M[i][j] represents the minimum cost to merge stones from index i to index j
		int[][] M = new int[stones.length][stones.length];
		// subSum[i][j] represents the partial sum from i to j
		int[][] subSum = new int[M.length][M.length];
		for (int i = 0; i < M.length; i++) {
			for (int j = i; j >= 0; j--) {
				if (i == j) {
					M[j][i] = 0; // base case
					subSum[j][i] = stones[i]; // current stones weight
				} else {
					subSum[j][i] = subSum[j][i - 1] + stones[i]; // grow subSum from memory cache and current merge weight
					M[j][i] = Integer.MAX_VALUE;
					for (int k = j; k <= i - 1; k++) { // k must be started from j to cover all possible merge cases
						M[j][i] = Math.min(M[j][i], M[j][k] + M[k + 1][i] + subSum[j][i]);
					}
				}
			}
		}
		return M[0][M.length - 1];
	}
	
	/*
	 * solution 1 prefixSum + blossom from center
	 * the code is not very concise and elegant
	 * time = O(n ^ 3)
	 * space = O(n ^ 2)
	 */
	public int minCost(int[] stones) {
		if (stones == null) {
			return 0;
		}
		int[][] M = new int[stones.length][stones.length];
		int[] prefix = createPrefixSumArray(stones);
		for (int i = 0; i < M.length; i++) {
			for (int j = i; j >= 0; j--) {
				if (i == j) {
					M[j][i] = 0; // base case
				} else if (i - j == 1) {
					M[j][i] = stones[j] + stones[i];
				} else {
					M[j][i] = Integer.MAX_VALUE;
					for (int k = j; k <= i - 1; k++) {
						M[j][i] = Math.min(M[j][i], M[j][k] + M[k + 1][i] + getPrefix(stones, prefix, j, i));
					}
					System.out.println(M[j][i]);
				}
				M[i][j] = M[j][i];
			}
		}
		return M[0][stones.length - 1];
	}

	private int[] createPrefixSumArray(int[] stones) {
		int[] prefixSum = new int[stones.length];
		int tmp = 0;
		for (int i = 0; i < stones.length; i++) {
			tmp += stones[i];
			prefixSum[i] = tmp;
		}
		return prefixSum;
	}

	private int getPrefix(int[] stones, int[] prefix, int start, int end) {
		return prefix[end] - prefix[start] + stones[start];
	}
	
	public static void main(String[] args) {
		MergeStones test = new MergeStones();
		int[] stones = new int[] {2,5,8,7,3,3};
		test.minCost(stones);
	}
}
