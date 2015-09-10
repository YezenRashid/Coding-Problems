/*
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
*/

import java.util.*;

public class CoinCombinations {
	   public static int[] coinTypes = {100, 25, 10 , 5 ,1};

	   public static void main(String[] args) {
	      printCombinations(11, 3);
	   }
	   
	   // print first N combinations which requires fewest number of coins to get amount “sum”
	   public static void printCombinations(int sum, int n) {
	      //int[] coinCombination = new int[coinTypes.length];
		  //Stack<Integer> coinCombination = new Stack<Integer>();
	      //printCombinations(sum, n, 0, 0, coinCombination, 0);
		  Stack<Integer> coinCombinations = new Stack<Integer>();
		  printCombinations(sum, n, coinCombinations, 0);
	   }
	   
	   private static void printCombinations(int sum, int max, Stack<Integer> coinCombinations, int count) {
	   		if (sum == 0 && count < max) {
			   System.out.println(coinCombinations);
			   count++;
	   		} else {
	   			for (int i = 0; i < coinTypes.length; i++) {
	   				int currentSum = sum - coinTypes[i];
	   				
	   				if(currentSum >= 0) {
				      coinCombinations.push(coinTypes[i]);
				      printCombinations(currentSum, max, coinCombinations, count);
				      coinCombinations.pop();
	   				}
				}
	   		}
	   }
	   
//	   private static void printCombinations(int sum, int n, int currentSum, int combos, Stack<Integer> coinCombination, int count) {
//		   if(count == coinTypes.length) {
//			   count = 0;
//		   }
//		   
//		   if(n > combos) {
//			   int coin = coinTypes[count];
//			   
//			   if(sum == currentSum) {
//				   System.out.println(coinCombination);
//				   while(!coinCombination.isEmpty()) {
//					   coinCombination.pop();
//				   }
//				   printCombinations(sum, n, 0, combos + 1, coinCombination, count + 1);
//			   } else if(currentSum < sum) {
//				   coinCombination.push(coin);
//				   printCombinations(sum, n, currentSum + coin, combos, coinCombination, count);
//			   } else {
//				   if(!coinCombination.isEmpty()) {
//					   coin = coinCombination.pop();
//					   printCombinations(sum, n, currentSum - coin, combos, coinCombination, count + 1);
//				   }
//			   }
//		   }
//	   }
}
