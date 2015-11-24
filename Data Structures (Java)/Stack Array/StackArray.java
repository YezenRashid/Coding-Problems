
public class StackArray {
	private int[] storage;
	private int size;
	private static int DEFAULT_CAPACITY = 100;
	
	public StackArray() {
		this(DEFAULT_CAPACITY);
	}
	
	public StackArray(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException("Stack must have a capacity greater than 0");
		}
		
		storage = new int[capacity];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(int n) {
		if(size >= storage.length) {
			increaseCapacity();
		}
		
		storage[size] = n;
		size++;
	}
	
	public int peek() {
		if(isEmpty()) {
			throw new StackException("The stack is empty.");
		}
		
		return storage[size];
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new StackException("The steack is empty.");
		}
		
		size--;
		int n = storage[size];
		return n;
	}
	
	public int getSize() {
		return size;
	}
	
	private void increaseCapacity() {
		int[] newStack = new int[storage.length * 2];
		
		for(int i = 0; i < storage.length; i++) {
			newStack[i] = storage[i];
		}
		
		storage = newStack;
	}
	
	public class StackException extends RuntimeException {
		public StackException(String message) {
			super(message);
		}
	}
}
