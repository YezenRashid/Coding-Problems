#pragma once
#include <algorithm>

class Rational
{
public:
	Rational();
	Rational(int num, int dem);
	int getNumerator() const;
	int getDenominator() const;
	void setNumerator(int num);
	void setDenominator(int dem);
	Rational operator*(const Rational &rat) const;
	~Rational();

private:
	int numerator, denominator;
	void Reduce();
};

