
public class AllStringPermutations {
	public static void main(String[] args) {
		String s = "Hello";
		printAllPermutations(s, "");
	}
	
	public static void printAllPermutations(String s, String permutation) {
		if(s.length() == 0) {
			System.out.println(permutation);
		} else {
			for(int i = 0; i < s.length(); i++) {
				printAllPermutations(s.substring(0, i) + s.substring(i + 1, s.length()), permutation + s.charAt(i));
			}
		}
	}
}
