package dynamicprogramming.cutting;
/**
 * 
 * @author yifengguo
 * There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. 
 * The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. 
 * Determine the minimum total cost to cut the stick into the defined pieces.

Examples

L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
 */
/*
 * basic idea: DP blossom from the center
 * 
 * 		analysis on why idea of left larger section + right smaller section cannot do?
 * 				Assume we apply the idea, then we have to grow from left to right and cache
 *              the minimum cost at each index 
 *              So assume M[i] represents from start to i, the minimum cost of cutting on current position i
 *              M[0] = 0
 *              M[1] = ? index 1 cannot be cut here
 *              M[2] = 0
 *              M[3] = ? index 3 cannot be cut here
 *              M[4] = M[2] + M[2] = 0
 *              M[5] = ?
 *              ..
 *              M[7] = M[3] + M[4] but we did not and cannot cache M[3] so here we find this idea does not work well on this problem
 *              
 *   
 *   Idea of blossom from the center
 *   M[i][j] represents from index i to index j, the minimum cost
 *   
 *   0  1    2   3   4   5  6  7    8  9  10                 array = {2 ,4 7} 
 *   | __ __ | __ __ | __ __ __ | __ __ __|
 *   
 *   base case: size = 1, at base case, each part of the wood cannot be cur further
 *              M[0][2] = M[2][4] = M[4][7] = M[7][10] = 0
 *              
 *              size = 2 [left = i, right = i + 2]
 *              M[0][4] = (A[4] - A[0])        +      M[0][2] + M[2][4]           ===== 4 + 0 + 0 = 4
 *                        cost of cutting 4
 *              M[2][7] = (A[7] - A[2])        +      M[2][4] + M[4][7]           ===== 5 + 0 + 0 = 5
 *                        cost of cutting 5
 *              M[4][10] = (A[10] - A[4])      +      M[4][7] + M[7][10]          ===== 6 + 0 + 0 = 6
 *                        cost of cutting 6                        
 *              
 *              
 *              
 *              size = 3 [left = i, right = i + 3]
 *              M[0][7]:
 *              	case 1: cut at index 2: M[0][7] = A[7] - A[0]           +          M[0][2] + M[2][7] = 7 + 0 + 5 = 12
 *                                             cost of cutting 7
 *                  case 2: cut at index 4: M[0][7] = A[7] - A[0]           +          M[0][4] + M[4][7] = 7 + 4 + 0 = 11
 *                                             cost of cutting 7
 *              			
 *              M[2][10]:
 *					case 1: cut at index 4: M[2][10] = A[10] - A[2]           +          M[2][4] + M[4][10] = 8 + 0 + 6 = 14
 *                                             cost of cutting 8
 *                  case 2: cut at index 7: M[2][10] = A[10] - A[2]           +          M[2][7] + M[7][10] = 8 + 5 + 0 = 13
 *                                             cost of cutting 8
 *                                             
 *              size = 4: A[10] - A[0] + Min(size 2 + size2, size 3 + size 1) = 10 + 4 + 6 = 20
 *              
 *              
 *     fill the 2D cache matrix      0 1 2 3 4 means the index of array
 *              0 1 2 3  4
 *            0 x 0 4 11 20
 *            1 x x 0 5 13
 *            2 x x x 0 6  
 *            3 x x x x 0
 *            4 x x x x x
 *              
 *      time = O(n ^ 3)
 *      space = O(n ^ 2)        			
 */
public class CuttingWood1 {
	public int minCost(int[] cuts, int length) {
		// make cut complete with index 0 and cut.length
		int[] arr = new int[cuts.length + 2];
		for (int i = 0; i < cuts.length; i++) {
			arr[i + 1] = cuts[i];
		}
		arr[0] = 0;
		arr[arr.length - 1] = length;

		// M[j][i] represents minimum cost for cutting from position i to position j
		// valid positions are stored in arr[]
		int[][] M = new int[arr.length][arr.length];
		// base case
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (i - j == 1) { // base case, adjacent cutting position is a unit which cannot be cut further,
									// so cost is 0
					M[j][i] = 0;
				} else { // iterate all possible cases with different cutting positions
					M[j][i] = Integer.MAX_VALUE; // at first the minimum cost between j and i is unknown
					for (int k = j + 1; k <= i - 1; k++) { // choose cutting position between j and i
						M[j][i] = Math.min(M[j][i], M[j][k] + M[k][i]);
					}
					M[j][i] += arr[i] - arr[j]; // M[j][i] = A[i] - A[j] + possible(M[j][k] + M[k][i])
					                            //   cost of cutting xxx
				}
			}
		}
		return M[0][arr.length - 1];
	}
}
