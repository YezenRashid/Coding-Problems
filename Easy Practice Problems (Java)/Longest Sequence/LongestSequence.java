
public class LongestSequence {
	public static void main(String[] args) {
		int[] arr1 = {1};
		int[] arr2 = {1, 2, 3, 4, 10, 9, 8, 7, 4};
		int[] arr3 = {5, 4, 3, 2, 1};
		int[] arr4 = {1, 10, 20, 40, 50, 100};
		int[] arr5 = {1, 4, 3, 5, 6, 2, 10, 12, 15, 16, 16};
		int[] arr6 = {9, 10, 11, 12, 13, 14, 3, 4, 5};
		int[] arr7 = {1, 1, 1, 1, 1};
		int[] arr8 = {};
		System.out.println(longestSeq(arr1));
		System.out.println(longestSeq(arr2));
		System.out.println(longestSeq(arr3));
		System.out.println(longestSeq(arr4));
		System.out.println(longestSeq(arr5));
		System.out.println(longestSeq(arr6));
		System.out.println(longestSeq(arr7));
		System.out.println(longestSeq(arr8));
	}
	
	public static int longestSeq(int[] array) {
		if(array.length == 0) {
			return 0;
		}
		
		int maxSeq = 1;
		int count = 1;
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i] <= array[i + 1]) {
				count++;
				if(count > maxSeq) {
					maxSeq = count;
				}
			} else {
				count = 1;
			}
		}
		
		return maxSeq;
	}
}
