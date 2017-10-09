package TopInterviewQuestions;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author yifengguo
 	Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

	The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

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
