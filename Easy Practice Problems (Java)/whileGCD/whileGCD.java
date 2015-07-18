public static int whileGCD(int a, int b) {   
    while(b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }
    
    return Math.abs(a);
}