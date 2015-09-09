public class LinkedIntList {
	private LinkedListNode front;

    // Constructs an empty list.
    public LinkedIntList() {
        front = null;
    }
    
	public LinkedIntList(int elements) {
		if(elements < 0) {
			throw new IllegalArgumentException("Elements cannot be less than 0.");
		}
		
		front = new LinkedListNode(0);
		LinkedListNode curr = front;
		
		for (int i = 1; i < elements ; i++) {
			curr.next = new LinkedListNode(i);
			curr = curr.next;
		}
	}
	
   // reverseList takes a given linkedlist and reverses the order of the original
   // linkedlist so that front is back and back is front and traversing in backwards order.
   public void reverseList() {
	   if(front != null && front.next != null) {
		   LinkedListNode current = front;
		   while(current.next != null) {
			   LinkedListNode temp = current.next;
			   LinkedListNode newFront = front;
			   current.next = current.next.next;
			   front = temp;
			   temp.next = newFront;
		   }
	   }
   }
   
   // takes given linkedlist and prints each individual node from
   // front to last
   public void printList() {
	   LinkedListNode current = front;
	   while(current != null) {
		   System.out.print(current.value);
		   current = current.next;
	   }
	   
	   System.out.println();
   }
}