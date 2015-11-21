
public class DoubleListNode {
	public int data;
	public DoubleListNode next;
	public DoubleListNode prev;
	
	public DoubleListNode(int data) {
		this(data, null, null);
	}

	public DoubleListNode(int data, DoubleListNode next, DoubleListNode prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
}
