Simple Java Asymmetric Encryption Class.

In Progress...
Still dealing with big Integers when encrypting and decrypting.
Need to create a random 512 bit prime number instead of what I currently have. Which is any prime number from 0-1000 for greatly enhanced security.

The Crypto class runs an example string "The quick brown fox jumps over the lazy dog” and shows the cyphertext output + decrypted plaintext recovered.

Algorithm Overview:
Formally, it is computationally infeasible to derive
D(kd , N) from E(ke , N), and so E(ke , N) need not be kept secret and can be widely disseminated
● E(ke , N) (or just ke) is the public key 
● D(kd , N) (or just kd) is the private key
● N is the product of two large, randomly chosen prime numbers p and q (for example, p and q are 512 bits each)
● Encryption algorithm is E(ke , N)(m) = mke mod N, where ke
satisfies kekd mod (p−1)(q −1) = 1
● The decryption algorithm is then D(kd , N)(c) = ckd mod N

An Example using this program would be:
Make p = 7and q = 13 
■We then calculate N = 7∗13 = 91 and (p−1)(q−1) = 72 
■We next select ke relatively prime to 72 and < 72, yielding 5 
■Finally, we calculate kd such that kekd mod 72 = 1, yielding 29 
■We now have our keys
  ●Public key, ke, N = 5, 91 
  ●Private key, kd , N = 29, 91
■Encrypting the message 69 with the public key results in the
cyphertext 62
■Cyphertext can be decoded with the private key
●Public key can be distributed in cleartext to anyone who wants to
communicate with holder of public key
