const swap = (numbers, leftPointer, rightPointer) =>{
      
   [numbers[leftPointer], numbers[rightPointer]] = [numbers[rightPointer], numbers[leftPointer]];

};

const partition = (numbers, leftPointer, rightPointer) =>{

    let pivot = numbers[Math.floor((rightPointer + leftPointer)/2)];
    
    while( leftPointer <= rightPointer){

        while(numbers[leftPointer] < pivot){
           leftPointer ++;
        }

        while(numbers[rightPointer] > pivot){
            rightPointer --;
        }

        if(leftPointer <= rightPointer){
            swap(numbers,leftPointer,rightPointer);
            leftPointer ++;
            rightPointer --;
        }
    }
    return leftPointer;
};

const quickSort = (numbers, leftPointer, rightPointer) =>{

    let index;
    
    if(numbers.length > 1){
        index = partition(numbers,leftPointer, rightPointer);

        if(leftPointer < index -1){
            quickSort(numbers,leftPointer,index -1);
        }
        if(index < rightPointer){
            quickSort(numbers,index,rightPointer);
        }
    }
    return numbers;
};

const numbers = [5,19,20,50,21,2,18,4,1,49];

console.log(quickSort(numbers,0, numbers.length -1));