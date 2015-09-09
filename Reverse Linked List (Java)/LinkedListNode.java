public class LinkedListNode {
    public int value;
    public LinkedListNode next;  

    public LinkedListNode(int value) {
        this(value, null);
    }

    public LinkedListNode(int value, LinkedListNode next) {
        this.value = value;
        this.next = next;
    }
}