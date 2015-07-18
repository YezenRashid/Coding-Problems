public static String toBinary(int n) {
    if(n == 0) {
        return "0";
    }
    
    String binary = "";
    while(n != 0) {
        int bin = n % 2;
        binary = bin + binary;
        n = n / 2;
    }
    
    return binary;
}