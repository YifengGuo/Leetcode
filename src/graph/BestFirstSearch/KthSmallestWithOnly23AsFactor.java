package graph.BestFirstSearch;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
/**
 * 
 * @author yifengguo
 * 
	Find the Kth smallest number s such that s = 2 ^ x 3 ^ y, x >= 0 and y >= 0, x and y are all integers.
	
	Assumptions
	
	K >= 1
	Examples
	
	the smallest is 1
	the 2nd smallest is 2
	the 3rd smallest is 3
	the 5th smallest is 2 3 = 6
	the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
 */
public class KthSmallestWithOnly23AsFactor {
	public static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 1, 1 } };

	class Entry {
		int base1 = 2;
		int base2 = 3;
		int exp1;
		int exp2;
		int value;

		public Entry(int exp1, int exp2) {
			this.exp1 = exp1;
			this.exp2 = exp2;
			this.value = (int) (Math.pow(base1, exp1) * Math.pow(base2, exp2));
		}
	}

	public int kth(int k) {
		if (k <= 0) {
			return 0;
		}
		PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
			@Override
			public int compare(Entry e1, Entry e2) {
				if (e1.value == e2.value) {
					return 0;
				}
				return e1.value < e2.value ? -1 : 1;
			}
		});
		Set<Integer> set = new HashSet<>();
		Entry start = new Entry(0, 0);
		minHeap.offer(start);
		set.add(1);
		int count = 0;
		while (!minHeap.isEmpty()) {
			Entry cur = minHeap.poll();
			count++;
			if (count == k) {
				return cur.value;
			}
			for (int[] dir : DIRS) {
				int nextExp1 = cur.exp1 + dir[0];
				int nextExp2 = cur.exp2 + dir[1];
				int product = (int) (Math.pow(2, nextExp1) * Math.pow(3, nextExp2));
				if (!set.add(product)) {
					continue;
				}
				minHeap.offer(new Entry(nextExp1, nextExp2));
			}
		}
		return 1;
	}
}
