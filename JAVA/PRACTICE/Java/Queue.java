package Java;

import java.util.ArrayList;
import java.util.List;

class Queue {

    static List<String> queue = new ArrayList<>();

    static int rear = -1;
    static int front = -1;
    static String item;
    static int totalQueueSize = 5;

    public static boolean isEmpty(){
        return rear == -1 && front == -1;
    }

    public static boolean isFull(){
        return rear == totalQueueSize -1;
    }

    public static void enqueue(String item){

        if(isFull()){
            System.out.println("Cannot add '"  + item + "' Queue is full");
            return;
        }else if (isEmpty()){
            rear = front = 0;
            queue.add(rear,item);
        }else{
            rear ++;
            queue.add(rear,item);
        }

    }

    public static String dequeue(){

        if(isEmpty()){
            System.out.println(" Queue is empty");
        }else{
            item = queue.get(front);
            deleteItem(item);           
            front ++;
        }
        return item;
    }

    public static void printQueue(){
        
        for(String i: queue){
           if(i != null){
               System.out.print(i +" ");
           }
        }
    }

    public static void deleteItem(String item){
     rear --;
     queue.remove(item);

    }
    
    public static void main(String[] args) {
        
       enqueue("Fish");
       enqueue("Cake");
       enqueue("Millet");
       enqueue("Goat");
       enqueue("Hen");
       System.out.println("Dequeued item is " + dequeue());
       enqueue("Pig");
       enqueue("Another animal");
      
       printQueue();
    }
}