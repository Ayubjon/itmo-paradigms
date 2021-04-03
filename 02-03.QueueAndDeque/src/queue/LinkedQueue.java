package queue;

public class LinkedQueue extends AbstractQueue {
    private static class Node {
        private final Object value;
        private Node next;
        private Node prev;

        public Node(Object value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public Node head = null;

    protected void enqueueImpl(Object x) {
        if (size == 0) {
            Node cur = new Node(x, null, null);
            cur.prev = cur;
            cur.next = cur;
            head = cur;
        } else {
            head.prev.next = new Node(x, head.prev, head);
            head.prev = head.prev.next;
        }
    }

    protected void dequeueImpl() {
        if (size == 1) {
            head = null;
        } else {
            head.prev.next = head.next;
            head.next.prev = head.prev;
            head = head.next;
        }
    }

    public Object element() {
        return head.value;
    }

    protected Queue getOwnClass() {
        return new LinkedQueue();
    }
}

