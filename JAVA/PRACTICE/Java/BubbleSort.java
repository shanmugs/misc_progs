package Java;

public class BubbleSort {

    static void sortNumbers(int numbers[]){

        boolean isSorted;

        for(int i=0 ; i<numbers.length ; i ++){
              isSorted = true;
            for(int j = 1 ; j < numbers.length - i;  j ++)

                if(numbers[j] < numbers[j-1]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j-1];
                    numbers[j-1] = temp;
                }
                isSorted = false;

            if(isSorted) return;

        }
        

        for(int i : numbers){
            System.out.print(i + " ");
        }       

    }

    public static void main(String[] args) {
        
        int numbers[] = {5,1,4,2,8,9,3};

        sortNumbers(numbers);
    }
    
}