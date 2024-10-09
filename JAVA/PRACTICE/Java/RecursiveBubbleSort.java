package Java;

import java.util.Arrays;

class RecursiveBubbleSort{

    static void sort(int data[], int length){

        if(length == 1) return;

        for(int i = 0; i < length -1 ; i++){
            if(data[i] > data[i+1]){
                int temp = data[i];
                data[i] = data[i+1];
                data[i+1] = temp;
            }
        }
        sort(data,length -1);
    }

    public static void main(String[] args) {
        int data[] = {5,12,10,6,1,9,2};
        sort(data, data.length);
        System.out.println(Arrays.toString(data));
    }
}