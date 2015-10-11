import java.util.Stack;

public class BinaryTree {
	private Node overallRoot;
	
	public class Node {
		Node left;
		Node right;
		int data;
		
		Node(int data) {
			this(data, null, null);
		}
		
		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	BinaryTree() {
		overallRoot = null;
	}
	
	BinaryTree(int[] a) {
		overallRoot = construct(a, 0, a.length - 1);
	}
	
	private Node construct(int[] a, int start, int end) {
		if(start > end) { 
			return null;
		}
		
		int mid = (start + end) / 2;
		Node root = new Node(a[mid], construct(a, start, mid - 1), construct(a, mid + 1, end));
		return root;
	}
	
	public void printInOrderTreeRecursive(Node root) {
		if(root != null) {
			printInOrderTreeRecursive(root.left);
			System.out.print(root.data + " ");
			printInOrderTreeRecursive(root.right);
		}
	}
	
	public void printPreOrderTreeRecursive(Node root) {
		if(root != null) {
			System.out.print(root.data + " ");
			printPreOrderTreeRecursive(root.left);
			printPreOrderTreeRecursive(root.right);
		}
	}
	
	public void printPostOrderTreeRecursive(Node root) {
		if(root != null) {
			printPostOrderTreeRecursive(root.left);
			printPostOrderTreeRecursive(root.right);
			System.out.print(root.data + " ");
		}
	}
	
	public void printInOrderIterative(Node root) {
		Node current = root;
		Stack<Node> s = new Stack<Node>();
		while(!s.isEmpty() || current != null) {
			if(current != null) {
				s.push(current);
				current = current.left;
			} else {
				current = s.pop();
				System.out.print(current.data + " ");
				current = current.right;
			}
		}
	}
	
	public Node getOverallRoot() {
		return overallRoot;
	}	
}
