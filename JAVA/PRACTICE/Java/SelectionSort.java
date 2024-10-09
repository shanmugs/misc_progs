package Java;

class SelectionSort {

    static void sortNumbers(int numbers[]){

        for(int i = 0 ; i < numbers.length ; i ++){

            int minimum = i;

            for(int j = i + 1; j < numbers.length ; j ++){

                if(numbers[j] < numbers[minimum]){
                    minimum = j;
                }
            }  
        
            int temp =  numbers[minimum];
            numbers[minimum] = numbers[i];
            numbers[i] = temp;

        }       
        
        for(int i =0; i<numbers.length; i++){
            System.out.print(numbers[i] +" ");
        }

    }

    public static void main(String args[]){

        int numbers[] = {5,1,4,2,8,9};

        sortNumbers(numbers);
    }
}