// Reverses a String in C++

#include <iostream>
using namespace std;

void reverse(char* s);

void main() {
	int wait;
	char sentence[] = "Hello My name is Yezen";
	char *s = sentence;
	reverse(s);
	cout << sentence << endl;
	cin >> wait;
}

void reverse(char *s) {
	char * last = s;
	char temp;

	if (s) {
		while (*last) {
			last++;
		}

		last--;

		while (s < last) {
			temp = *s;
			*s = *last;
			*last = temp;
			s++;
			last--;
		}
	}
}