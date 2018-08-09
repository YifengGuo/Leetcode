package DataStructureImplementations.Trie;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * Design a data structure that supports the following two operations:

	void addWord(word)
	bool search(word)
	search(word) can search a literal word or a regular expression string containing only letters a-z or .. 
	A '.' means it can represent any one letter.
	
	Example:
	
	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true
	Note:
	You may assume that all words are consist of lowercase letters a-z.
 */ 
/*
 * time = O(n * m) where n is the longest length of word and m is largest count of trienode's children
 */
public class WordDictionary {
	class TrieNode {
	    public boolean isWord;
	    public Map<Character, TrieNode> children;

	    public TrieNode() {
	        isWord = false;
	        children = new HashMap<>();
	    }
	}
    public TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = this.root;
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
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchWithRegEx(word, this.root, 0);
    }
    
    private boolean searchWithRegEx(String word, TrieNode root, int index) {
        if (index == word.length()) return root.isWord;
        Character c = word.charAt(index);
        TrieNode next = root.children.get(c);
        // if (next == null) return false;
        if (!c.equals('.')) {
            return next != null && searchWithRegEx(word, next, index + 1);
        } else {
            for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
                if (entry.getKey() != null) {
                    if (searchWithRegEx(word, entry.getValue(), index + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void main(String... args) {
    	WordDictionary test = new WordDictionary();
    	test.addWord("bad");
    	test.addWord("pad");
    	test.addWord("dad");
    	System.out.println(test.search("bad"));
    	System.out.println(test.search("pad"));
    	System.out.println(test.search("baad"));
    	System.out.println(test.search("b.."));
    	System.out.println(test.search(".ad"));
    }
    
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */