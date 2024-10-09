#include "sort.h"

/**
 * selection_sort - Sorts an array of integers in
 * ascending order using the Selection sort algorithm
 * @array: Pointer to array
 * @size: Size of array
 * Return: void
 */
void selection_sort(int *array, size_t size)
{
	size_t i, j, min;
	int temp;

	if (array == NULL || size < 2)
		return;

	for (i = 0; i < size - 1; i++)
	{
		/* keep track of minimum */
		min = i;
		for (j = i + 1; j < size; j++)
		{
			if (array[j] < array[min])
				min = j;
		}

		if (min == i)
			continue;

		temp = array[i];
		array[i] = array[min];
		array[min] = temp;

		print_array(array, size);
	}

}
