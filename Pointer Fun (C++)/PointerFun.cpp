#include <iostream>
using namespace std;

int main()
{
	int *p = new int;
	cout << p << endl;
	cout << *p << endl;

	*p = 23;

	cout << p << endl;
	cout << *p << endl;

	delete p;
	return 0;
}

