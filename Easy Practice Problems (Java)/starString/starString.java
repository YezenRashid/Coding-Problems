public static String starString(int n) {
    if(n < 0) {
        throw new IllegalArgumentException("Cannot pass negative value");
    } else if (n == 0) {
        return "*";
    } else {
        return starString(n - 1) + starString(n - 1);
    }
}