#include "Rational.h"
#include <algorithm>
using namespace std;

Rational::Rational()
{
	numerator = 0;
	denominator = 0;
}

Rational::Rational(int num, int dem)
{
	numerator = num;
	denominator = dem;
	Reduce();
}

int Rational::getNumerator() const {
	return numerator;
}

int Rational::getDenominator() const {
	return denominator;
}

void Rational::setNumerator(int num) {
	numerator = num;
	Reduce();
}

void Rational::setDenominator(int dem) {
	denominator = dem;
	Reduce();
}

Rational Rational::operator*(const Rational &rat) const
{
	Rational tempRat;

	tempRat.numerator = numerator * rat.numerator;
	tempRat.denominator = denominator * rat.denominator;
	tempRat.Reduce();

	return tempRat;
}

void Rational::Reduce() {
	int gcd = 1;
	for (int i = 2; i <= min(numerator, denominator); i++)
	{
		if (((numerator % i) == 0) && ((denominator % i) == 0))
		{
			gcd = i;
		}
		if (gcd > 1)
		{
			numerator /= gcd;
			denominator /= gcd;
		}
	}
}

Rational::~Rational()
{
}
