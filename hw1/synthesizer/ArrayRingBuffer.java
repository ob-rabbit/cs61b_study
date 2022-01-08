package synthesizer;

import java.util.*;
import java.util.function.Consumer;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    private int first;

    private int last;

    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        this.capacity = capacity;
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (!isFull()) {
            rb[last] = x;
            last = (last + 1) % capacity;
            fillCount++;
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        T res = rb[first];
        if (!isEmpty()) {
            first = (first + 1) % capacity;
            fillCount--;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        private int cursor = first;

        private int fence = last;


        @Override
        public boolean hasNext() {
            return cursor != fence;
        }

        @Override
        public T next() {
            T result = (T) rb[cursor];
            cursor = (cursor + 1) % capacity;
            return result;
        }
    }
}
