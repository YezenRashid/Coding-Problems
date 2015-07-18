public static void showTwos(int n) {
    System.out.print(n + " = ");
    while(n % 2 == 0) {
        System.out.print("2 * ");
        n = n / 2;
    }
    System.out.print(n);
}