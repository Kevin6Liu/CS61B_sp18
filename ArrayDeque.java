/** Array based List */
/* Invariants :
1. The last item is always a[nextLast - 1]
2. The first item is always a[nextFirst + 1]
 */
public class ArrayDeque<T> {
    private T[] a;
    private int size = 0;
    private int nextFirst = 4;
    private int nextLast = 5;
    private int factor = 2;
    /** Creates an empty list. */
    public ArrayDeque() {
        a = (T[]) new Object[8];
    }

    /** Move the indexes forward/backward
     * @source Got inspired from 
     * https://github.com/yiyikkk/cs61b-spring18/blob/master/proj1a */
    private int addOne(int x) {
        return (x + 1) % a.length;
    }
    private int subOne(int x) {
        return (x - 1 + a.length) % a.length;
    }

    /** Increase the volume of the original array
     *
     * @param capacity : the expanded length of array
     */
    private void resize(int capacity) {
        T[] b = (T[]) new Object[capacity];
        System.arraycopy(a, nextFirst + 1, b, 0, a.length - nextFirst - 1);
        if(nextFirst != 0){
            System.arraycopy(a, 0, b, a.length - nextFirst - 1, nextLast);
        }
        nextLast = a.length;
        a = b;
        nextFirst = a.length - 1;

    }

    public void addFirst(T x) {
        if(size == a.length){
            resize(size * factor);
        }
        a[nextFirst] = x;
        size += 1;
        nextFirst = subOne(nextFirst);
    }
    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if(size == a.length){
            resize(size * factor);
        }
        a[nextLast] = x;
        size += 1;
        nextLast = addOne(nextLast);
    }
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the item from the back of the list. */
    public T getLast() {

        return a[nextLast - 1];
    }


    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last
     * separated by a space.w
     */
    public void printDeque() {
        System.out.print("The items in the deque are: [ ");
        int i = addOne(nextFirst);
        for(int j = 0; j < size; j++){  // use j to count the number of printed items
                System.out.print(a[i]+" ");
                i = addOne(i); // only addOne(i) won't change the value of i
        }
        System.out.print("]");
    }





    /** Removes and returns the item at the front of the deque.
     * if no such item exists, return null
     */
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        nextFirst = addOne(nextFirst);
        size -= 1;
        if(4 * size < a.length){
            resize(a.length/factor);
        }
        return a[nextFirst];


    }
    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if(size == 0){
            return null;
        }
        nextLast = subOne(nextLast);
        size -= 1;
        if(4 * size < a.length){
            resize(a.length/factor);
        }
        return a[nextLast];
    }

    /** Gets the item at the given index, where 0 is the front
     * if no such item exists, return null
     * @param i
     * @return i th item
     */
    public T get(int i) {
        if(i >= size){
            return null;
        }
       return a[(i + addOne(nextFirst)) % a.length];
    }


}
