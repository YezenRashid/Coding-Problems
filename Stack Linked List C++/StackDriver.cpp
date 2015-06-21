// Driver to test the stack class.

#include <iostream>
#include "Stack.h"
#include "Item.h"
using namespace std;

void main() {
	int pause;
	Stack<Item> stack1;
	Stack<Item> stack2;

	Item i1("ann"), i2("Selma"), i3("Jack"), i4("Bob"), i5("Sarah");

	stack1.push(&i1);
	stack1.push(&i2);
	stack1.push(&i3);
	stack1.push(&i4);
	stack1.push(&i5);

	stack1.printStack();
	stack2 = stack1;

	cout << endl;

	while (!stack1.isEmpty()) {
		cout << stack1.peek() << endl;
		stack1.pop();
	}

	cout << endl;

	stack1.printStack();
	stack2.printStack();

	cin >> pause;
}

