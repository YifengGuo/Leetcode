package TopInterviewQuestions;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yifengguo 
 *         Given an unsorted array of integers, find the length of the
 *         longest consecutive elements sequence.
 * 
 *         For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive
 *         elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 *         Your algorithm should run in O(n) complexity.
 */
/*
 * The key thing is to keep track of the sequence length and store that in the
 * boundary points of the sequence. For example, as a result, for sequence {1,
 * 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.
 * 
 * Whenever a new element n is inserted into the map, do two things:
 * 
 * See if n - 1 and n + 1 exist in the map, and if so, it means there is an
 * existing sequence next to n. Variables left and right will be the length of
 * those two sequences, while 0 means there is no sequence and n will be the
 * boundary point later. Store (left + right + 1) as the associated value to key
 * n into the map. Use left and right to locate the other end of the sequences
 * to the left and right of n respectively, and replace the value with the new
 * length.
 * 
 * time = O(n)
 * space = O(n)
 */
public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0;
		// key: element in nums[]
		// value: the length of consecutive sequence the element is in
		// but only boundary elements value will be updated immediately
		// eg: 1 2 3 4 5. map.get(1) = 5, map.get(5) = 5;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : nums) {
			if (!map.containsKey(i)) {
				int left = (map.containsKey(i - 1)) ? map.get(i - 1) : 0;
				int right = (map.containsKey(i + 1)) ? map.get(i + 1) : 0;
				// sum: length of the sequence i is in
				int sum = left + right + 1;
				map.put(i, sum);
				res = Math.max(sum, res); // update res if possible

				// extend the length to the boundary(s)
				// of the sequence
				// will do nothing if n has no neighbors
				map.put(i - left, sum);
				map.put(i + right, sum);
			} else {
				// duplicate
				continue;
			}
		}
		return res;
	}
}
