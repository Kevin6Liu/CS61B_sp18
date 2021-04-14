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
        a = (T[])new Object[8];
    }

    /** Increase the volume of the original array
     *
     * @param capacity : the expanded length of array
     */
    private void resize(int capacity){
        T[] b = (T[])new Object[capacity];
        System.arraycopy(a,nextFirst + 1,b,0,a.length - nextFirst - 1);
        if(nextFirst != 0){
            System.arraycopy(a,0,b,a.length - nextFirst - 1, nextLast);
        }
        nextLast = a.length;
        a = b;
        nextFirst = a.length - 1;

    }

    public void addFirst(int x){
        a[nextFirst] = x;
        size += 1;
        if(nextFirst == 0){
            nextFirst = a.length;
        }
        nextFirst -= 1;
        if(size == a.length){
            resize(size * factor);
        }


    }
    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        a[nextLast] = x;
        size += 1;
        if(nextLast == a.length - 1){
            nextLast = -1;
        }
        nextLast += 1;
        if(size == a.length){
            resize(size * factor);
        }
    }
    public boolean isEmpty(){
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
     * seperated by a space.
     */
    public void printDeque(){
        System.out.print("The items in the deque are: [ ");
        if(nextFirst < nextLast){
            for(int i = nextFirst +1; i < nextLast; i++){
                System.out.print(a[i]+" ");
            }
        }
        else{
            for(int i = nextFirst + 1; i < a.length; i++){
                System.out.print(a[i]+" ");
            }
            for(int j = 0; j < nextLast; j++ ){
                System.out.print(a[j]+" ");
            }
        }

            System.out.print("]");
    }

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, return null
     */
    public T removeFirst(){
        if(nextFirst == a.length - 1){
            nextFirst = -1;
        }
        nextFirst += 1;
        size -= 1;
        return a[nextFirst];

    }
    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if(nextLast == 0){
            nextLast = a.length;
        }
        nextLast -= 1;
        size -= 1;
        return a[nextLast];
    }

    /** Gets the item at the given index, where 0 is the front
     * if no such item exists, return null
     * @param i
     * @return i th item
     */
    public T get(int i) {
        if(i + nextFirst + 1 >= a.length){
            return a[i + nextFirst + 1 - a.length];
        }
        else if(i >= size){
            return null;
        }
        else{
            return a[i + nextFirst + 1];
        }
    }

}
