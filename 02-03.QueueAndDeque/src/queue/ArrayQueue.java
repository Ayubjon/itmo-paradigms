package queue;

import java.util.Arrays;

public class ArrayQueue {
    /*
     Model:
        [a1, ..., an]
        n -- размер очереди
     Inv:
        n >= 0
        forall i = 1..n: a[i] != null
     */

    private Object[] a = new Object[2];
    private int head = 0;
    private int size = 0;

    /*
        Pred: x != null
        Post: n == n' + 1 && forall i = 1..n': a[i] == a'[i] && a[n] == x
        enqueue(x)
     */
    public void enqueue(ArrayQueue this, Object x) {
        assert x != null;
        ensureCapacity(this.size + 1);
        this.a[getTail(0)] = x;
        this.size++;
    }

    /*
        Pred: n >= 0
        Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[1]
        element – первый элемент в очереди;
     */
    public Object element(ArrayQueue this) {
        assert this.size > 0;
        return this.a[this.head];
    }

    /*
        Pred: n > 0
        Post: n == n' - 1 && forall i = 1..n: a[i] == a'[i + 1] && R == a'[1]
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    public Object dequeue(ArrayQueue this) {
        assert this.size > 0;
        Object res = element();
        this.a[this.head] = null;
        this.head = (this.head + 1) % this.a.length;
        this.size--;
        return res;
    }

    /*
        Pred: true
        Post: R == n && forall i = 1..n:
        size – текущий размер очереди;
     */
    public int size(ArrayQueue this) {
        return this.size;
    }

    /*
        Pred: true
        Post: R == (n == 0)
        isEmpty – является ли очередь пустой;
     */
    public boolean isEmpty(ArrayQueue this) {
        return this.size == 0;
    }

    /*
        Pred: true
        Post: ArrayQueueModule == new ArrayQueueModule();
        clear – удалить все элементы из очереди.
     */
    public void clear(ArrayQueue this) {
        this.size = 0;
        Arrays.fill(this.a, null);
        this.head = 0;
    }

    private void ensureCapacity(ArrayQueue this, int capacity) {
        if (capacity >= this.a.length) {
            this.a = Arrays.copyOf(this.toArray(), capacity * 2);
            this.head = 0;
        }
    }

    /*
        Pred: x != null
        Post: n == n' + 1 && a[1] = x && forall i = 1..n': a[i + 1] == a'[i]
        enqueue(x)
     */
    public void push(ArrayQueue this, Object x) {
        ensureCapacity(this.size + 1);
        if (this.head == 0) {
            this.head = this.a.length;
        }
        this.size++;
        this.a[--this.head] = x;
    }

    /*
        Pred: true
        Post: R = a[n]
     */
    public Object peek(ArrayQueue this) {
        return this.a[getTail(-1)];
    }

    /*
        Pred: n > 0
        Post: n == n' - 1 && forall i = 1..n: a[i] == a'[i] && R == a'[n']
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    public Object remove(ArrayQueue this) {
        Object res = peek();
        this.a[getTail(-1)] = null;
        this.size--;
        return res;
    }

    /*
        Pred: true
        Post: R = a
     */
    public Object[] toArray(ArrayQueue this) {
        Object[] res = new Object[this.size];
        if (size() > 0 && getTail(0) <= this.head) {
            System.arraycopy(this.a, this.head, res, 0, this.a.length - this.head);
            System.arraycopy(this.a, 0, res, this.a.length - this.head, this.size + this.head - this.a.length);
        } else {
            System.arraycopy(this.a, this.head, res, 0, size());
        }
        return res;
    }

    private int getTail(ArrayQueue this, int k) {
        return (this.head + this.size + k) % this.a.length;
    }


    /*
        Pred: true
        Post: R = a.toString
     */
    public String toStr(ArrayQueue this) {
        return Arrays.toString(toArray());
    }
}

