
public class insertBits {
	public static void main(String[] args) {
//		System.out.println(Integer.toBinaryString(255));
//		System.out.println(Integer.toBinaryString(19));
//		System.out.println(Integer.toBinaryString(insertBits(255, 19, 2, 6)));
		
		double realNumb = 0.72;
		printRealBinary(realNumb);
	}
	
	public static int insertBits(int N, int M, int i, int j) {
		int leftMask = ~((1 << j) - 1);
		int rightMask = ~((-1 << i));
		int totalMask = rightMask | leftMask;
		int insertM = (M << i);
		int result = N & totalMask; 
		return result | insertM;
	}
	
	public static void printRealBinary(double realNumb) {
		String result = "";
		while(realNumb != 1.0 && result.length() <= 32) {
			realNumb = realNumb * 2;
			int whole = (int) realNumb;
			realNumb = realNumb % 1;
			result = result + whole;
		}
		
		System.out.println(result);
	}
}
