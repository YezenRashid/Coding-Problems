
public class DoubleLinkedListDriver {
	public static void main(String[] args) {
		DoubleLinkedList list1 = new DoubleLinkedList();
		DoubleLinkedList list2 = new DoubleLinkedList(10);
		list1.printList();
		list1.printListReverse();
		list2.printList();
		list2.printListReverse();
		
		list2.insertAfter(3, 10);
		list2.insertAfter(5, 100);
		list2.insertAfter(9, 20);
		list2.insertAfter(0, 33);
		list2.printList();
		
		list2.delete(100);
		list2.delete(9);
		list2.delete(20);
		list2.delete(0);
		list2.printList();
		list2.addFirst(1);
		list2.addFirst(1);
		list2.addFirst(1);
		list2.printList();
		list2.printListReverse();
	}
}
