// Yezen Rashid
// The TrinaryTreeNode class is used to store a single node of a tri-nary
// tree of integers.

public class TrinaryTreeNode implements Comparable<TrinaryTreeNode> {
	public int data;
	public TrinaryTreeNode left;
	public TrinaryTreeNode right;
	public TrinaryTreeNode middle;
	
	// post: constructs a leaf node with given data
	TrinaryTreeNode(int data) {
		this(data, null, null, null);
	}
	
	// post: constructs a TrinaryTreeNode with the given data, left subtree, 
	//       right subtree, and middle subtree.
	TrinaryTreeNode(int data, TrinaryTreeNode left, TrinaryTreeNode right, TrinaryTreeNode middle) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.middle = middle;
	}
	
	// post: returns an integer based on comparing this TrinaryTreeNode's data to 
	//       another TrinaryTreeNode's data.
	public int compareTo(TrinaryTreeNode other) {
		return this.data - other.data;
	}
}
