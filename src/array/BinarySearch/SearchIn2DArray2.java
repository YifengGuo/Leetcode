package array.BinarySearch;

import java.util.LinkedList;
import java.util.Queue;

public class SearchIn2DArray2 {
	/*
	 * better solution :
	 * 		for matrix from left to right and from top to bottom is sorted
	 * 		searching from top right corner can prune quite a lot useless search
	 * time = O(m + n)
	 * space = O(1)
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int rowIdx = 0;
		int colIdx = matrix[0].length - 1;
		while (rowIdx < matrix.length && colIdx >= 0) {
            if (matrix[rowIdx][colIdx] == target) {
			return true;
            } else if (matrix[rowIdx][colIdx] < target) { // left elements are all smaller than current
                                                          // so we can only move row idx
                rowIdx++;
            } else {   // elements below are all greater than current
            		   // so we can only move col idx 
                colIdx--;
            }
        }
		return false;
    }
	
	
	
	/*
	 * my bad solution: BFS
	 * 		Time = O(n) for we need to traverse at worst case all the nodes of matrix
	 * 		space = O(b ^ m) 
	 */
	class Entry {
		int x; // x coordinate
		int y; // y coordinate
		int value;
		Entry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
	public boolean searchMatrix2(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		Queue<Entry> minHeap = new LinkedList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		Entry start = new Entry(0,0,matrix[0][0]);
        minHeap.offer(start);
		visited[0][0] = true;
        if (start.value == target) {
            return true;
        }
		while (minHeap.isEmpty() == false) {
			Entry cur = minHeap.poll();
			// go down
			if (cur.x + 1 < matrix.length) {
				Entry e = new Entry(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]);
				if (matrix[cur.x + 1][cur.y] == target) {
					return true;
				}
				if (!visited[cur.x + 1][cur.y]) {
					minHeap.offer(e);
					visited[cur.x + 1][cur.y] = true;
				}
			}
			// go right
			if (cur.y + 1 < matrix[0].length) {
				Entry e = new Entry(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]);
				if (matrix[cur.x][cur.y + 1] == target) {
					return true;
				}
				if (!visited[cur.x][cur.y + 1]) {
					minHeap.offer(e);
					visited[cur.x][cur.y + 1] = true;
				}

			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 },
				{ 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } };
		int target = 15;
		System.out.println(new SearchIn2DArray2().searchMatrix(matrix, target));

	}
}
