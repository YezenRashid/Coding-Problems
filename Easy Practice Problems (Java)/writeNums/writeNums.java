public�static�void�writeNums(int�n)�{
    if(n < 1) {
        throw new IllegalArgumentException("Value less than 1");
    }
    
    if(n == 1) {
        System.out.print(n);
    } else {
        writeNums(n - 1);
        System.out.print(", " + n);
    }
}