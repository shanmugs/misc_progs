class Node{

    constructor(data){
        this.data = data;
        this.next = null;
    }

}

class LinkedList{

    constructor(){
        this.head =  null;  
        this.size =0;      
    }

    insert(data){

        const node = new Node(data);
        let current;

        if(this.head === null){
            this.head = node;
            this.size ++;
        }else{
            current = this.head;

            while(current.next){
                current = current.next
            }
            current.next = node;
            this.size ++;

        }
    }

    print(){
        let current = this.head;
        while(current){
            console.log(current.data)
            current = current.next
        }
    }

}

const ll = new  LinkedList();
ll.insert('1')
ll.insert('2')
ll.insert('3')
ll.insert('4')

console.log(ll.print())

