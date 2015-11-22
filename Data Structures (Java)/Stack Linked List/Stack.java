
public class Stack {
	private int size;
	private ListNode top;
	
	public Stack() {
		size = 0;
		top = null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(int n) {
		top = new ListNode(n, top);
		size++;
	}
	
	public int peek() {
		if(isEmpty()) {
			return -1;
		}
		
		return top.data;
	}
	
	public int pop() {
		int n = top.data;
		top = top.next;
		size --;
		return n;
	}
	
	public int getSize() {
		return size;
	}
}
