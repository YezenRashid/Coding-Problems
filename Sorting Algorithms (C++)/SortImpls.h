// Sorting Algorithms
// This file includes MergeSort, InsertSort, BubbleSort, Iterative MergeSort, and QuickSort.

#include <vector>
using namespace std;

void BubbleSort(vector<int> &items, int first, int last)
{
	for (int i = 0; i < last; i++)
	{
		for (int j = 0; j < last - i; j++)
		{
			if (items[j] > items[j + 1])
			{
				int temp = items[j];
				items[j] = items[j + 1];
				items[j + 1] = temp;
			}
		}
	}
}

void InsertSort(vector<int> &items, int first, int last)
{
	for (int i = 1; i < last; i++)
	{
		int j = i + 1;
		while (j > 0 && items[j - 1] > items[j])
		{
			int temp = items[j];
			items[j] = items[j - 1];
			items[j - 1] = temp;
			j--;
		}
	}
}

void Merge(vector<int> &items, int first, int mid, int last)
{
	vector<int> tempArray(items.size());

	int first1 = first;
	int last1 = mid;
	int first2 = mid + 1;
	int last2 = last;

	int index = first1;
	while ((first1 <= last1) && (first2 <= last2))
	{
		if (items[first1] <= items[first2]) 
		{
			tempArray[index] = items[first1];
			first1++;
		}
		else
		{
			tempArray[index] = items[first2];
			first2++;
		}
		index++;
	}

	while (first1 <= last1)
	{
		tempArray[index] = items[first1];
		first1++;
		index++;
	}

	while (first2 <= last2)
	{
		tempArray[index] = items[first2];
		first2++;
		index++;
	}

	for (index = first; index <= last; index++)
	{
		items[index] = tempArray[index];
	}
}

void MergeSort(vector<int> &items, int first, int last)
{
	if (first < last)
	{
		int mid = first + (last - first) / 2;
		MergeSort(items, first, mid);
		MergeSort(items, mid + 1, last);
		Merge(items, first, mid, last);
	}
}

void IterativeMerge(vector<int> &items, vector<int> &tempArray, int first, int next, int last)
{
	int left = first;
	int right = next;

	for (int index = first; index < last; index++)
	{
		if (left < next && (right >= last || items[left] <= items[right]))
		{
			tempArray[index] = items[left];
			left++;
		}
		else
		{
			tempArray[index] = items[right];
			right++;
		}
	}
}

void IterativeMergeSort(vector<int> &items, int first, int last)
{
	vector<int> tempArray(items.size());

	for (int i = 1; i < last; i = i * 2) 
	{
		for (int j = 0; j <= last; j = j + 2 * i)
		{
			IterativeMerge(items, tempArray, j, min(j + i, last + 1), min(j + 2 * i, last + 1));
		}

		for (int k = first; k <= last; k++)
		{
			items[k] = tempArray[k];
		}
	}
}

void sortFirstMiddleLast(vector<int> &items, int first, int mid, int last)
{
	if (items[first] > items[mid])
	{
		swap(items[first], items[mid]);
	}
	if (items[mid] > items[last])
	{
		swap(items[mid], items[last]);
	}
	if (items[first] > items[mid])
	{
		swap(items[first], items[mid]);
	}
}

int partition(vector<int> &items, int first, int last)
{
	int mid = first + (last - first) / 2;
	sortFirstMiddleLast(items, first, mid, last);
	swap(items[mid], items[last - 1]);

	int pivotIndex = first;
	int pivotElement = items[first];

	for (int i = first + 1; i <= last; i++)
	{
		if (items[i] <= pivotElement)
		{
			pivotIndex++;
			swap(items[i], items[pivotIndex]);
		}
	}

	swap(items[pivotIndex], items[first]);
	return pivotIndex;
}

void QuickSort(vector<int> &items, int first, int last)
{
	if (first < last)
	{
		int pivotIndex = partition(items, first, last);
		QuickSort(items, first, pivotIndex - 1);
		QuickSort(items, pivotIndex + 1, last);
	}
}