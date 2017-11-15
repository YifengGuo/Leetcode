package DataStructureImplementations.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author guoyifeng
 * If a class which does not implement Comparable<> interface and need to be 
 * compared, then we must supply a Comparator either by a explicit auxiliary
 * comparator class or anonymous class in collection which we want to sort the 
 * class in it 
 */
class Point {
	int x;
	int y;
	int value;
	public Point(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}
public class TestComparator_AnonymousClass {
	public static void main(String[] args) {
		PriorityQueue<Point> minHeap = new PriorityQueue<>(11, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if (p1.value == p2.value) {
					return 0;
				}
				return p1.value < p2.value ? -1 : 1;
			}
		});
		
		minHeap.offer(new Point(0, 0, 1));
		minHeap.offer(new Point(0, 0, 2));
		minHeap.offer(new Point(0, 0, 8));
		minHeap.offer(new Point(0, 0, -4));
		
		while (minHeap.isEmpty() == false) {
			System.out.println(minHeap.poll().value);
		}	
	}
}
