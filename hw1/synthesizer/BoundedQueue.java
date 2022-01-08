package synthesizer;

/**
 * @author Bunny
 * @create 2022-01-08 21:06
 */
public interface BoundedQueue<T> extends Iterable<T> {

    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();

    default boolean isEmpty() {
        return false;
    }

    default boolean isFull(){
        return false;
    }
}