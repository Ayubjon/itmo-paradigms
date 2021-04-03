package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    /*
     Model:
        [a1, ..., an]
        n -- размер очереди
     Inv:
        n >= 0
        forall i = 1..n: a[i] != null
     */

    private static Object[] a = new Object[2];
    private static int head = 0;
    private static int size = 0;

    /*
        Pred: x != null
        Post: n == n' + 1 && forall i = 1..n': a[i] == a'[i] && a[n] == x
        enqueue(x)
     */
    public static void enqueue(Object x) {
        assert x != null;
        ensureCapacity(size + 1);
        a[getTail(0)] = x;
        size++;
    }

    /*
        Pred: n >= 0
        Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[1]
        element – первый элемент в очереди;
     */
    public static Object element() {
        assert size > 0;
        return a[head];
    }

    /*
        Pred: n > 0
        Post: n == n' - 1 && forall i = 1..n: a[i] == a'[i + 1] && R == a'[1]
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    public static Object dequeue() {
        assert size > 0;
        Object res = element();
        a[head] = null;
        head = (head + 1) % a.length;
        size--;
        return res;
    }

    /*
        Pred: true
        Post: R == n
        size – текущий размер очереди;
     */
    public static int size() {
        return size;
    }

    /*
        Pred: true
        Post: R == (n == 0)
        isEmpty – является ли очередь пустой;
     */
    public static boolean isEmpty() {
        return size == 0;
    }

    /*
        Pred: true
        Post: ArrayQueueModule == new ArrayQueueModule();
        clear – удалить все элементы из очереди.
     */
    public static void clear() {
        size = 0;
        Arrays.fill(a, null);
        head = 0;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity >= a.length) {
            a = Arrays.copyOf(toArray(), capacity * 2);
            head = 0;
        }
    }

    /*
        Pred: x != null
        Post: n == n' + 1 && a[1] = x && forall i = 1..n': a[i + 1] == a'[i]
        enqueue(x)
     */
    public static void push(Object x) {
        ensureCapacity(size + 1);
        if (head == 0) {
            head = a.length;
        }
        size++;
        a[--head] = x;
    }

    /*
        Pred: true
        Post: R = a[n]
     */
    public static Object peek() {
        return a[getTail(-1)];
    }

    /*
        Pred: n > 0
        Post: n == n' - 1 && forall i = 1..n: a[i] == a'[i] && R == a'[n']
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    public static Object remove() {
        Object res = peek();
        a[getTail(-1)] = null;
        size--;
        return res;
    }

    /*
        Pred: true
        Post: R = a
     */
    public static Object[] toArray() {
        Object[] res = new Object[size];
        if (size() > 0 && getTail(0) <= head) {
            System.arraycopy(a, head, res, 0, a.length - head);
            System.arraycopy(a, 0, res, a.length - head, size + head - a.length);
        } else {
            System.arraycopy(a, head, res, 0, size());
        }
        return res;
    }

    private static int getTail(int k) {
        return (head + size + k) % a.length;
    }

    /*
        Pred: true
        Post: R = a.toString
     */
    public static String toStr() {
        return Arrays.toString(toArray());
    }
}
