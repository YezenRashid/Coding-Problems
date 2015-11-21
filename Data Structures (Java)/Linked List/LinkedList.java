
public class LinkedList {
	private ListNode head;
	
	public LinkedList() {
		head = null;
	}
	
	public LinkedList(int elements) {
		if(elements < 0) {
			throw new IllegalArgumentException("Elements cannot be less than 0.");
		}
		
		head = new ListNode(0);
		ListNode curr = head;
		
		for (int i = 1; i < elements ; i++) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}
	}
	
	public void insert(int data) {
		head = new ListNode(data, head);
	}
	
	public void delete(int data) throws IllegalArgumentException {
		if(head == null) {
			throw new IllegalArgumentException("List is empty");
		}
		
		if(head.data == data) {
			head = head.next;
			return;
		}
	
		ListNode current = head;
		
		while(current.next != null) {
			if(current.next.data == data) {
				current.next = current.next.next;
				return;
			}
			current = current.next;
		}
	}
	
	public ListNode getNode(int data) {
		ListNode current = head;
		
		while(current != null) {
			if(current.data == data) {
				return current;
			}
			current = current.next;
		}
		
		return null;
	}
	
	public void reverseList() {
		if(head != null && head.next != null) {
			ListNode current = head;
			while(current.next != null) {
				ListNode temp = current.next;
				ListNode newHead = head;
				current.next = current.next.next;
				head = temp;
				temp.next = newHead;
			}
		}
	}
	
	public void printList() {
		ListNode current = head;
		
		while(current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		
		System.out.println();
	}	
}
