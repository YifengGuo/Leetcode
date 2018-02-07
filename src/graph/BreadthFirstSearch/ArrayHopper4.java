package graph.BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author yifengguo
  Given an array A of non-negative integers, you are initially positioned at an arbitrary index of the array. 
  A[i] means the maximum jump distance from that position (you can either jump left or jump right). 
  Determine the minimum jumps you need to reach the right end of the array. Return -1 if you can not reach the right end of the array.

	Assumptions
	
	The given array is not null and has length of at least 1.
	Examples
	
	{1, 3, 1, 2, 2}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the right end of array)
	
	{3, 3, 1, 0, 0}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the right end of array)
	
	{4, 0, 1, 0, 0}, if the initial position is 2, you are not able to reach the right end of array, return -1 in this case.
 */
public class ArrayHopper4 {
	class Entry {
		int index; // index in array
		int level; // level in BFS, which also means the number of jumps

		public Entry(int index, int level) {
			this.index = index;
			this.level = level;
		}
	}

	public int minJump(int[] array, int index) {
		// sanity check
		if (array == null || array.length == 0) {
			return -1;
		}
		if (index < 0 || index >= array.length) {
			return -1;
		}
		if (index == array.length - 1 || array.length == 1) {
			return 0;
		}
		if (array[index] + index >= array.length - 1) { // if start can reach the end directly
			return 1;
		}
		Queue<Entry> queue = new LinkedList<>();
		int finalLevel = Integer.MAX_VALUE;
		boolean[] visited = new boolean[array.length]; // record current index has been visited
		// step 1: start node
		Entry start = new Entry(index, 1);
		queue.offer(start);
		// step 2: BFS
		while (!queue.isEmpty()) {
			Entry cur = queue.poll();
			int next_level = cur.level + 1; // next possible entry's level which should be added by 1 of current entry's
											// level
			if (!visited[cur.index]) { // if current entry has been visited before, then we do need to check its feasibility again
				for (int i = array[cur.index]; i >= -array[cur.index]; i--) { // iterate on all possible positions both left side and right side of curr
					int next_index = cur.index + i; // next possible entry's index which current entry can reach
					if (next_index < 0) {
						next_index = 0; // at most go left can only reach index 0
					}
					if (array[next_index] + next_index >= array.length - 1) { // this potential next entry can reach the end, update the jumps
						finalLevel = Math.min(finalLevel, next_level);
					} else {
						queue.offer(new Entry(next_index, next_level)); // cannot reach the end, offer into the queue for further process in the future
					}
				}
				visited[cur.index] = true;
			}
		}
		return finalLevel == Integer.MAX_VALUE ? -1 : finalLevel;
	}
}

