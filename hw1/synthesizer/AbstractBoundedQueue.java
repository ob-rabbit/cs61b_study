package synthesizer;

/**
 * @author Bunny
 * @create 2022-01-08 21:10
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {


    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }
}
