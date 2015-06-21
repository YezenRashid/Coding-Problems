// StackDriver.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include "IntStack.h"
using namespace std;

int main() {
	int pause;
	IntStack stack1;
	stack1.Push(8);
	
	for (int i = 0; i < INITIAL_STACK_CAPACITY; i++) {
		stack1.Push(i);
	}

	cout << stack1.getCount() << endl;
	cout << stack1.Pop() << endl;
	cout << stack1.Pop() << endl;


	cin >> pause;

	return 0;
}

