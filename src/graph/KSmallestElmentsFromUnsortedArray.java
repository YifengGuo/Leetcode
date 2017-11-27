package graph;
/**
 * 
 * @author yifengguo
 * Find the K smallest numbers in an unsorted integer array A. 
 * The returned numbers should be in ascending order.

	Assumptions
	
	A is not null
	K is >= 0 and smaller than or equal to size of A
	Return
	
	an array with size K containing the K smallest numbers in ascending order
	Examples
	
	A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}


 */
/*
 * basic idea: several ideas can solve this problem
 * 	idea1: sort time = O(nlogn)
 * 
 * 	idea2: selection sort idea time = O(n * k) 
 * 		   for k times outer loop and n times inner loop
 * 		   1th iteration to find smallest element
 *         2th iteration to find second smallest element
 *         ...
 *         kth iteration to find kth smallest element
 *         
 *  idea3: heap
 *      idea3.1 minHeap
 *      	step1: heapify the given unsorted array to a minHeap O(n)
 *          step2: pop the top element for k times O(k*logn)
 *          total: O(n) + O(k*logn)
 *      
 *      idea3.2 maxHeap  ---> do not have to heapify the whole unsorted array
 *      	step1: insert the first k elements from array into maxHeap (heapify first k elements)
 *                 O(k)
 *          step2: 
 *          	case1: new element < maxHeap.top() -> maxHeap.pop() and insert new element
 *              case2: new element > maxHeap.top() -> continue 
 *              for we have (n - k) elements in the array left, so in worst case, the
 *              time complexity is O((n - k)*log(k))
 *              
 *          total time: O(k) + O((n-k) * log(k))
 *           
 *     to compare idea3.1 and idea3.2, we need to consider relationship between n and k 
 *                                minHeap                       maxHeap
 *     if k <<<<<<<< n            O(n)                           O(logk * n) 
 *                                still do not know which one is better
 *                                
 *     if k ~ n                   O(nlogn)                       O(c * logn)
 *                                eg: k = 1/2 * n, then still do not know which one is better
 *                                
 *                                
 *	idea4: quick-partition: average time = O(n)
 *         1st: xxxxxxxxxxxxxx pivot xxxxxxxxxxxxx       O(n)
 *         
 *         2st: xxxxpivotxxxxx                           O(n/2)
 *         
 *         ...
 *         
 *         nth
 *         
 *         average time: O(n + n/2 + n/4 + ...) = O(n)
 *         worst case: O(n ^ 2 )
 *         
 */
public class KSmallestElmentsFromUnsortedArray {
	public int find(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int res = 0;
		return res;
	}
}
