package DataStructureImplementations.Heap;

import java.util.PriorityQueue;
/**
 * 
 * @author guoyifeng
 * class implements Comparable interface and override compareTo()
 * can be directly compared in PriorityQueue
 */
class Entry implements Comparable<Entry> {
	int x;
	int y;
	int value;
	
	public Entry(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	@Override
	public int compareTo(Entry other) {
		if (this.value == other.value) {
			return 0;
		}
		return this.value < other.value ? -1 : 1;
	}
}

public class TestComparable {
	public static void main(String[] args) {
		PriorityQueue<Entry> minHeap = new PriorityQueue<>();
		minHeap.offer(new Entry(0, 0, 1));
		minHeap.offer(new Entry(0, 0, -3));
		minHeap.offer(new Entry(0, 0, 6));
		minHeap.offer(new Entry(0, 0, 8));
		
		while (minHeap.isEmpty() == false) {
			System.out.println(minHeap.poll().value);
		}
	}
}
