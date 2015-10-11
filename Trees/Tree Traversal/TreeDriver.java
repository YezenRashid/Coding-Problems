public class TreeDriver {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		BinaryTree t = new BinaryTree(a);
		
		System.out.print("In order Recursive: ");
		t.printInOrderTreeRecursive(t.getOverallRoot());
		System.out.println();
		
		System.out.print("In order Iterative: ");
		t.printInOrderIterative(t.getOverallRoot());
		System.out.println();
		
		System.out.print("Pre order Recursive: ");
		t.printPreOrderTreeRecursive(t.getOverallRoot());
		System.out.println();
		
		System.out.print("Pre order Iterative: ");
		t.printPreOrderIterative(t.getOverallRoot());
		System.out.println();
		
		System.out.print("Post order Recursive: ");
		t.printPostOrderTreeRecursive(t.getOverallRoot());
		System.out.println();
		
		System.out.print("Post order Iterative: ");
		t.printPostOrderIterative(t.getOverallRoot());
		System.out.println();
	}
}
