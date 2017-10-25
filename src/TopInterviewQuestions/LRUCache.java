package TopInterviewQuestions;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	// DLL Node
	class Node<K, V> {
		Node<K, V> next;
		Node<K, V> prev;
		int key;
		int value;

		// constructor of Node
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public void update(int key, int value) {
			this.key = key;
			this.value = value;
		}

	}

	private int capacity;
	private Map<Integer, Node<Integer, Integer>> map;
	private Node<Integer, Integer> head; // head node of DLL
	private Node<Integer, Integer> tail; // tail node of DLL

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<>();
	}

	public int get(int key) {
		Node<Integer, Integer> node = map.get(key);
		if (node == null) {
			return -1;
		} else {
			remove(node); // remove node from its current position
			append(node); // and update node to the head
			return node.value;
		}
	}

	public void put(int key, int value) {
		Node<Integer, Integer> node = null;
		if (map.containsKey(key)) {
			node = map.get(key);
			node.value = value;
			remove(node);
		} else { // append() will call map.put(), so do not call map.put() here
			if (map.size() < capacity) {
				node = new Node<Integer, Integer>(key, value);
			} else { // if map.size >= capacity, we need remove tail(least
						// recently used) node
				node = new Node<Integer, Integer>(key, value);
				if (tail != null) {
					remove(tail);
				}
			}
		}
		append(node); // new put node must be head
	}

	// append node to the head of DLL
	private Node<Integer, Integer> append(Node<Integer, Integer> node) {
		map.put(node.key, node); // initialize mapping
		if (head == null) { // DLL is empty
			head = tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node; // appended node must head(most recently used)
		}
		return node;
	}

	// remove node from DLL
	private Node<Integer, Integer> remove(Node<Integer, Integer> node) {
		if (node == null) {
			return null;
		}
		map.remove(node.key); // remove mapping of current node.key
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		if (node == head) {
			head = head.next;
		}
		if (node == tail) {
			tail = tail.prev;
		}
		node.prev = node.next = null; // set node prev and next to null
		return node;
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
