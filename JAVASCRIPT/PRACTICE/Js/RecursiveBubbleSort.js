class RecursiveBubbleSort{

    sort = (data, length = data.length -1) =>{

        if(length === 1){
            return;
        }
 
        for(let i = 0; i < length; i ++){
            if(data[i] > data[i + 1]){
                //post this on twitter
                // let temp = data[i];
                // data[i] = data[i+1];
                // data[i+1] = temp;
                [ data[i], data[i+1]] = [ data[i+1], data[i]]
            }
        }
        
       this.sort(data, length -1);
       
        return data;
    };

}

const sortClass = new RecursiveBubbleSort();

const data = [ 5,12,10,6,1,9,2];

console.log(sortClass.sort(data));