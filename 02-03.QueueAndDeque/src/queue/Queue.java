package queue;

public interface Queue {
            /*
             Model:
                [a1, ..., a(size)]
                n -- размер очереди
             Inv:
                size >= 0
                forall i = 1..n: a[i] != null
             */

///---------------------------------------------------------------------------

    /*
        Pred: x != null
        Post: size == size' + 1 && forall i = 1..size': a[i] == a'[i] && a[size] == x
        enqueue(x)
     */
    void enqueue(Object x);

    /*
        Pred: n > 0
        Post: size == size' - 1 && forall i = 1..size: a[i] == a'[i + 1] && R == a'[1]
        dequeue – удалить и вернуть первый элемент в очереди;
     */
    Object dequeue();

    /*
        Pred: true
        Post: size == n && forall i = 1..size:
        size – текущий размер очереди;
     */
    int size();

    /*
        Pred: size >= 0
        Post: size == size' && forall i = 1..size': a[i] == a'[i] && R == a[1]
        element – первый элемент в очереди;
     */
    Object element();

    /*
        Pred: true
        Post: R == (size == 0)
        isEmpty – является ли очередь пустой;
     */
    boolean isEmpty();

    /*
        Pred: true
        Post: ArrayQueueModule == new ArrayQueueModule();
        clear – удалить все элементы из очереди.
     */
    void clear();

    // Pred: n > 0
    // Post: (class) R == (class) queue && R = b[size/n] && forall i = 1..(size/n): b[i] = a[i*n]
    Queue getNth(int n);

    // Pred: n > 0
    // Post: size = size' - size'/n && forall i = 1..size: a[i] = a'[i + i/n]
    void dropNth(int n);

    // Pred: n > 0
    // Post: size = size' - size'/n && forall i = 1..size: a[i] = a'[i + i/n] &&
    // (class) R == (class) queue && R = b[size/n] && forall i = 1..(size/n): b[i] = a[i*n]
    Queue removeNth(int n);
}