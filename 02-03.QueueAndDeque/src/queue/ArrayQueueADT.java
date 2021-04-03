package queue;

import java.util.Arrays;

public class ArrayQueueADT {
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
        Pred: queue != null && x != null
        Post: n == n' + 1 && forall i = 1..n': a[i] == a'[i] && a[n] == x
        enqueue(x)
     */
    public static void enqueue(ArrayQueueADT queue, Object x) {
        assert queue != null && x != null;
        ensureCapacity(queue, queue.size + 1);
        queue.a[getTail(queue, 0)] = x;
        queue.size++;
    }

    /*
        Pred: queue != null && n >= 0
        Post: n == n' && forall i = 1..n': a[i] == a'[i] && R == a[1]
        element – первый элемент в очереди;
     */
    public static Object element(ArrayQueueADT queue) {
        assert queue != null && queue.size > 0;
        return queue.a[queue.head];
    }

    /*
        Pred: queue != null && n > 0
        Post: n == n' - 1 && forall i = 1..n: a[i] == a'[i + 1] && R == a'[1]
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null && queue.size > 0;
        Object res = element(queue);
        queue.a[queue.head] = null;
        queue.head = (queue.head + 1) % queue.a.length;
        queue.size--;
        return res;
    }

    /*
        Pred: queue != null
        Post: R == n && forall i = 1..n:
        size – текущий размер очереди;
     */
    public static int size(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size;
    }

    /*
        Pred: queue != null
        Post: R == (n == 0)
        isEmpty – является ли очередь пустой;
     */
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size == 0;
    }

    /*
        Pred: queue != null
        Post: ArrayQueueModule == new ArrayQueueModule();
        clear – удалить все элементы из очереди.
     */
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;
        queue.size = 0;
        Arrays.fill(queue.a, null);
        queue.head = 0;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity >= queue.a.length) {
            queue.a = Arrays.copyOf(toArray(queue), capacity * 2);
            queue.head = 0;
        }
    }

    /*
        Pred: queue != null && x != null
        Post: n == n' + 1 && a[1] = x && forall i = 1..n': a[i + 1] == a'[i]
        enqueue(x)
     */
    public static void push(ArrayQueueADT queue, Object x) {
        assert queue != null;
        ensureCapacity(queue, queue.size + 1);
        if (queue.head == 0) {
            queue.head = queue.a.length;
        }
        queue.size++;
        queue.a[--queue.head] = x;
    }

    /*
        Pred: queue != null
        Post: R = a[n]
     */
    public static Object peek(ArrayQueueADT queue) {
        assert queue != null;
        return queue.a[getTail(queue, -1)];
    }


    /*
        Pred: queue != null && n > 0
        Post: n == n' - 1 && forall i = 1..n: a[i] == a'[i] && R == a'[n']
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    public static Object remove(ArrayQueueADT queue) {
        assert queue != null;
        Object res = peek(queue);
        queue.a[getTail(queue, -1)] = null;
        queue.size--;
        return res;
    }

    /*
        Pred: queue != null
        Post: R = a
     */
    public static Object[] toArray(ArrayQueueADT queue) {
        assert queue != null;
        Object[] res = new Object[queue.size];
        if (size(queue) > 0 && getTail(queue, 0) <= queue.head) {
            System.arraycopy(queue.a, queue.head, res, 0, queue.a.length - queue.head);
            System.arraycopy(queue.a, 0, res, queue.a.length - queue.head, queue.size + queue.head - queue.a.length);
        } else {
            System.arraycopy(queue.a, queue.head, res, 0, size(queue));
        }
        return res;
    }

    private static int getTail(ArrayQueueADT queue, int k) {
        return (queue.head + queue.size + k) % queue.a.length;
    }

    /*
        Pred: queue != null
        Post: R = a.toString
     */
    public static String toStr(ArrayQueueADT queue) {
        assert queue != null;
        return Arrays.toString(toArray(queue));
    }
}

