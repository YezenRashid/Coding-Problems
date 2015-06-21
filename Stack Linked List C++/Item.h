#pragma once
#include <string>
using namespace std;

class Item {
public:
	Item(string n) { name = n; }
	~Item();

	friend ostream& operator<<(ostream &output, const Item &);

private:
	string name;
};

ostream& operator<<(ostream &output, const Item &i) {
	output << i.name;
	return output;
}