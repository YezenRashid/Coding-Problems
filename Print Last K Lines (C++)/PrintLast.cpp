#include <stdio.h>
#include <string>
#include <fstream>
#include <iostream>
#include <math.h>
using namespace std;

void printLast(string fileName, int k);

void main()
{
	int wait;

	string fileName = "names.txt";
	printLast(fileName, 5);
	cin >> wait;
}

void printLast(string fileName, int k) {
	ifstream inputFile(fileName);
	string line;
	string * lastLines = new string[k];
	int counter = 0;

	while (getline(inputFile, line)) {
		lastLines[counter % k] = line;
		counter++;
	}

	for (int i = 0; i < (k > counter ? counter : k); i++) {
		cout << lastLines[((counter % k) + i) % k] << endl;
	}

	//delete lastLines;
}