package DataStructureImplementations.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

	Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
	cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
	
	For example,
	Given words = ["oath","pea","eat","rain"] and board =
	
	[
	  ['o','a','a','n'],
	  ['e','t','a','e'],
	  ['i','h','k','r'],
	  ['i','f','l','v']
	]
	Return ["eat","oath"].
	Note:
	You may assume that all inputs are consist of lowercase letters a-z.
 */

public class WordSearch2 {
	/*	To improve an existed solution in terms of time:
	 * 		1. unnecessary computation (e.g binary search, DFS pruning)
	 * 		2. repeated computation (e.g Dynamic Programming)
	 * better solution: initialize trie tree and run dfs on trie
	 * 				    traverse each cell in the board and take it 
	 *                  as start and try to find a valid path in the trie
	 *                  if path exists, the word is in the dict
	 *                  
	 *        Assume m, n are the side length of board matrix
	 *        length of word is i, size of dictionary is k
	 *        to construct a trie, we need to traverse each word in the dict
	 *        and each character of each word
	 *  Time Complexity = Construct Trie + DFS
	 *                  = O(k * l + m * n * 4 ^ l)
	 *            
	 *        for each dfs():
	 *        at each layer of dfs, branching factor is 4(left, right, up, down)
	 *        and the depth of trie is l, so each dfs() time is O(4 ^ l)  
	 *                 
	 *        Compared with solution1: we do not need to run dfs on each word in the dict
	 *                                 as long as trie is initialized, run dfs on each 
	 *                                 cell of board, as soon as the path matches the word
	 *                                 in the trie, we add it into the result
	 */
    private class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children;
        public TrieNode() {
            this.isWord = false;
            this.children = new HashMap<>();
        }
    }
    
    private class Trie {
        public boolean insert(TrieNode root, String word) {
            if (search(root, word)) {
                return false;
            }
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                TrieNode next = cur.children.get(c);
                if (next == null) {
                    next = new TrieNode();
                    cur.children.put(c, next);
                }
                cur = next;
            }
            cur.isWord = true;
            return true;
        }
        
        public boolean search(TrieNode root, String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                TrieNode next = cur.children.get(c);
                if (next == null) {
                    return false;
                }
                cur = next;
            }
            return cur.isWord;
        }
    }
	static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	public List<String> findWords(char[][] board, String[] dict) {
		List<String> res = new ArrayList<>();
		// sanity check
		if (dict == null || dict.length == 0 || board == null || board.length == 0 || board[0].length == 0) {
			return res;
		}
		TrieNode root = buildDict(dict);
		boolean[][] visited = new boolean[board.length][board[0].length];
		StringBuilder sb = new StringBuilder();
		// try to match the word with word in the dict started from each cell of board 
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dfs(res, root, board, visited, i, j, sb);
			}
		}
		return res;
	}
	
	// initialize the dictionary by trie tree
	private TrieNode buildDict(String[] dict) {
		TrieNode root = new TrieNode();
		Trie trie = new Trie();
		for (String word : dict) {
			trie.insert(root, word);
		}
		return root;
	}
	
	private void dfs(List<String> res, TrieNode cur, char[][] board, boolean[][] visited, int x, int y, StringBuilder sb) {
		final int m = board.length;
		final int n = board[0].length;
		// pruning and sanity check
		if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || cur.children.get(board[x][y]) == null) {
			return;
		}
		
		char c = board[x][y]; // current -> on the trie tree 
		sb.append(c); // append current character of board if all the cases satisfied
        cur = cur.children.get(c); // if we want to know if current character is the end of a word, we need to check next TrieNode.isWord
		// base case
		if (cur.isWord) {
			res.add(sb.toString());
			cur.isWord = false; // avoid to be appended by other paths, one word shall be added only once
		}
		
		// process on current layer
		visited[x][y] = true; // mark current cell as visited
		for (int[] dir : DIRS) {
			int neiX = x + dir[0];
			int neiY = y + dir[1];
			dfs(res, cur, board, visited, neiX, neiY, sb);
		}
		// recover to make it visible to other paths
		sb.deleteCharAt(sb.length() - 1);
		visited[x][y] = false;
		
	}
	
	
	
	
	/*
	 * solution 1: DFS + backtracking + visited memorization
	 *     Time Complexity:
	 * 		Assume m, n are the side length of board matrix
	 *             length of word is i, size of dictionary is k
	 *      The layers of dfs tree is i for we need to check each character of the word
	 *      and on each layer of dfs, we at worst case need to run dfs() on four directions
	 *      so we have total 4 ^ i nodes in the dfs tree based on one single cell as root in the board
	 *      and we have m * n cells in the board, k words in the dictionary
	 *      so the complexity of this problem is :
	 *      	O(k * m * n * 4 ^ i)
	 *     Which is not so good in the large size
	 */
//	static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
//
//	public List<String> findWords(char[][] board, String[] dict) {
//		List<String> res = new ArrayList<>();
//		Set<String> dedup = new HashSet<>();
//		if (dict == null || dict.length == 0 || board == null || board.length == 0 || board[0].length == 0) {
//			return res;
//		}
//		for (String word : dict) {
//			if (exist(board, word)) {
//				dedup.add(word);
//			}
//		}
//		for (String s : dedup) {
//			res.add(s);
//		}
//		return res;
//	}
//
//	private boolean exist(char[][] board, String word) {
//		final int m = board.length;
//		final int n = board[0].length;
//		boolean[][] visited = new boolean[m][n];
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++) {
//				if (dfs(board, m, n, word, 0, i, j, visited)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private boolean dfs(char[][] board, int m, int n, String word, int index, int x, int y, boolean[][] visited) {
//		// base case
//		if (index == word.length()) {
//			return true;
//		}
//		// validation check
//		if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || board[x][y] != word.charAt(index)) {
//			return false;
//		}
//		// dfs on each possible directions
//		visited[x][y] = true; // set current cell as visited
//		for (int[] dir : DIRS) {
//			int neiX = dir[0] + x;
//			int neiY = dir[1] + y;
//			if (dfs(board, m, n, word, index + 1, neiX, neiY, visited)) {
//				visited[x][y] = false; 	 // if do not recover the visited[x][y] back to false
//						                 // dfs will be stopped once it finds first word matches
//						                 // given word
//				return true;
//			}
//
//		}
//		visited[x][y] = false; // recover to make it visible to other paths
//		return false;
//	}
	public static void main(String[] args) {
		WordSearch2 ws2 = new WordSearch2();
		char[][] board = new char[][] {

				 { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' }
			// {'a'}

		};
		String[] dict = new String[]{"ABCCED", "ABC","SF"};
		List<String> res = ws2.findWords(board, dict);
		for (String s : res) {
			System.out.println(s);
		}
	}
}