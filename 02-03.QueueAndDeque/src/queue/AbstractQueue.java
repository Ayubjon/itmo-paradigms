package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    public void enqueue(Object x) {
        Objects.requireNonNull(x);
        enqueueImpl(x);
        size++;
    }

    public Object dequeue() {
        assert size > 0;
        Object res = element();
        dequeueImpl();
        size--;
        return res;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        extendedNth(1, true);
    }

    protected abstract void enqueueImpl(Object x);

    protected abstract void dequeueImpl();

    public abstract Object element();

    public Queue getNth(int n) {
        return extendedNth(n, false);
    }

    public void dropNth(int n) {
        extendedNth(n, true);
    }

    public Queue removeNth(int n) {
        return extendedNth(n, true);
    }

    private Queue extendedNth(int n, boolean delete) {
        assert n > 0;
        int curSize = size();
        Queue retQueue = getOwnClass();
        for (int i = 0; i < curSize; ++i) {
            Object obj = dequeue();
            if ((i + 1) % n == 0) {
                retQueue.enqueue(obj);
            }
            if ((i + 1) % n != 0 || !delete) {
                enqueue(obj);
            }
        }
        return retQueue;
    }

    protected abstract Queue getOwnClass();
}
