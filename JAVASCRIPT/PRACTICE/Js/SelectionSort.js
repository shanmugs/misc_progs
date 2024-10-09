class SelectionSort{

    sortNumbers = (numbers) =>{

        for(let i =0; i < numbers.length ; i++){
            let min = i;
            for(let j = i + 1; j < numbers.length; j ++){
                if(numbers[j] < numbers[min]){
                    min = j;
                }
            }
            
            [numbers[i], numbers[min] ] = [ numbers[min], numbers[i]];

        }

        console.log(numbers);

    };
}
const selectionSort = new SelectionSort();
selectionSort.sortNumbers([10,7,3,5,8,11,4,2])