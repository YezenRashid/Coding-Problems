// Templatized stack class using a linked-list.

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
		ItemType pop();
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

// constructor
template <class ItemType>
Stack<ItemType>::Stack() {
	head = NULL;
}

// Copy Constructor for a deep copy
template <class ItemType>
Stack<ItemType>::Stack(const Stack &source) {
	this->head = NULL;
	*this = source;
}

// Deconstructor removes all items from stack.
template <class ItemType>
Stack<ItemType>::~Stack() {
	this->clearStack();
}

// Takes an item and places it on the top of the stack.
template <class ItemType>
void Stack<ItemType>::push(ItemType *item) {
	Node *i = new Node;
	i->value = item;
	i->next = head;
	head = i;
}

// Removes the top element from the stack and returns the data held in it.
template <class ItemType>
ItemType Stack<ItemType>::pop() {
	Node *i = head;

	if (head == NULL) {
		cerr << "Stack is Empty, no elements to remove." << endl;
		return NULL;
	}

	ItemType *data = i->value;
	head = head->next;

	delete i;
	return *data;
}

// Returns true if empty, false otherwise.
template <class ItemType>
bool Stack<ItemType>::isEmpty() {
	return head == NULL;
}

// Returns the item that is at the top of the stack
template <class ItemType>
ItemType Stack<ItemType>::peek() const {
	return *head->value;
}

template <class ItemType>
void Stack<ItemType>::printStack() {
	Node *reader = head;
	
	while (reader != NULL) {
		cout << *reader->value << endl;
		reader = reader->next;
	}
}

// Creates a deep copy of the stack
template <class ItemType>
void Stack<ItemType>::operator=(const Stack &source) {
	if (this != &source) {
		Node *sNode, *dNode, *insNode;
		this->clearStack();

		if (source.head != NULL) {
			dNode = new Node;
			dNode->value = (source.head)->value;
			this->head = dNode;
			sNode = (source.head)->next;

			while (sNode != NULL) {
				insNode = new Node;
				insNode->value = sNode->value;
				dNode->next = insNode;
				dNode = dNode->next;
				sNode = sNode->next;
			}
		}
	}
}

// Removes all elements from the stack
template <class ItemType>
void Stack<ItemType>::clearStack() {
	while (head != NULL) {
		pop();
	}
}