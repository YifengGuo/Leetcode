package DataStructureImplementations.HashMap;

import java.util.Arrays;

public class MyHashMap<K, V> {
	static class Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public V getValue() {
			return this.value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	private static int DEFAULT_CAPACITY = 16;
	private static float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private Entry<K, V>[] array;
	private int size;
	private float loadFactor;
	
	public MyHashMap(int cap, float loadFactor) {
		if (cap <= 0) {
			throw new IllegalArgumentException("Initial capacity must be positive!");
		}
		array = (Entry<K, V>[]) new Entry[cap];
		this.size = 0;
		this.loadFactor = loadFactor;
	}
	
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public void clear() {
		Arrays.fill(array, null);
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public int hash(K key) {
		if (key == null) {
			return 0;
		}
		return key.hashCode() & 0x7FFFFFFF;
	}
	
	public int getIndex(K key) {
		int hashCode = hash(key);
		return hashCode % array.length;
	}
	
	public V get(K key) {
		if (!this.containsKey(key)) {
			return null;
		}
		int index = getIndex(key);
		Entry<K, V> cur = array[index];
		while (cur != null) {
			if (equalsKey(key, cur.key)) {
				return cur.value;
			}
			cur = cur.next;
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		int index = getIndex(key);
		Entry<K, V> entry = array[index];
		while (entry != null) {
			if (equalsKey(key, entry.getKey())) {
				return true;
			}
			entry = entry.next;
		}
		return false;
	}
	
	private boolean equalsKey(K k1, K k2) {
		if (k1 == null && k2 == null) {
			return true;
		}
		if (k1 == null || k2 == null) {
			return false;
		}
		return k1.equals(k2);
	}
	
	public V put(K key, V value) {
		int index = getIndex(key);
		Entry<K, V> head = array[index];
		Entry<K, V> cur = head;
		while (cur != null) {
			if (equalsKey(cur.getKey(), key)) {
				V res = cur.getValue();
				cur.setValue(value);
				return res;
			}
			cur = cur.next;
		}
		// no existed key found or current position in bucket is null
		// append it to the head of current bucket
		Entry<K, V> newE = new Entry(key, value);
		newE.next = head;
		array[index] = newE;
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}
	
	private boolean needRehashing() {
		return (this.size * (1.0) / array.length) >= this.loadFactor;
	}
	
	private void rehashing() {
		Entry<K, V>[] newArray = (Entry<K, V>[]) new Entry[2 * array.length];
		Entry<K, V>[] oldArray = array;
		this.array = newArray;
		for (Entry<K, V> e : oldArray) {
			put(e.getKey(), e.getValue());
		}
	}
	
}
