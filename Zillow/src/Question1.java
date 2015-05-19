// Yezen Rashid
// Zillow Question #1 and Test of Question #2
// This file tests the Trinary Tree and prints it.  The function stringToLong()
// is also created in this file as well which converts a string to a long.

public class Question1 {
	public static void main(String[] args) {
		test();
		
		TrinaryTree tree = new TrinaryTree();
		tree.insertNode(new TrinaryTreeNode(5));
		tree.insertNode(new TrinaryTreeNode(4));
		tree.insertNode(new TrinaryTreeNode(9));
		tree.insertNode(new TrinaryTreeNode(5));
		tree.insertNode(new TrinaryTreeNode(7));
		tree.insertNode(new TrinaryTreeNode(2));
		tree.insertNode(new TrinaryTreeNode(2));
		tree.insertNode(new TrinaryTreeNode(14));
		tree.insertNode(new TrinaryTreeNode(10));
		
		tree.printTree();
		
		tree.deleteNode(9);
		tree.deleteNode(5);
		tree.deleteNode(5);
		tree.deleteNode(24);
		
		tree.printTree();
	}
	
	// pre: Long.MIN_VALUE <= value for conversion <= Long.MAX_VALUE (throws 
	//      IllegalArgumentException if not). Throws IllegalArgumentException 
	//		if string is null.  '0' <= character <= '9' (throws IllegalArgumentException if not)		
	// 		Number separators such as commas or periods are not accepted. Example: "10,000" 
	//		and "10.323" is not accepted. Only '+' and '-' are acceptable symbols at the 
	//		beginning of a string for conversion.
	// post: Converts a string of integers into a long. 
	public static long stringToLong(String s) {
		if(s == null) {
			throw new IllegalArgumentException("Null String");
		}
		
		long num = 0;
		boolean negative = false;
		if(s != "" && s.charAt(0) == '-') {
			s = s.substring(1);
			negative = true;
		}
		
		if(s != "" && s.charAt(0) == '+') {
			s = s.substring(1);
		}
		
		if (s.length() > String.valueOf(Long.MAX_VALUE).length()) {
			throw new IllegalArgumentException("Number overflow");			
		}
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if(c >= '0' && c <= '9') {
				c -= '0';
				// To catch numbers more than Long.MAX_VALUE and less than Long.MIN_VALUE
				if((num == Long.MAX_VALUE / 10) && 
						((negative && c ==9) || (!negative && c >7))) {
					throw new IllegalArgumentException("Number overflow");
				} else {
					num = (num * 10) + c;
				}
			} else {
				throw new IllegalArgumentException("Conversion error");
			}
		}
		
		if(negative) {
			num = -num;
		}
		
		return num;
	}
	
	// Tests stringToLong() function
	public static void test()	{
		
		 String s = String.valueOf(898);
		 long i = stringToLong(s);

		 System.out.println(i);
	
		 s = String.valueOf("-9223372036854775808");
		 i = stringToLong(s);
		 
		 System.out.println(i);
		 
		 if (i == (Long.MIN_VALUE)) { 
			 System.out.println("Success!");
		 } else {
			 System.out.println("Failure!");
		 }
	}
}
