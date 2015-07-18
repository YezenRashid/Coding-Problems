int main() {	
	System.out.println("Expected: Perfect numbers up to 1: ");
	System.out.println("Result: ");
	perfectNumbers(1);
	
	System.out.println("Expected: Perfect numbers up to 6: 6");
	System.out.println("Result: ");
	perfectNumbers(6);

	System.out.println("Expected: Perfect numbers up to 500: 6 28 496");
	System.out.println("Result: ");	
	perfectNumbers(500);

	System.out.println("Expected: Perfect numbers up to 20: 6");
	System.out.println("Result: ");	
	perfectNumbers(20);
	
	System.out.println("Expected: Perfect numbers up to 2345: 6 28 496");
	System.out.println("Result: ");		
	perfectNumbers(2345);	
}

public static void perfectNumbers(int max) {
    if(max < 0) {
        throw new IllegalArgumentException("Perfect number must be positive integer");
    }
    
    System.out.print("Perfect numbers up to " + max + ":");
    
    for(int i = 1; i <= max; i++) {
        int sum = 0;
        
        for(int j = 1; j <= i / 2; j++) {
            if(i % j == 0) {
                sum += j;
            }
        }
        
        if(i == sum) {
            System.out.print(" " + i);
        }
    }
}
