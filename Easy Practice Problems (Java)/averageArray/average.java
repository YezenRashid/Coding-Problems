public static double average(int[] array) {
    double average = 0;
    
    for(int i = 0; i < array.length; i++) {
        average += array[i];
    }
    
    average = average / array.length;
    
    return average;
}