
public class StringPalindrome {
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "racecar";
		String s3 = "helloolleh";
		String s4 = "wooooow";
		String s5 = "Yezen";
		String s6 = "h";
		String s7 = "";
		
		System.out.println(isPalindrome(s1));
		System.out.println(isPalindrome(s2));
		System.out.println(isPalindrome(s3));
		System.out.println(isPalindrome(s4));
		System.out.println(isPalindrome(s5));
		System.out.println(isPalindrome(s6));
		System.out.println(isPalindrome(s7));
	}
	
	public static boolean isPalindrome(String s) {
		int end = s.length() - 1;
		for(int i = 0; i < s.length() / 2; i++) {
			if(s.charAt(i) != s.charAt(end)) {
				return false;
			}
			end--;
		}
		
		return true;
	}
}
