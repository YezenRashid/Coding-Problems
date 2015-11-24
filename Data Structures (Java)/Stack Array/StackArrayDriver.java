
public class StackArrayDriver {
	public static void main(String[] args) {
		StackArray s = new StackArray(5);
		
		for(int i = 0; i < 2000; i++) {
			s.push(i);
		}
		
		while(!s.isEmpty()) {
			System.out.println(s.pop() + " ");
		}
	}
}
