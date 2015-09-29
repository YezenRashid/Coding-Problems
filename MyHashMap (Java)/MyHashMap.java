import java.util.*;

public class MyHashMap<K, V> {
	private int size;
	private int DEFAULT_CAPACITY = 16;
	private Entry<K, V>[] entries;
	
	// This entry class represents an entry in the hashmap.
	// It also acts as a linked list in the case of collisions.
	@SuppressWarnings("hiding")
	public class Entry<K, V> {
		private final K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}	
	}
	
	@SuppressWarnings("unchecked")
	public MyHashMap() {
		size = 0;
		entries = new Entry[DEFAULT_CAPACITY];
	}
	
	public V get(K key) {
		int hash = key.hashCode() % size;
		Entry<K, V> e = entries[hash];
		
		while(e != null) {
			if(e.getKey().equals(key)) {
				return e.getValue();
			}
			
			e = e.next;
		}

		return null;
	}
	
	// Add the new Entry to the the start of the bucket list.
	public V put(K key, V value) {
		//checks if the key already exists, then we overwrite its value.
		if(containsKey(key)) {
			V previousValue = get(key);
			return previousValue;
		}
		
		size++;
		ensureCapacity();
		
		int hash = key.hashCode() % size;
		Entry<K, V> insert = new Entry<K, V>(key, value);
		insert.next = entries[hash];
		entries[hash] = insert;
		
		return null;
	}
	
	public V remove(K key) {
		return null;
	}
	
	// Returns true if the key is contained in the hashmap, false otherwise.
	public boolean containsKey(K key) {
		int hash = key.hashCode() % size;
		Entry<K, V> e = entries[hash];
		
		while(e != null) {
			if(e.getKey().equals(key)) {
				return true;
			}
			e = e.next;
		}
		
		return false;
	}
	
	// Returns true if the value is contained in the hashmap, false otherwise.
	public boolean containsValue(V value) {
		for(int i = 0; i < entries.length; i++) {
			Entry<K, V> e = entries[i];
			while(e != null) {
				if(e.getValue().equals(value)) {
					return true;
				}
				e = e.next;
			}
		}
		
		return false;
	}
	
	// Clears all values in the HashMap.
	public void clear() {
		for(int i = 0; i < entries.length; i++) {
			entries[i] = null;
			size--;
		}
	}
	
	// Returns the number of elements in the hashmap.
	public int size() {
		return size;
	}
	
	// Ensures the size of the array can hold the number of elements.
	private void ensureCapacity() {
		if(size == entries.length) {
			int newSize = entries.length * 2;
			entries = Arrays.copyOf(entries, newSize);
		}
	}
}
