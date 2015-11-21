
public class DoubleLinkedList {
	private DoubleListNode head;
	private DoubleListNode tail;
	
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
	
	public DoubleLinkedList(int elements) {
		if(elements < 0) {
			throw new IllegalArgumentException("Elements cannot be less than 0.");
		}
		
		head = new DoubleListNode(0);
		DoubleListNode curr = head;
		curr.prev = null;
		
		for (int i = 1; i < elements ; i++) {
			curr.next = new DoubleListNode(i, null, curr);
			curr = curr.next;
			tail = curr;
		}
	}
	
	public void addFirst(int data) {
		DoubleListNode n = new DoubleListNode(data, head, null);
		head.prev = n;
		head = n;
	}
	
	public boolean insertAfter(int element, int data) {
		if(head == null) {
			return false;
		}
		
		DoubleListNode current = head;
		while(current.data != element) {
			current = current.next;
			if(current == null) {
				return false;
			}
		}
		
		DoubleListNode insNode = new DoubleListNode(data);
		
		if(current == tail) {
			insNode.next = null;
			tail = insNode;
		} else {
			insNode.next = current.next;
			current.next.prev = insNode;
		}
		
		insNode.prev = current;
		current.next = insNode;
		return true;
	}
	
	public DoubleListNode delete(int data) throws IllegalArgumentException {
		if(head == null) {
			throw new IllegalArgumentException("List is empty");
		}
		
		DoubleListNode current = head;
		
		while(current.data != data) {
			current = current.next;
			if(current == null) {
				return null;
			}
		}
		
		if(current == head) {
			head = current.next;
		} else {
			current.prev.next = current.next;
		}
		
		if(current == tail) {
			tail = current.prev;
		} else {
			current.next.prev = current.prev;
		}
		
		return current;
	}
	
	public DoubleListNode getNode(int data) {
		DoubleListNode current = head;
		
		while(current != null) {
			if(current.data == data) {
				return current;
			}
			current = current.next;
		}
		
		return null;
	}
	
	public void printList() {
		DoubleListNode current = head;
		
		while(current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		
		System.out.println();
	}
	
	public void printListReverse() {
		DoubleListNode current = tail;
		
		while(current != null) {
			System.out.print(current.data + " ");
			current = current.prev;
		}
		
		System.out.println();
	}	
}
