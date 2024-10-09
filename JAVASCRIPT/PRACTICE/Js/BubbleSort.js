class BubbleSort{

    sortNums = (numbers) => {

        for(let i = 0 ; i < numbers.length; i ++ ){
            for(let j = 1; j < numbers.length - i; j ++ ){
                if(numbers[j] < numbers[j - 1]){
                    [ numbers[j-1] , numbers[j] ] = [ numbers[j], numbers[j-1] ];
                }
            }
        }
        console.log(numbers);

    };
}

const bubbleSort = new BubbleSort();

bubbleSort.sortNums([10,7,3,5,8,1,4,2]);