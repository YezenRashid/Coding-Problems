public static void randomX() {
    Random r = new Random();
    boolean startOver = true;
    
    while(startOver) {
        int x = r.nextInt(15) + 5;
        for(int i = 0; i < x; i++) {
            System.out.print("x");
        }
        
        System.out.println();
        
        if(x >= 16) {
            startOver = false;
        }
    }
}