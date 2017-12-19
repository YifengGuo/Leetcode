package DataStructureImplementations.Trie;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * implement a trie with insert, search, and startsWith methods.
 */
public class Trie_Leetcode {
    class TrieNode {
        private int count; // under the current TrieNode, if it has any valid words downstream
        private boolean isWord;
        private Map<Character, TrieNode> children;
        public TrieNode() {
            isWord = false;
            children = new HashMap<>();
        }
    }
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie_Leetcode() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode root = this.root;
        if (word == null || word.length() == 0) {
            return;
        }
        if (search(word)) { // already existed
            return;
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
            cur.count++;
        }
        cur.isWord = true;
        return;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode root = this.root;
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
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode root = this.root;
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            TrieNode next = cur.children.get(c);
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return cur.count >= 1;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
