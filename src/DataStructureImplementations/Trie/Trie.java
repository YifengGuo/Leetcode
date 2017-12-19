package DataStructureImplementations.Trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
	public int count; // below this TrieNode, how many TrieNode's isWord == true
	public boolean isWord;
	public Map<Character, TrieNode> children;
	public TrieNode() {
		isWord = false;
		children = new HashMap<>();
	}
}
public class Trie {
	// search "cat" in the trie
    // finding the path from root which is equal to "cat"
    // for each of the character in "cat", see if there is an edge associated with it for the next level
	public boolean search(TrieNode root, String target) {
		// sanity check
		if (target == null || target.length() == 0) {
			return false;
		}
		// assume target is not null
		TrieNode cur = root;
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i); // current scanned character
			TrieNode next = cur.children.get(c);
			if (next == null) {
				return false; // could not match with given input
			}
			cur = next;
		}
		return cur.isWord;
	}
	
	// insert "carp" into the trie
	// finding the path from root which is equal to "carp"
	// for each of the character in "cat", see if there is an edge associated with it for the next level
	// 1. if there is an edge, go to the corresponding child node
	// 2. if there is not an edge, create the child node
	public boolean insert(TrieNode root, String target) {
		if (search(root, target)) { // if we can search this in the tree
			return false; // cannot insert same word twice
		}
		TrieNode cur = root;
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			TrieNode next = cur.children.get(c);
			if (next == null) {
				next = new TrieNode();
				cur.children.put(c, next);  // insert current character of target
			}
			cur = next; // iterate to next index
			cur.count++; // word is added time by time, how many times insert() invoked, how many count upstream node will have
		}
		cur.isWord = true;
		return true;
		
	}
	
	/*
	 * delete "apple" from the Trie
	 * - finding the path from root which is equal to "apple"
	 * - how to just delete "l e" instead of the whold word "apple" from Trie? for app may be another valid word
	 * 		so what we need to figure out is to determine that only one valid word exists under current node which is the word we want to delete
	 *      and deleting the downstream nodes is safe
	 * 
	 * idea 1: recursion  when children's word is only one (base case)
	 * 
	 * idea 2: stack   store whole word to delete and look back until only one word on the path scanned
	 * 
	 * idea 3: add a field in TrieNode count to represent how many isWord node existed below this node  O(1) space
	 * 
	 */
	public boolean delete(TrieNode root, String target) {
		if (!search(root, target)) { // if cannot find this target in the Trie, return;
			return false;
		}
		
		TrieNode cur = root;
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			TrieNode next = cur.children.get(c);
			if (next.count == 1) {
				cur.children.remove(c);
				return true;
			}
			cur = next;
			cur.count--; // do not forget because we do delete one node in this function, so count field of upstream nodes will change
		}
		cur.isWord = false; // if target word did not reach the node whose count == 1
					        // we need to set the current node's isWord = false to 
							// delete this word manually (only one word on this path )
		return true;
	}
}

