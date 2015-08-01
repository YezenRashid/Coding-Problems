public static boolean isMagicSquare(int[][] a) {
    if (a.length == 0 || a.length == 1 && a[0].length == 1) {
        return true;   // trivial cases; empty and one-element array
    }
    
    // get sum of first row, use as basis for others
    int sum = 0;
    for (int c = 0; c < a[0].length; c++) {
        sum += a[0][c];
    }
    
    // compute all row sums
    for (int r = 0; r < a.length; r++) {
        if (a[r].length != a.length) {
            return false;   // not square shape
        }
        int rowSum = 0;
        for (int c = 0; c < a[r].length; c++) {
            rowSum += a[r][c];
        }
        if (rowSum != sum) {
            return false;
        }
    }
    
    // compute all column sums
    for (int c = 0; c < a.length; c++) {
        int colSum = 0;
        for (int r = 0; r < a.length; r++) {
            colSum += a[r][c];
        }
        if (colSum != sum) {
            return false;
        }
    }
    
    // compute diagonal sums
    int diagSum1 = 0;
    int diagSum2 = 0;
    for (int rc = 0; rc < a.length; rc++) {
        diagSum1 += a[rc][rc];
        diagSum2 += a[rc][a.length - 1 - rc];
    }
    if (diagSum1 != sum || diagSum2 != sum) {
        return false;
    }
    
    return true;
}