package DataStructureImplementations.Trie;

/**
 * 
 * @author guoyifeng
 * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells 
are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */
/*
 * basic idea: DFS + auxiliary matrix to record visited cells
 * Time Complexity:
 * 	the time complexity analysis on this problem and Word Search 2 is hard and important:
 * 		Assume m, n are the side length of board matrix
 *             length of word is i
 *      The layers of dfs tree is i for we need to check each character of the word
 *      and on each layer of dfs, we at worst case need to run dfs() on four directions
 *      so we have total 4 ^ i nodes in the dfs tree based on one single cell as root in the board
 *      and we have m * n cells in the board
 *      so the complexity of this problem is :
 *      	O(m * n * 4 ^ i)
 * 
 */
public class WordSearch {
	static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;
		if (board == null || m == 0 || n == 0 || word == null || word.length() == 0) {
			return false;
		}
		if (m * n < word.length()) {
			return false;
		}
		boolean[][] visited = new boolean[m][n];
		
		// call dfs on each cell of board
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (dfs(board, m, n, word, i, j, 0, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean dfs(char[][] board, int m, int n, String word, int x, int y, int index, boolean[][] visited) {
		//base case 
		// this base must be checked before the invalid cases
		// because we could successfully find the matched word in the board 
		// while x and y maybe out of bounds at that moment
		if (index == word.length()) { // means every character from index 0 to k - 1 is matched 
			return true;
		}
		
		// processes on current layer
		// filter out invalid cases
		// 1. out of bounds
		// 2. has been visited
		// 3. not matched
		if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || board[x][y] != word.charAt(index)) {
			return false;
		}
		
		// dfs on all possible directions
		visited[x][y] = true; // record current cell of board as visited
		for (int[] dir : DIRS) { // four direction movement
			int neiX = dir[0] + x;
			int neiY = dir[1] + y;
			if (dfs(board, m, n, word, neiX, neiY, index + 1, visited)) { // if find such path
				// visited[x][y] = false; // if do not recover the visited[x][y] back to false
				                          // dfs will be stopped once it finds first word matches
				                          // given word, in this problem, do not have to recover
				                          // but necessary when find multiple matched words
				return true;
			}
		}
		visited[x][y] = false; // recover to make it visible for other paths
		return false;
	}

/*
 * could solve the problem but some bug exist
 */
//public class WordSearch {
//	public static boolean flag;
//	public boolean exist(char[][] board, String word) {
//		if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
//			return false;
//		}
//		
//		// to record if the cell has been visited
//		boolean[][] visit = new boolean[board.length][board[0].length];
//		StringBuilder sb;
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[0].length; j++) {
//				sb = new StringBuilder(board[i][j]);
//				//sb = new StringBuilder();
//				//sb.append(board[i][j]);
//				// System.out.println(sb.toString());
//				visit[i][j] = true;
//				dfs(word, 0, visit, board, i, j, sb);
//				visit[i][j] = false;
//				if (flag) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	public boolean dfs(String word, int index, boolean[][] visit, char[][] board, int x, int y, StringBuilder sb) {
//		// base case
//		if (index == word.length()) {
//			System.out.println(sb.toString());
//			if (sb.toString().equals(word)) {
//				flag = true;
//				return true;
//			}
//			return false;
//		}
//		// go up
//		if (x - 1 >= 0 && !visit[x - 1][y]) {
//			sb.append(board[x - 1][y]);
//			visit[x - 1][y] = true;
//			dfs(word, index + 1, visit, board, x - 1, y, sb);
//			sb.deleteCharAt(sb.length() - 1);
//			visit[x - 1][y] = false;
//		}
//		// go down 
//		if (x + 1 < board.length && !visit[x + 1][y]) {
//			sb.append(board[x + 1][y]);
//			visit[x + 1][y] = true;
//			dfs(word, index + 1, visit, board, x + 1, y, sb);
//			sb.deleteCharAt(sb.length() - 1);
//			visit[x + 1][y] = false;
//		}
//
//		// go left
//		if (y - 1 >= 0 && !visit[x][y - 1]) {
//			sb.append(board[x][y - 1]);
//			visit[x][y - 1] = true;
//			dfs(word, index + 1, visit, board, x, y - 1, sb);
//			sb.deleteCharAt(sb.length() - 1);
//			visit[x][y - 1] = false;
//		}
//
//		// go right
//		if (y + 1 < board[0].length && !visit[x][y + 1]) {
//			sb.append(board[x][y + 1]);
//			visit[x][y + 1] = true;
//			dfs(word, index + 1, visit, board, x, y + 1, sb);
//			sb.deleteCharAt(sb.length() - 1);
//			visit[x][y + 1] = false;
//		}
//		return flag;
//	}
	public static void main(String[] args) {
		WordSearch ws = new WordSearch();
		char[][] board = new char[][] {

				 { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' }
			// {'a'}

		};
		String word = "ABCCED";
		// String word = "b";
		System.out.println(ws.exist(board, word));
	}
}

