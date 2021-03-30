public class LinkedListDeque<T> {
    public TList sentinel;
    int size;

    /**
     * Nested Class
     */
    public class TList {
        public TList prev;
        public T item;
        public TList next;

        public TList(TList p, T i, TList n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /**
     * Constructor
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TList(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(T first) {
        sentinel = new TList(null, null, null);
        TList next = new TList(sentinel, first, sentinel);
        sentinel.next = next;
        sentinel.prev = next;
        size += 1;
    }

    /**
     * Adds an item of type T to the front of the deque
     */
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new TList(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return removedItem;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    // review on #improvement 6b
    public void addLast(T item) {
        size += 1;
        sentinel.prev = new TList(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    /**
     * Returns true if deque is empty, false otherwise
     */

    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of items in the deque
     */

    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space.
     */

    public void printDeque() {
        TList cur = sentinel.next;
        int i = size;
        while (i != 0) {
            System.out.print(cur.item + " ");
            cur = cur.next;
            i -= 1;
        }
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return removedItem;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */

    public T get(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        TList cur = sentinel.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    /**
     * Same as get(), but uses recursion.
     */
    public T getRecursive(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(TList cur, int ind) {
        if (ind == 0) {
            return cur.item;
        }
        return getRecursiveHelper(cur.next, ind - 1);
    }



/*
    public static void main(String[] args){
        LinkedListDeque<Integer> t = new LinkedListDeque<>();
        LinkedListDeque<String> test = new LinkedListDeque<>("Plus");
        //test.removeFirst();
        test.addLast("Dog");
        System.out.println(test.isEmpty());
        t.addFirst(10);
        t.addFirst(20);
       // t.removeFirst();
        t.addLast(30);
        System.out.println(t.getRecursive(1));
        t.printDeque();
        System.out.println();
        test.printDeque();
       // t.removeLast();
    }

*/
}