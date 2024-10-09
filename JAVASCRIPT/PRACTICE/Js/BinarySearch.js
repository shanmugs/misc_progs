const binarySearch = (nums,target) =>{

    let startIndex = 0;
    let endIndex = nums.length - 1;

    while( startIndex <= endIndex ){

        let middleIndex = Math.floor( (startIndex +endIndex)/2 );

        if(nums[middleIndex] === target){
             return `${target} found at ${middleIndex}`
            }
        
        if(target < nums[middleIndex]){
             endIndex = middleIndex - 1;
        }

        if(target > nums[middleIndex]){
            startIndex = middleIndex + 1;
        }
        
    }

    return `${target} not found`;

};

const nums  = [1,2,3,4,5,6,7,8];
const target = 4;

console.log(binarySearch(nums,target));