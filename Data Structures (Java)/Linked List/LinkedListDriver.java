
public class LinkedListDriver {
   public static void main(String args[]) {
		  // Creates a list with 10 elements 
	      LinkedList list = new LinkedList(10);
	      
	      list.printList();
	      list.insert(1);
	      list.insert(1);
	      list.insert(1);
	      list.insert(1);
	      list.insert(2);
	      list.printList();
	      list.delete(1);
	      list.delete(9);
	      list.delete(4);
	      list.printList();
	      System.out.println("<----- REVERSING LINKEDLIST ----->");
	      list.reverseList();
	      list.printList();
	   }
}
