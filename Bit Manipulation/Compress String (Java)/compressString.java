
public class compressString {
	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "abc hello my name is yezen";
		
		System.out.println(compress(s1));
	}
	
	public static String compress(String s) {
		String compressed = "";
		for(int i = 0; i < s.length(); i++) {
			int singleByte = (int) s.charAt(i);
			singleByte = singleByte << 3;
			
		}
		
		return compressed;
	}
}
