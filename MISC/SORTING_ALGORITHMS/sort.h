#ifndef SORT_H
#define SORT_H
#include <stdlib.h>

/**
 * struct listint_s - Doubly linked list node
 * @n: Integer stored in the node
 * @prev: Pointer to the previous element of the list
 * @next: Pointer to the next element of the list
 */
typedef struct listint_s
{
	const int n;
	struct listint_s *prev;
	struct listint_s *next;
} listint_t;

void print_array(const int *array, size_t size);
void print_list(const listint_t *list);
void bubble_sort(int *array, size_t size);
void insertion_sort_list(listint_t **list);
void insertion_swap(listint_t *n1, listint_t *n2, listint_t **head);
void selection_sort(int *array, size_t size);
void quick_sort(int *array, size_t size);
void swap(int *num1, int *num2);
size_t partition_array(int *arr, size_t low, size_t pt, size_t size);
void qSort_helper(int *arr, size_t low, size_t high, size_t size);

#endif
