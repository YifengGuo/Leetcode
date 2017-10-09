package TopInterviewQuestions;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author yifengguo Determine if a Sudoku is valid, according to: Sudoku
 *         Puzzles - The Rules.
 * 
 *         The Sudoku board could be partially filled, where empty cells are
 *         filled with the character '.'.
 * 
 */
/*
 * % and /. These two operators can be helpful for matrix traversal problems.
 * 
 * For a block traversal, it goes the following way.
 * 
 * 0,0, 0,1, 0,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next
 * level.
 * 
 * 1,0, 1,1, 1,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next
 * level.
 * 
 * 2,0, 2,1, 2,2; < --- 3 Horizontal Steps.
 * 
 * And so on... But, the j iterates from 0 to 9.
 * 
 * But we need to stop after 3 horizontal steps, and go down 1 step vertical.
 * 
 * Use % for horizontal traversal. Because % increments by 1 for each j : 0%3 =
 * 0 , 1%3 = 1, 2%3 = 2, and resets back. So this covers horizontal traversal
 * for each block by 3 steps.
 * 
 * Use / for vertical traversal. Because / increments by 1 after every 3 j: 0/3
 * = 0; 1/3 = 0; 2/3 =0; 3/3 = 1.
 * 
 * So far, for a given block, you can traverse the whole block using just j.
 * 
 * But because j is just 0 to 9, it will stay only first block. But to increment
 * block, use i. To move horizontally to next block, use % again : ColIndex = 3
 * * i%3 (Multiply by 3 so that the next block is after 3 columns. Ie 0,0 is
 * start of first block, second block is 0,3 (not 0,1);
 * 
 * Similarly, to move to next block vertically, use / and multiply by 3 as
 * explained above.
 */
public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return false;
		}
		for (int i = 0; i < board.length; i++) {
			Set<Character> row = new HashSet<>();
			Set<Character> col = new HashSet<>();
			Set<Character> cube = new HashSet<>();
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.' && !row.add(board[i][j])) {
					return false;
				}
				if (board[j][i] != '.' && !col.add(board[j][i])) {
					return false;
				}
				int rowIdx = 3 * (i / 3);
				int colIdx = 3 * (i % 3);
				if (board[rowIdx + j / 3][colIdx + j % 3] != '.' && !cube.add(board[rowIdx + j / 3][colIdx + j % 3])) {
					return false;
				}
			}
		}
		return true;
	}
}
