package graph.BestFirstSearch;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallestWithOnly357AsFactor {
	class Entry {
		int x;
		int y;
		int z;
		long product;

		public Entry(int x, int y, int z, long product) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.product = product;
		}
	}

	public long kth(int k) {
		if (k <= 0) {
			return 0;
		}
		PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
			@Override
			public int compare(Entry e1, Entry e2) {
				if (e1.product == e2.product) {
					return 0;
				}
				return e1.product < e2.product ? -1 : 1;
			}
		});
		Entry start = new Entry(1, 1, 1, (long) (Math.pow(3, 1) * Math.pow(5, 1) * Math.pow(7, 1)));
		minHeap.offer(start);
		int count = 0;
		final int[][] DIRS = { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 0, 1 }, { 0, 1, 1 }, { 0, 1, 0 }, { 1, 1, 1 },
				{ 0, 0, 1 } };
		// de-duplicate when offering new Entry into minHeap
		Set<Long> set = new HashSet<>();
		while (!minHeap.isEmpty()) {
			Entry cur = minHeap.poll();
			count++;
			if (count == k) {
				return cur.product;
			}
			for (int[] dir : DIRS) { // iterate on all possible changes to x, y and z of Entry and add them into minHeap after checking duplication
				int nextX = cur.x + dir[0];
				int nextY = cur.y + dir[1];
				int nextZ = cur.z + dir[2];
				long product = (long)(Math.pow(3, nextX) * Math.pow(5, nextY) * Math.pow(7, nextZ));
				if (!set.add(product)) {
					continue;
				}
				minHeap.offer(new Entry(nextX, nextY, nextZ, product));
			}
			
		}
		return 0;
	}
	
	public static void main(String[] args) {
		int k = 40;
		KthSmallestWithOnly357AsFactor test = new KthSmallestWithOnly357AsFactor();
		System.out.println(test.kth(k));
	}
	
}
