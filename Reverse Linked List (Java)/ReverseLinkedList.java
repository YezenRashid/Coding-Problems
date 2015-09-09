public class ReverseLinkedList {
	
   public static void main(String args[]) {
	  // Creates a list with 10 elements 
      LinkedIntList list = new LinkedIntList(10);
      
      list.printList();
      System.out.println("<----- REVERSING LINKEDLIST ----->");
      list.reverseList();
      list.printList();
   }
}