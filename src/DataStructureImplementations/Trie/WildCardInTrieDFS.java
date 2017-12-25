package DataStructureImplementations.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
/*
 * for dfs
 * 	1. what does it store on each level
 *  2. how many levels in total?
 *  
 *  for this problem
 *  	'?' means we have to run dfs on all the brunches
 *      'a' means we have to run dfs on 'a' brunch 
 */
public class WildCardInTrieDFS {
	public List<String> findWildCard(TrieNode root, String wildcard) {
		List<String> res = new ArrayList<>();
		if (wildcard == null || wildcard.length() == 0) {
			return res;
		}
		StringBuilder path = new StringBuilder();
		dfs(res, root, path, wildcard, 0);
		return res;
	}
	
	public void dfs(List<String> res, TrieNode cur, StringBuilder path, String wildcard, int index) {
		// base case
		if (index == wildcard.length()) {
			if (cur.isWord) {
				res.add(path.toString());
			}
			return;
		}
		
		// process branches
	    // two cases: if current character is '?' or not
		// if current character is '?' dfs on all brunches
		if (wildcard.charAt(index) == '?') {
			for (Entry<Character, TrieNode> child : cur.children.entrySet()) {
				path.append(child.getKey());
				dfs(res, child.getValue(), path, wildcard, index + 1);
				path.deleteCharAt(path.length() - 1);
			}
		} else {
			TrieNode nextLevel = cur.children.get(wildcard.charAt(index));
			if (nextLevel != null) {
				path.append(wildcard.charAt(index));
				dfs(res, nextLevel, path, wildcard, index + 1);
				path.deleteCharAt(path.length() - 1);
			}
			return;
		}
	}
	
	public static List<String> test() {
		Trie trie = new Trie();
		TrieNode root = new TrieNode();
		trie.insert(root, "cap");
		trie.insert(root, "cat");
		trie.insert(root, "game");
		trie.insert(root, "because");
		trie.insert(root, "capper");
		trie.delete(root, "because");
		trie.insert(root, "cop");
		WildCardInTrieDFS test = new WildCardInTrieDFS();
		List<String> res = test.findWildCard(root, "???");
		return res;
	}
	public static void main(String[] args) {
		List<String> res = test();
		for (String s : res) {
			System.out.println(s);
		}
		
	}
}
