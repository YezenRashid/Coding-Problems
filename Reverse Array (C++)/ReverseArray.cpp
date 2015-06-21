// Reverses an array using pointer arithmetic.

#include <iostream>
using namespace std;

void reverseArray(int anArray[], int length);
void printArray(int *array, int length);

void reverseArray2(int *array, int length)
{
	int *next = array + length - 1;
	while (array < next)
	{
		int temp = *array;
		*array = *next;
		*next = temp;
		array++;
		next--;
	}
}

int main()
{
	int wait;

	int arr1[6] = { 100, 200, 300, 4, 5, 6 };
	int *dynArr;
	int size;

	cout << "Input size of array: ";
	cin >> size;

	dynArr = new int[size];
	//dynArr = (int *) malloc(size * sizeof(int));  Another way to do it

	for (int i = 0; i < size; i++)
	{
		dynArr[i] = i * 100;
	}

	reverseArray2(dynArr, size);
	printArray(dynArr, size);

	reverseArray(dynArr, size);
	printArray(dynArr, size);

	reverseArray(dynArr, size);
	printArray(dynArr, size);

	delete(dynArr);

	//free(dynArr); delete malloc
	cin >> wait;
	return 0;
}

void reverseArray(int array[], int length)
{
	for (int i = 0; i < length / 2; i++)
	{
		int temp = *(array + i);
		*(array + i) = *(array + length - 1 - i);
		*(array + length - 1 - i) = temp;
	}
}

void printArray(int *array, int length)
{
	for (int i = 0; i < length; i++)
	{
		//cout << "the value of (array + i) is: " << (int)(array + i) << endl;
		cout << "array[" << i << "] = " << *(array + i) << endl;
	}
}