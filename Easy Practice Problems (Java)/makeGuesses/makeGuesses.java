public static void makeGuesses() {
    Random rand = new Random();
    int guesses = 0;
    int num = 1;
    
    while(num < 48) {
        num = rand.nextInt(50) + 1;
        System.out.println("guess = " + num);
        guesses++;
    }
    
    System.out.println("total guesses = " + guesses);
}