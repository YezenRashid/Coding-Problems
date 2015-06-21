#pragma once
const int INITIAL_STACK_CAPACITY = 100;

class IntStack {
public:
	IntStack();
	bool Push(int num);
	int Pop();
	int Peek() const;
	int getCount() const;
	int getStackCapacity() const;
	bool isEmpty() const;
	bool isFull() const;
	~IntStack();

private:
	int stackCapacity = INITIAL_STACK_CAPACITY;
	int *stack;
	int count;
};

