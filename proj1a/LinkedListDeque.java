
/**
 * @author Bunny
 * @create 2022-01-05 21:03
 */
public class LinkedListDeque<T> {

    private MyLinkedList<T> myLinkedList;


    public LinkedListDeque(){
        myLinkedList = new MyLinkedList<T>();
    }

    //deep copy
    public LinkedListDeque(LinkedListDeque<T> other){
        myLinkedList = new MyLinkedList<>();
        myLinkedList.deepCopy(other.myLinkedList);
    }

    public void addFirst(T item){
        myLinkedList.addFirst(item);
    }

    public void addLast(T item){
        myLinkedList.addLast(item);
    }

    public boolean isEmpty(){
        return myLinkedList.isEmpty();
    }

    public int size(){
        return myLinkedList.size();
    }

    public void printDeque(){
        myLinkedList.printList();
    }

    public T removeFirst(){
        return myLinkedList.removeFirst();
    }

    public T removeLast(){
        return myLinkedList.removeLast();
    }

    public T get(int index){
        return myLinkedList.get(index);
    }

    public T getRecursive(int index){
        return  myLinkedList.getRecursive(index);
    }
}
