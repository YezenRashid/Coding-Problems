Write a method leetSpeak that accepts two parameters: a Scanner representing an input file, and a PrintStream representing an output file. Your method should convert the input file's text to "leet speak" (aka 1337 speak), an internet dialect where various letters are replaced by other letters/numbers. Output the leet version of the text to the given output file. Preserve the original line breaks from the input. Also wrap each word of input in parentheses. Perform the following replacements:

Original character	'Leet' character
o	0
l (lowercase L)	1
e	3
a	4
t	7
s (at the end of a word only)	Z
For example, if the input file lincoln.txt contains the following text:

four score and
seven years ago our

fathers brought forth on this continent
a new nation
And your method is called in the following way:

Scanner input = new Scanner(new File("lincoln.txt"));
PrintStream output = new PrintStream(new File("leet.txt"));
leetSpeak(input, output);
Then after the call, the output file leet.txt should contain the following text:

(f0ur) (sc0r3) (4nd)
(s3v3n) (y34rZ) (4g0) (0ur)

(f47h3rZ) (br0ugh7) (f0r7h) (0n) (7hiZ) (c0n7in3n7)
(4) (n3w) (n47i0n)
You may assume that each token from the input file is separated by exactly one space.

Hint: You may want to use the String object's replace method, which is used as follows:

String str = "mississippi";
str = str.replace("s", "*");    // str = "mi**i**ippi"