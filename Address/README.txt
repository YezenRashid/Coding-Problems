Assuming that a system has a 32-bit virtual address, write a Java program
that is passed (1) the size of a page and (2) the virtual address. Your 
program will report the page number and offset of the given virtual
address with the specified page size. Page sizes must be specified as a
power of 2 and within the range 1024 —16384 (inclusive). Assuming
such a program is named Address, it would run as follows:

java Address 4096 19986
and the correct output would appear as:
The address 19986 contains:
page number = 4