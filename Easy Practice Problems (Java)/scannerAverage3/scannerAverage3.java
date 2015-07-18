Scanner console = new Scanner(System.in);
double sum = 0;

for(int i = 0; i < 3; i++) {
    System.out.print("Type an integer: ");
    while(!console.hasNextInt()) {
        String trash = console.next();
        System.out.print("Type an integer: ");
    }  
    
    sum += console.nextInt();
}

System.out.println("Average: " + (sum / 3.0));