package graph.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 
 * @author yifengguo
	  Given a gym with k pieces of equipment and some obstacles.  
	  We bought a chair and wanted to put this chair into the gym such that  
	  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. 
	  The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 
	  'C' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) 
	  if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not 
	  put the chair on a cell with equipment or obstacle.
	
	Assumptions
	
	There is at least one equipment in the gym
	The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
	It is guaranteed that each 'C' cell is reachable from all 'E' cells.
	If there does not exist such place to put the chair, just return null (Java) empty vector (C++)
	Examples

	{ { 'E', 'O', 'C' },
	
	  {  'C', 'E',  'C' },
	
	  {  'C',  'C',  'C' } }

    we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipment is 1 + 1 = 2, which is minimal.
 */
/*
 * basic idea: 
 * 			step 1: confirm four-connected model(Manhattan Distance) or eight-connected model(Euclidean Distance)
 * 
 * 			step 2: run BFS on each EQUIP to find its cost to each 'C' and record the sum of cost from each EQUIP to 'C'
 *                  in a 2d matrix
 *                  
 *          step 3: remember check whether the place to put the chair can reach each EQUIP in the gym[][]
 *          
 *          assume there are k EQUIP, n^2 cells in gym[][]
 *          time = O(k * n^2) for each EQUIP bfs takes n^2 time
 */
public class PlaceToPutTheChair {
	private static final char OB = 'O';
	private static final char EQUIP = 'E';

	public List<Integer> putChair(char[][] gym) {
		List<Integer> res = null;
		if (gym == null || gym.length == 0 || gym[0].length == 0) {
			return res;
		}
		int m = gym.length;
		int n = gym[0].length;
		// use a 2d matrix to record the shortest sum cost calculated by BFS for current cell to
		// each every equipment, the goal of this problem is to find the
		// minimum sum among all the 'C' cells
		int[][] cost = new int[m][n];
		// iterate on each entry of gym[][] and run bfs on each EQUIP to 
		// calculate the minimum cost from current EQUIP to each 'C'
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// if current cell is an equipment
				// run bfs on it to find shortest path cost from it to
				// every reachable 'C' cells, and add the cost from current equip to that cell
				// to corresponding position in cost[][]
				if (EQUIP == gym[i][j]) {
					// if return false, it means there exists another equipment cannot be reached
					// from some cell which can reach current equipment
					if (!addCost(cost, gym, i, j)) {
						return null;
					}
				}
			}
		}
		// iterate on the cost[][] and find the cell which has shortest cost sum
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (OB != gym[i][j] && EQUIP != gym[i][j]) {
					if (res == null) {
						res = Arrays.asList(i, j);
					} else if (cost[i][j] < cost[res.get(0)][res.get(1)]) {
						res.set(0, i);
						res.set(1, j);
					}
				}
			}
		}
		return res;
	}
	
	// BFS on current EQUIP
	private boolean addCost(int[][] cost, char[][] gym, int i, int j) {
		int m = gym.length;
		int n = gym[0].length;
		boolean[][] visited = new boolean[m][n];
		int pathCost = 1; // initial cost, advance by 1 by each level
		Queue<Cell> queue = new LinkedList<>();
		Cell start = new Cell(i, j);
		queue.offer(start);
		visited[i][j] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int a = 0; a < size; a++) { // process on each level ( neighbors, pathCost does not change)
				Cell cur = queue.poll();
				List<Cell> neis = getNeis(gym, cur);
				for (Cell nei : neis) {
					if (!visited[nei.x][nei.y]) {
						queue.offer(nei);
						visited[nei.x][nei.y] = true;
						cost[nei.x][nei.y] += pathCost;
					}
				}
			}
			pathCost++; // when move to next level, advance pathCost by 1
		}
		// if there exists some cell which is EQUIP but has not been visited
		// it means the BFS on current EQUIP cannot reach this EQUIP, which violates 
		// the requirement of problem, so return null for we cannot find a place to 
		// put the chair which can reach each EQUIP in this gym
		for (int a = 0; a < m; a++) {
			for (int b = 0; b < n; b++) {
				if (!visited[a][b] && EQUIP == gym[a][b]) {
					return false;
				}
			}
		}
		return true;
	}

	public static int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	// get four direction neighbors of current cell
	private List<Cell> getNeis(char[][] gym, Cell cur) {
		List<Cell> res = new ArrayList<>();
		for (int[] dir : DIRS) {
			int nextX = cur.x + dir[0];
			int nextY = cur.y + dir[1];
			if (nextX >= 0 && nextX < gym.length && nextY >= 0 && nextY < gym[0].length && OB != gym[nextX][nextY]) {
				res.add(new Cell(nextX, nextY));
			}
		}
		return res;
	}

	class Cell {
		int x;
		int y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		char[][] gym =  { { 'E', 'O', 'C' },

				  {  'C', 'E',  'C' },

				  {  'C',  'C',  'C' } };
		List<Integer> res = new PlaceToPutTheChair().putChair(gym);
		for (Integer i : res) {
			System.out.print(i + " ");
		}
	}
}
