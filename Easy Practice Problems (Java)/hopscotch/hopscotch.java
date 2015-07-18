public static void hopscotch(int splits) {
    int count = 1;
    
    for(int i = 0; i < splits; i++) {
        System.out.println("   " + count++);
        System.out.println(count++ + "     " + count++);
    }
    System.out.println("   " + count);
}