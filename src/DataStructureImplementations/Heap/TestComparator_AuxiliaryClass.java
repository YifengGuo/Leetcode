package DataStructureImplementations.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author guoyifeng 
 *         If a class which does not implement Comparable<> interface
 *         and need to be compared, then we must supply a Comparator either by a
 *         explicit auxiliary comparator class or anonymous class in collection
 *         which we want to sort the class in it
 */
class myComparator implements Comparator<Point> {
	@Override
	public int compare(Point e1, Point e2) {
		if (e1.value == e2.value) {
			return 0;
		}
		return e1.value < e2.value ? -1 : 1;
	}
}

public class TestComparator_AuxiliaryClass {
	public static void main(String[] args) {
		PriorityQueue<Point> minHeap = new PriorityQueue<>(11, new myComparator());
		minHeap.offer(new Point(0, 0, 1));
		minHeap.offer(new Point(0, 0, -3));
		minHeap.offer(new Point(0, 0, 6));
		minHeap.offer(new Point(0, 0, 8));

		while (minHeap.isEmpty() == false) {
			System.out.println(minHeap.poll().value);
		}
	}
}
