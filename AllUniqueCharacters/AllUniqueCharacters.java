import java.io.*;

public class AllUniqueCharacters {
	public static void main(String[] args) {
		String s = "abcdef";
		System.out.println(allUnique(s));
	}
	
	// Assumes only ASCII characters. Also considers upper case and lower case 
	// different characters.
	public static boolean allUnique(String s) {
		boolean[] chars = new boolean[256];
		
		for(int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			if(chars[c]) {
				return false;
			} else {
				chars[c] = true;
			}
		}
		return true;
	}	
}
