#include <iostream>
#include "Rational.h"
using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	int wait;

	Rational rat1(2, 5), rat2(2, 4), rat3;

	cout << "Welcome to our Calculator!" << endl;

	rat3 = rat1 * rat2;
	cout << rat1.getNumerator() << " / " << rat1.getDenominator() << endl;
	cout << rat2.getNumerator() << " / " << rat2.getDenominator() << endl;
	cout << rat3.getNumerator() << " / " << rat3.getDenominator() << endl;

	cin >> wait;
	return 0;
}

