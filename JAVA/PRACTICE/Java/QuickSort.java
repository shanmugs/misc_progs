package Java;

import java.util.Arrays;

class QuickSort{

    static void swap(int[]items, int leftPointer, int rightPointer){

        int temp;
        temp = items[leftPointer];
        items[leftPointer] = items[rightPointer];
        items[rightPointer] = temp;
    }

    static int partition(int[] items, int leftPointer, int rightPointer){
        
        int pivot = items[ (int)Math.floor( ( rightPointer + leftPointer)/2 )];

        while(leftPointer <= rightPointer){
        
        while(items[leftPointer] < pivot ){
            leftPointer ++;
        }

        while(items[rightPointer] > pivot){
            rightPointer --;
        }

        if(leftPointer <= rightPointer){
            swap(items, leftPointer, rightPointer);
            leftPointer ++;
            rightPointer --;
        }

        }

        return leftPointer;    

    }
   
    static int[] quickSort(int[] items, int leftPointer, int rightPointer){

        int index ; 
        
        if(items.length > 1){
            index = partition(items, leftPointer, rightPointer);
        if(leftPointer < index - 1){
            quickSort(items, leftPointer, index - 1);
        }
        if(index < rightPointer){
            quickSort(items, index, rightPointer);
        }
        }
        return items;
    }

    public static void main(String[] args) {
        
        int items[] = {9,12,4,1,3,8,5,10,6,11};

        int sortedItems[] = quickSort(items, 0, items.length - 1);

        System.out.println(Arrays.toString(sortedItems));
    }
}