#include "IntStack.h"

IntStack::IntStack() {
	count = 0;
}

int IntStack::getCount() const {
	return count;
}

int IntStack::getStackCapacity() const {
	return stackCapacity;
}

bool IntStack::Push(int x)  {
	//if (isFull()) {
	//	const int cap = stackCapacity;
	//	int newStack[stackCapacity * 2];
	//	for () {

	//	}
	//}

	if (count < stackCapacity)	{
		stack[count] = x;
		count++;
		return true;
	}

	return false;
}

int IntStack::Pop() {
	if (count > 0) {
		int temp = stack[count];
		count--;
		return stack[count];
	}
}

int IntStack::Peek() const {
	if (count > 0) 	{
		return stack[count - 1];
	} else {
		return -1;
	}
}

bool IntStack::isEmpty() const {
	if (count == 0) {
		return true;
	}

	return false;
}

bool IntStack::isFull() const {
	if (count == INITIAL_STACK_CAPACITY) {
		return true;
	}

	return false;
}

IntStack::~IntStack() {
}
