/**
 * @author Bunny
 * @create 2022-01-05 20:59
 */
public class ArrayDeque<T> {

    private static final int DEFAULT_CAPACITY = 8;

    private Object[] elements;

    private int head;

    private int tail;

    private int size;


    public ArrayDeque() {
        elements = new Object[DEFAULT_CAPACITY];
        head = tail = 0;
        size = 0;
    }

    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    public void addFirst(T item) {
        elements[head = (head - 1) & (elements.length - 1)] = item;
        size++;
        if (head == tail) {
            doubleCapacity();
        }
    }

    public void addLast(T item) {
        elements[tail] = item;
        size++;
        if ((tail = (tail + 1) & (elements.length - 1)) == head) {
            doubleCapacity();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("[]");
            return;
        }
        StringBuilder s = new StringBuilder();
        s.append("[");
        int point = head;
        s.append(elements[point]);
        point = (point + 1) % elements.length;
        while (point != tail) {
            s.append(",");
            s.append(elements[point]);
            point = (point + 1) % elements.length;
        }
        s.append("]");
        System.out.println(s.toString());
    }

    public T removeFirst() {
        int h = head;
        T result = (T) elements[h];
        if (result == null) {
            return null;
        }
        elements[h] = null;
        head = (h + 1) & (elements.length - 1);
        size--;
        if (size * 1.0 / elements.length < 0.25 && elements.length >= 16) {
            reduceCapacity();
        }
        return result;
    }

    public T removeLast() {
        int t = (tail - 1) & (elements.length - 1);
        T result = (T) elements[t];
        if (result == null) {
            return null;
        }
        elements[t] = null;
        tail = t;
        size--;
        if (size * 1.0 / elements.length < 0.25 && elements.length >= 16) {
            reduceCapacity();
        }
        return result;
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return (T) elements[(head + index) % elements.length];
    }

    private void doubleCapacity() {
        int p = head;
        int n = elements.length;
        int newCapacity = n << 1;
        Object[] a = new Object[newCapacity];
        int index = 0;
        while (index < n) {
            a[index++] = elements[p];
            p = (p + 1) % n;
        }
        elements = a;
        head = 0;
        tail = n;
    }

    private void reduceCapacity() {
        int p = head;
        int n = elements.length;
        int newCapacity = n >> 1;
        Object[] a = new Object[newCapacity];
        int index = 0;
        while (p != tail) {
            a[index++] = elements[p];
            p = (p + 1) % n;
        }
        elements = a;
        head = 0;
        tail = index;
    }
}
