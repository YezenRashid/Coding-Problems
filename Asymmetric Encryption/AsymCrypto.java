// This is a simple asymmetric encryption class.

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;

public class AsymCrypto {
	private static int privateKey;
	private static int publicKey;
	private static int N;
	
	public static void main(String[] args) {
		String message = "The quick brown fox jumps over the lazy dog";
		int p = generateRandomPrime();
		int q = generateRandomPrime();
		
		AsymCrypto cryp = new AsymCrypto(p, q, 0, 0);
		
		// Encrypt message
		long[] m = new long[message.length()];
		
		for(int i = 0; i < m.length; i++) {
			m[i] = (int) message.charAt(i);
		}
		
		for(int i = 0; i < m.length; i++) {
			m[i] = cryp.Encrypt(m[i]);
		}
		
		System.out.println(m);
		
		for(int i = 0; i < m.length; i++) {
			m[i] = cryp.Decrypt(m[i]);
		}
		
		//String plainText = new String(m);
		
		System.out.println(m);
		
	}
	
	// ke represents the public key
	// kd represents the private key
	// Both of the keys are calculated in the constructor based on the passed
	// prime numbers.
	AsymCrypto(int p, int q, int Kd, int Ke) {
		N = p * q;
		int relativeMax = (p - 1) * (q - 1);
		
		if(relativeMax <= 2) {
			throw new IllegalArgumentException("prime numbers are too small");
		}
		
		int n = relativeMax;
		
		// find smallest relative prime to relativeMax that is less than relativeMax
		while(n > 2) {
			if(relativelyPrime(relativeMax, n)) {
				Ke = n;
			}
			n--;
		}
		
		publicKey = Ke;
		
		Kd = calculateKd(Ke, relativeMax);
		privateKey = Kd;
	}
	
	public static long Encrypt(long m) {
		return (long) Math.pow(m, publicKey) % N;
	}
	
	public static long Decrypt(long m) {
		return (long) Math.pow(m, privateKey) % N;
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
	
	// returns true if a two numbers are relatively prime, false otherwise.
	private static boolean relativelyPrime(int a, int b) {
		return findGCD(a, b) == 1;
	}
	
	// returns the greatest common divisor
	private static int findGCD(int a, int b) {
		if(b == 0) {
			return a;
		}
		
		return findGCD(b, a % b);
	}
	
	// Calculates the private key based upon public key
	private static int calculateKd(int Ke, int relativeMax) {
		int Kd = 0;
		
		while((Kd * Ke % 72) != 1) {
			Kd++;
		}
		
		return Kd;
	}
}
