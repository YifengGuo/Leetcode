package DataStructureImplementations.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class FindAllWithPrefixInTrie {
	public List<String> findAllWithPrefix(TrieNode root, String prefix) {
		List<String> res = new ArrayList<>();
		if (prefix == null || prefix.length() == 0) {
			return res;
		}
		TrieNode matchNode = searchPrefix(root, prefix);
		if (matchNode == null) {
			return res;
		}
		// string builder shall be initialized with prefix
		StringBuilder curPath = new StringBuilder(prefix);
		findAllByDFS(res, matchNode, curPath);
		return res;
	}
	/*
	 * to find the node where prefix ends
	 */
	private TrieNode searchPrefix(TrieNode root, String prefix) {
		TrieNode cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			Character c = prefix.charAt(i);
			TrieNode next = cur.children.get(c);
			if (next == null) {
				return null;
			}
			cur = next;
		}
		return cur;
	}
	/*
	 * dfs
	 */
	public void findAllByDFS(List<String> res, TrieNode cur, StringBuilder curPath) {
		// this condition must check before base case
		if (cur.isWord) { // when find a word with such prefix, add it to the result
			res.add(curPath.toString()); // be careful: this is not base case
		}
		// base case
		// arrive the leaf node of trie
		// for the for loop on hash map will check the size
		// if size of map is 0, then the dfs function will not be invoked
		// so the for loop can be seen as base case as well
//		if (cur.children.size() == 0) {
//			return;
//		}
		/*
		 * dfs on all the children of current TrieNode
		 */
		for (Entry<Character, TrieNode> e : cur.children.entrySet()) {
			curPath.append(e.getKey());
			findAllByDFS(res, e.getValue(), curPath);
			curPath.deleteCharAt(curPath.length() - 1);
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
		String prefix = "ca";
		FindAllWithPrefixInTrie test = new FindAllWithPrefixInTrie();
		List<String> res = test.findAllWithPrefix(root, prefix);
		return res;
	}
	public static void main(String[] args) {
		List<String> res = test();
		for (String s : res) {
			System.out.println(s);
		}
		
	}
}
