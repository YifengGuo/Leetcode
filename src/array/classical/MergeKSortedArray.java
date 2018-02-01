package array.classical;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author yifengguo
  Merge K sorted array into one big sorted array in ascending order.

Assumptions

The input arrayOfArrays is not null, none of the arrays is null either.
 */
/*
 * basic idea: minHeap + k pointers
 * assume there are k rows and longest row has n elements
 * time = O(k * n * log(k))
 * space = O(k)
 */
public class MergeKSortedArray {
	class Entry {
		int x;
		int y;
		int value;

		public Entry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public int[] merge(int[][] arrayOfArrays) {
		if (arrayOfArrays == null) {
			return new int[] {};
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
		// get the total item number of arrayOfArrays
		int len = 0;
		for (int i = 0; i < arrayOfArrays.length; i++) {
			int[] tmp = arrayOfArrays[i];
			if (tmp.length > 0) {
				minHeap.offer(new Entry(i, 0, tmp[0])); // add first element of each array into minHeap
				len += tmp.length;
			}
		}
		int[] res = new int[len];
		int cur = 0;
		while (!minHeap.isEmpty()) {
			Entry e = minHeap.poll();
			res[cur++] = e.value;
			// check if e’s array has element left or not
			if (e.y < arrayOfArrays[e.x].length - 1) {
				minHeap.offer(new Entry(e.x, e.y + 1, arrayOfArrays[e.x][e.y + 1]));
			} else {
				continue;
			}
		}
		return res;
	}
}