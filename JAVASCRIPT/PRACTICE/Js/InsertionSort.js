class InsertionSort{

    constructor(data){
        this.data = data;
    }

    sort =  () =>{
        let length = this.data.length;

        for( let i = 1; i <length; i++){

            let key =  this.data[i];
            let j = i - 1;

            while(j >= 0 && this.data[j] > key){
                this.data[j+1] = this.data[j];
                j = j-1;
            }
            this.data[j+1] = key;
        }
        return this.data;
    }
}

const insertionSort = new InsertionSort([11,1,20,3,10,2,5,9,4]);
console.log(insertionSort.sort());