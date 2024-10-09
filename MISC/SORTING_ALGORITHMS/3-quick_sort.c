#include "sort.h"

/**
 * swap - used to swap in partition function
 * @num1: pointer to first element
 * @num2: pointer to second element
 * Return: void
 */
void swap(int *num1, int *num2)
{
	int temp = *num1;

	*num1 = *num2;
	*num2 = temp;
}


/**
 * partition_array - divides array
 * @arr: pointer to array
 * @low: the start index
 * @pt: The pivot
 * @size: Array size
 * Return: pt
 */
size_t partition_array(int *arr, size_t low, size_t pt, size_t size)
{
	size_t i;

	for (i = low; i < pt; i++)
	{
		if (arr[i] < arr[pt])
		{
			if (i != low)
			{
				swap(&arr[low], &arr[i]);
				print_array(arr, size);
			}
			low++;
		}
	}
	if (arr[low] > arr[pt])
	{
		swap(&arr[low], &arr[pt]);
		pt = low;
		print_array(arr, size);
	}
	return (pt);
}


/**
 * qSort_helper - sorts left and right sections
 * @arr: pointer to array
 * @low: the start index
 * @high: the end index
 * @size: size of array
 * Return: void
 */
void qSort_helper(int *arr, size_t low, size_t high, size_t size)
{
	size_t pt;

	if (high == 0 || high <= low)
		return;

	pt = partition_array(arr, low, high, size);

	if (pt != 0 && pt > low)
		qSort_helper(arr, low, pt - 1, size);

	if (pt < size - 1)
		qSort_helper(arr, pt + 1, high, size);
}


/**
 * quick_sort - Sorts an array of integers in ascending
 * order using the Quick sort algorithm
 * @array: Pointer to array
 * @size: Size of array
 */
void quick_sort(int *array, size_t size)
{
	if (array == NULL || size < 2)
		return;
	qSort_helper(array, 0, size - 1, size);
}
