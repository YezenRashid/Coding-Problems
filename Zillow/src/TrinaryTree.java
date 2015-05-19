// Yezen Rashid
// Zillow Question #2
// This class TrinaryTree constructs a tri-nary tree.  It allows
// for the insertion and deletion of nodes.  The delete solution relies on keeping
// an orphan left node and promoting the right node and then reinserting the orphan
// left node.  It does not use the conventional algorithm for deleting a node in a BST. 

public class TrinaryTree {
	private TrinaryTreeNode overallRoot;
	
	// post: constructs an empty TrinaryTree
	public TrinaryTree() {
		overallRoot = null;
	}
	
	// post: Inserts the given TrinaryTreeNode into the tri-nary tree.
	public void insertNode(TrinaryTreeNode insNode) {
		overallRoot = insertNode(overallRoot, insNode);
	}
	
	// post: Deletes a node with the given value from the tri-nary tree.
	public void deleteNode(int value) {		
		overallRoot = deleteNode(overallRoot, value);
	}
	
	// post: Outputs the current TrinaryTree in standard format.
	public void printTree() {
		printTree(overallRoot);
		System.out.println();
	}
	
	// post: Inserts the given a TrinaryTreeNode into the tri-nary tree. Returns
	//		 a TrinaryTreeNode.
	private TrinaryTreeNode insertNode(TrinaryTreeNode root, TrinaryTreeNode insNode) {
		if(root == null) {
			root = insNode;
		} else {
			if(root.data > insNode.data) {
				root.left = insertNode(root.left, insNode);
			} else if(root.data == insNode.data) {
				root.middle = insertNode(root.middle, insNode);
			} else {
				root.right = insertNode(root.right, insNode);
			}
		}
		return root;
	}
	
	// post: Deletes a node with the given value from the tri-nary tree. Returns
	//		 a TrinaryTreeNode.
	private TrinaryTreeNode deleteNode(TrinaryTreeNode root, int value) {
		if(root != null) {			
			if(root.data == value) {
				if(root.middle != null) {
					TrinaryTreeNode left = root.left;
					TrinaryTreeNode right = root.right;
					root = root.middle;
					root.left = left;
					root.right = right;
				} else if(root.right != null) {
					TrinaryTreeNode left = root.left;
					root = root.right;
					insertNode(root, left);
				} else {
					root = root.left;
				}
			}
			root.left = deleteNode(root.left, value);
			root.right = deleteNode(root.right, value);
		}
		return root;
	}
	
	// post: Prints the values in the TrinaryTree.
	private void printTree(TrinaryTreeNode root) {
		if(root != null) {
			System.out.print(root.data + " ");
			printTree(root.middle);
			printTree(root.left);
			printTree(root.right);
		}
	}
}
