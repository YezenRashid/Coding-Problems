
public class DirectoryPath {
	public static void main(String[] args) {
		String filePath = "C:\\Directory1\\Directory2\\hello\\name\\d1\\filename.txt";
		int searchBarLength = 22;
		System.out.println(newFilePath(filePath, searchBarLength));
	}
	
	public static String newFilePath(String filePath, int searchBarLength) {
		if(searchBarLength <= 0 || filePath == null) {
			return null;
		}
		
		if(filePath.length() <= searchBarLength) {
			return filePath;
		}
		
		int length = searchBarLength - 7;
		String result = "";
		int index1 = filePath.length() - 1;
		
		for(int i = filePath.length() - 1; i >= 3; i--) {
			if(filePath.charAt(i) == '\\') {
				int dirLength = index1 - i;
				if(dirLength <= length) {
					result = filePath.substring(i, index1) + result;
					length -= dirLength;
				} else {
					break;
				}
				
				index1 = i;
			}
		}
		
		
		return filePath.substring(0, 3) + "..." + result;
	}
}
