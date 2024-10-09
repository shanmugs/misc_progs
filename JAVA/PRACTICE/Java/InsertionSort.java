package Java;

class InsertionSort{

    static void sort(int[] data){

        int length = data.length;

        for(int i = 1 ;i <length;  i ++){

            int key =  data[i];
            int j =  i - 1;

            while(j >=0 && data[j] > key){
                data[j+1] = data[j];
                j = j-1;
            }

            data[j+1] = key;
        }

        for(int i =0 ; i <data.length; i++){
            System.out.print(data[i] + " ");
        }

    };

    public static void main(String[] args) {

        int data[] = {11,1,20,3,10,2,5,9,4};
        sort(data);
    }
}