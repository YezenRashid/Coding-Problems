public static void printFactors(int n) {
    for(int i = 1; i <= n / 2; i++) {
        if(n % i == 0) {
            System.out.print(i + " and ");
        }
    }
    
    System.out.println(n);
}