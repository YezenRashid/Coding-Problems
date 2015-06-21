#pragma once
#include <iostream>
using namespace std;

template <class ItemType>
class Stack {
	public:
		Stack();
		Stack(const Stack &);
		~Stack();

		void push(ItemType *item);
		void pop();
		bool isEmpty();
		void printStack();
		ItemType peek() const;

		void operator=(const Stack &source);

	private:
		struct Node {
			ItemType *value = NULL;
			Node *next = NULL;
		};

		Node *head;
		void clearStack();
};