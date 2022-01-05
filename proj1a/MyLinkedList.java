package com.bunny.cs61b_sp18.proj1a;


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

    public MyLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(T e){
        ListNode point = first;
        ListNode newNode = new ListNode<T>(null,e,point);
        first = newNode;
        if (point == null){
            last = newNode;
        }else{
            point.prev = newNode;
        }
        size++;
    }
}
