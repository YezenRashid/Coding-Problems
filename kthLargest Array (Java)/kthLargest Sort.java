public static int kthLargest(int k, int[] a) {
    int[] a2 = new int[a.length];
    for (int i = 0; i < a.length; i++) {
        a2[i] = a[i];
    }
    Arrays.sort(a2);
    return a2[a2.length - 1 - k];
}