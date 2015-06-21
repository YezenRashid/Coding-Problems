// Generic Item class with one field to test Stack.

#pragma once
#include <string>
using namespace std;

class Item {
public:
	Item(string itemName);
	~Item();

	void operator=(Item &source);

	friend ostream& operator<<(ostream &output, const Item &);

private:
	string name;
};

Item::Item(string itemName) { 
	name = itemName; 
}

Item::~Item() {
}

ostream& operator<<(ostream &output, const Item &i) {
	output << i.name;
	return output;
}