// This is a simple asymmetric encryption class.

import java.io.*;
import java.util.*;

public class AsymCrypto {
	public static void main(String[] args) {
		String s = "The quick brown fox jumps over the lazy dog";
		int p = generateRandomPrime();
		int q = generateRandomPrime();

		AsymCrypto cryp = new AsymCrypto(p, q, 1232139, 23214213);
	}
	
	// ke represents the public key
	// kd represents the private key
	AsymCrypto(int p, int q, int Kd, int Ke) {
		int N = p * q;
		int relativeMax = (p - 1) * (q - 1);
		
		//int relativePrime = findGCD(relativeMax, Ke);
		
		Ke = 0;
	}
	
	public static int Encrypt(int m) {
		return 0;
	}
	
	public static int Decrypt(int m) {
		return 0;
	}
	
	// Generates and then returns a random prime number.
	private static int generateRandomPrime() {
		Random r = new Random(); // generates a random number
		int n = r.nextInt(1000) + 1; // between 0 and 1000 could be larger
		
		while(!isPrime(n)) {
			n = r.nextInt(1000) + 1;
		}
		
		return n;
	}
	
	// Checks if a number is prime or not.
	private static boolean isPrime(int n) {
		if (n % 2 == 0) {
			return false;
		}
		
		for (int i = 3; i * i <= n; i+=2) {
			if(n % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
