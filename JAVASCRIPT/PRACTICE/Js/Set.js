class Set{   
    
    constructor(){
        this.values = [];
    }
}

class SetMath{

    hasItem = (set,item)=>{
        console.log(set.values)
        return set.values.includes(item);
    };

    addItem = (set,item) =>{

        if(this.hasItem(set,item)){
            return console.log(item, " :already added");
        }
        set.values.push(item);
    };

    union = (firstSet, secondSet) =>{
        let unionvalues = [];
        firstSet.values.forEach(element => {
            if(!unionvalues.includes(element)){
                unionvalues.push(element);
            }
        });

        secondSet.values.forEach(element => {
            if(!unionvalues.includes(element)){
                unionvalues.push(element);
            }
        });
        console.log("UNION",unionvalues);
    };

    intersection  = ( firstSet, secondSet) =>{

        let union = [];

        firstSet.values.forEach( e=>{
          if(secondSet.values.includes(e)){
              union.push(e);
          }
        });
        console.log("INTERSECTION", union);
    };

    difference  = ( firstSet, secondSet) =>{

        let difference = [];

        firstSet.values.forEach( e=>{
          if(!secondSet.values.includes(e)){
            difference.push(e);
          }
        });
        console.log("DIFFERENCE", difference);
    };

    subset  = ( firstSet, secondSet) =>{

        let subset = [];

        firstSet.values.every( e=>{
          if(secondSet.values.includes(e)){
            subset.push(e);
          }
        });
        console.log("SUBSET", subset);
    };

    print = (set)=>{
        console.log(set);
    };
}

const setA = new Set();
const setMaths = new SetMath();
setMaths.addItem(setA,"a");
setMaths.addItem(setA,"f");
setMaths.addItem(setA,"b");
setMaths.addItem(setA, "c");

const setB = new Set();
setMaths.addItem(setB,"c");
setMaths.addItem(setB,"f");
setMaths.addItem(setB,"k");

setMaths.union(setA, setB);
setMaths.intersection(setA, setB);
setMaths.difference(setA, setB);
setMaths.subset(setA, setB);

setMaths.print(setA)
setMaths.print(setB)