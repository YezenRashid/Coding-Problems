
public class Queue {
	private int size;
	private ListNode top;
	private ListNode end;
	
	public Queue() {
		size = 0;
		top = null;
		end = null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void add(int n) {
		if(isEmpty()) {
			top = new ListNode(n, top);
			end = top;
		} else {
			end.next = new ListNode(n);
			end = end.next;
		}
		
		size++;
	}
	
	public int peek() {
		if(isEmpty()) {
			throw new QueueException("Queue is empty.");
		}
		
		return top.data;
	}
	
	public int remove() {
		if(isEmpty()) {
			throw new QueueException("Queue is empty.");
		}
		
		int n = top.data;
		
		if(top.next == null) {
			end = null;
		}
		
		top = top.next;
		size --;
		return n;
	}
	
	public int getSize() {
		return size;
	}
	
	public class QueueException extends RuntimeException {
		public QueueException(String message) {
			super(message);
		}
	}
}
