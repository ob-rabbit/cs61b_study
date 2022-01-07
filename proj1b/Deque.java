package com.bunny.cs61b_sp18.proj1b;

/**
 * @author Bunny
 * @create 2022-01-07 13:20
 */
public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    boolean isEmpty();

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);
}
