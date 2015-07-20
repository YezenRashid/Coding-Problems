public static void swapPairs(String[] array) {
    for(int i = 0; i < array.length - 1; i+=2) {
        String temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
    }
}