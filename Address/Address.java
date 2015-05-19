// Program that finds the page number and offset when given the page size
// and the virtual address.  Assumes that a system has a 32-bit virtual address

import java.io.*;

public class Address {
	public static final int VIRTUAL_ADDRESS = 32;	// 32 bit virtual address
	
	public static void main(String[] args) {
		if(args.length != 2) {
			throw new IllegalArgumentException("Invalid arguments");
		}
		
		int pageSize = Integer.parseInt(args[0]);
    	int address = Integer.parseInt(args[1]);
		
		if(pageSize < 1024 || pageSize > 16384) {
			throw new IllegalArgumentException("Page size is not within the range of " +
					"1024 - 16384");
		}
		
		if((pageSize & (pageSize - 1)) != 0) { // determines if the passed pageSize is a power of 2
			throw new IllegalArgumentException("Page size is not a power of 2");
		}
		
		double virtualAddressSize = Math.pow(2, VIRTUAL_ADDRESS);
		double pages = virtualAddressSize / pageSize; 
		int pageBits = (int) (Math.log(pages) / Math.log(2)); // find how many page bits 
		int otherBits = VIRTUAL_ADDRESS - pageBits; //Find the other bits
		
		int pageNumber = (address >> otherBits); // bit shift to only keep page bits for page number
		int offset = (address & (pageSize - 1)); // find the offset
		
		System.out.println("The address " + address + " contains:");
		System.out.println("page number = " + pageNumber);
		System.out.println("offset = " + offset);
	}
}
