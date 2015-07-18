public static void diceSum() {
    Random r = new Random();
    Scanner console = new Scanner(System.in);
    
    System.out.print("Desired dice sum: ");
 
    while(!console.hasNextInt()) {
        String trash = console.next();
        System.out.print("Desired dice sum: ");
    }
    
    int diceSum = console.nextInt();
    int sum = 0;
    
    while(diceSum != sum) {
        int die1 = r.nextInt(6) + 1;
        int die2 = r.nextInt(6) + 1;
        sum = die1 + die2;
        
        System.out.println(die1 + " and " + die2 + " = " + sum);
    }
}