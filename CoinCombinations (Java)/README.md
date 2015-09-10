# Coin Combinations
Given a list of coins and amount in dollars, find all the possible coin combinations which makes the given amount.
     dollar (100), quarter (25) , dime (10) , nickel (5), penny (1)
  coinTypes [100, 25, 10 , 5 ,1 ]
void printCombinations(int sum, int n) // print first N combinations which requires fewest number of coins to get amount “sum”
e.g. 
Input: 11 cents, get first 2
     Total combinations 
           11 pennies, 0 nickel, 0 dimes, 0 quarters, 0 dollars // 11 coins
           6 pennies, 1 nickel, 0 dimes, 0 quarters, 0 dollars   // 7 coins
           1 pennies, 2 nickel, 0 dimes, 0 quarters, 0 dollars   // 3 coins
           1 pennies, 0 nickel, 1 dimes, 0 quarters, 0 dollars   // 2 coins
     first 2 should return  
        void printCombinations(int 11, int 2)
        -> 1 pennies, 2 nickel, 0 dimes, 0 quarters, 0 dollars
        -> 1 pennies, 0 nickel, 1 dimes, 0 quarters, 0 dollars
     first 3 should return 
        void printCombinations(int 11, int 3)
        -> 6 pennies, 1 nickel, 0 dimes, 0 quarters, 0 dollars   // 7 coins
        -> 1 pennies, 2 nickel, 0 dimes, 0 quarters, 0 dollars   // 3 coins
        -> 1 pennies, 0 nickel, 1 dimes, 0 quarters, 0 dollars   // 2 coins