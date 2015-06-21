#include "Stack.h"

template <class ItemType>
Stack<ItemType>::Stack() {
	head = NULL;
}

template <class ItemType>
Stack<ItemType>::Stack(const Stack &source) {
	this->head = NULL;
	*this = source;
}

template <class ItemType>
Stack<ItemType>::~Stack() {
	this->clearStack();
}

template <class ItemType>
void Stack<ItemType>::push(ItemType *item) {
	Node *i = new Node;
	i->value = item;
	i->next = head;
	head = i;
}

template <class ItemType>
void Stack<ItemType>::pop() {
	Node *i = head;

	if (head == NULL) {
		cerr << "Stack is Empty, no elements to remove." << endl;
		return;
	}

	ItemType *data = i->value;
	head = head->next();
	
	delete data;
	delete i;
}

template <class ItemType>
bool Stack<ItemType>::isEmpty() {
	return head == NULL;
}

template <class ItemType>
ItemType Stack<ItemType>::peek() const {
	return *head->value;
}

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

template <class ItemType>
void Stack<ItemType>::clearStack() {
	while (head != NULL) {
		pop();
	}
}