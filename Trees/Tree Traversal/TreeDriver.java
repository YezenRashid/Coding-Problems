public class TreeDriver {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		BinaryTree t = new BinaryTree(a);
		t.printInOrderTreeRecursive(t.getOverallRoot());
		System.out.println();
		t.printPreOrderTreeRecursive(t.getOverallRoot());
		System.out.println();
		t.printInOrderIterative(t.getOverallRoot());
	}
}
