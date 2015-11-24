public class QueueDriver {
	public static void main(String[] args) {
		Queue q = new Queue();
		
		for(int i = 0; i < 100; i++) {
			q.add(i);
		}
		
		while(!q.isEmpty()) {
			System.out.print(q.remove() + " ");
		}
		
		System.out.println();
		
		for(int i = 100; i < 200; i++) {
			q.add(i);
		}
		
		while(!q.isEmpty()) {
			System.out.print(q.remove() + " ");
		}
		
	}
}
