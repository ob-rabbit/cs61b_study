
/**
 * @author Bunny
 * @create 2022-01-05 21:21
 */
public class MyLinkedList<T> {

    private static class ListNode<T> {

        T item;

        ListNode next;

        ListNode prev;

        ListNode(ListNode prev, T element, ListNode next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private ListNode<T> first;
    private ListNode<T> last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(T e) {
        ListNode point = first;
        ListNode newNode = new ListNode<T>(null, e, point);
        first = newNode;
        if (point == null) {
            last = newNode;
        } else {
            point.prev = newNode;
        }
        size++;
    }

    public void addLast(T e) {
        ListNode point = last;
        ListNode newNode = new ListNode<T>(point, e, null);
        last = newNode;
        if (point == null) {
            first = newNode;
        } else {
            point.next = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (first == null) {
            //list is empty
            return null;
        }
        ListNode<T> temp = first;
        final T val = temp.item;
        ListNode<T> nextNode = temp.next;
        temp.next = null;
        first = nextNode;
        if (nextNode == null) {
            last = null;
        } else {
            nextNode.prev = null;
        }
        size--;
        return val;
    }

    public T removeLast() {
        if (last == null) {
            return null;
        }
        ListNode<T> temp = last;
        final T val = temp.item;
        ListNode<T> prevNode = temp.prev;
        temp.prev = null;
        last = prevNode;
        if (prevNode == null) {
            first = null;
        } else {
            prevNode.next = null;
        }
        size--;
        return val;
    }

    //非递归
    public T get(int index) {
        if (index < 0 || index >= size) {
            //下标越界
            return null;
        }
        ListNode<T> point = first;
        for (int i = 0; i < index; i++) {
            point = point.next;
        }
        return point.item;
    }

    //get by recursive
    public T getRecursive(int index) {
        return getRecursive(first, index);
    }

    private T getRecursive(ListNode<T> list, int index) {
        if (index <= 0) {
            return list.item;
        }
        index--;
        return (T) getRecursive(list.next,index);
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }

        return false;
    }

    public void printList() {
        if (size == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder b = new StringBuilder();
        b.append("[");
        ListNode<T> point = first;
        if (point != null) {
            b.append(point.item);
            point = point.next;
        }
        while (point != null) {
            b.append(",");
            b.append(point.item);
            point = point.next;
        }
        b.append("]");
        System.out.println(b.toString());
    }

    public void deepCopy(MyLinkedList<T> other){
        if (other == null){
            return;
        }
        ListNode<T> point = other.first;
        while (point != null){
            this.addLast(point.item);
            point = point.next;
        }
    }
}
