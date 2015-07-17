int main() {
	double x = 0;	
	x = pow2(3.0, 4);
	System.out.println("Expected: 81.0 return: " + x);
	x = pow2(2.0, 9);
	System.out.println("Expected: 512.0 return: " + x);	
	x = pow2(5.0, 7);
	System.out.println("Expected: 78125.0 return: " + x);	
	x = pow2(3.0, 14);	
	System.out.println("Expected: 4782969.0	return: " + x);	
	x = pow2(1.0, 10);	
	System.out.println("Expected: 1.0 return: " + x);	
	x = pow2(10.0, 1);	
	System.out.println("Expected: 10.0 return: " + x);	
	x = pow2(0.0, 8);	
	System.out.println("Expected: 0.0 return: " + x);	
	x = pow2(8.0, 0);	
	System.out.println("Expected: 1.0 return: " + x);	
	x = pow2(3.5, 3);
	System.out.println("Expected: 42.875 return: " + x);
	x = pow2(2.0, -2);	
	System.out.println("Expected: 0.25 return: " + x);
	x = pow2(8.0, -1);		
	System.out.println("Expected: 0.125 return: " + x);
}

public static double pow2(double base, int exp) {
    if(exp == 0) {
        return 1;
    }
    
    boolean neg = false;
    if(exp < 0) {
        neg = true;
        exp = -exp;
    }
    
    double n = base;
    for(int i = 0; i < exp - 1; i++) {
        n *= base;
    }
    
    if(neg) {
        n = 1 / n;
    }
    
    return n;
}