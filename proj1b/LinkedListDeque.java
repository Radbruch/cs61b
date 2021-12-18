
public class LinkedListDeque<T> implements Deque<T> {

    private class StuffNode{
        private T item;
        private StuffNode prev;
        private StuffNode next;



        private StuffNode(StuffNode prevnode, T itemnode, StuffNode nextnode){
            item = itemnode;
            prev = prevnode;
            next = nextnode;
        }
    }

    private StuffNode sentinel;
    private int size;




    public LinkedListDeque(){
        // Creates an empty linked list deque.
        sentinel = new StuffNode(sentinel, null, sentinel);
        size = 0;
    }

    @Override
    public void addFirst(T x){
        // Adds an item of type T to the front of the deque.
        StuffNode newnode = new StuffNode(sentinel, x, sentinel.next);
        if (size == 0){
            sentinel.prev = newnode;
            sentinel.next = newnode;
            newnode.next = sentinel;
        } else {
            sentinel.next.prev = newnode;
            sentinel.next = newnode;
        }
        size++;

    }

    @Override
    public void addLast(T x){
        // Adds an item of type T to the back of the deque.
        StuffNode newnode = new StuffNode(sentinel.prev, x, sentinel);
        if (size == 0){
            sentinel.prev = newnode;
            sentinel.next = newnode;
            newnode.prev = sentinel;
        } else {
            sentinel.prev.next = newnode;
            sentinel.prev = newnode;
        }
        size++;
    }


    //public boolean isEmpty(){
        // Returns true if deque is empty, false otherwise.
    //    return size == 0;
    //}

    @Override
    public int size(){
        // Returns the number of items in the deque.
        return size;
    }

    @Override
    public void printDeque(){
        /* Prints the items in the deque from first to last, separated by a space.
        Once all the items have been printed, print out a new line.
         */
        if (size == 0) return;
        StuffNode pointer = sentinel;
        for (int i = 1; i <= size; i++) {
            T itemofnode = pointer.next.item;
            pointer = pointer.next;
            System.out.println(itemofnode);
        }
        return;
    }

    @Override
    public T removeFirst(){
        // Removes and returns the item at the front of the deque. If no such item exists, returns null.
        if (size == 0) return null;
        else{
            T firstitem = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return firstitem;
        }

    }

    @Override
    public T removeLast(){
        // Removes and returns the item at the back of the deque. If no such item exists, returns null.
        if (size == 0) return null;
        else {
            T lastitem = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return lastitem;
        }
    }

    @Override
    public T get(int index){
        /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
        If no such item exists, returns null. Must not alter the deque!
         */
        if (index+1 > size) return null;
        else{
            StuffNode goalNode = sentinel.next;
            for (int pointer = 0; pointer < index; pointer++){
                goalNode = goalNode.next;
            }
            T itemGet = goalNode.item;
            return itemGet;
        }
    }


    private LinkedListDeque(LinkedListDeque other){
        sentinel = new StuffNode(sentinel, null, sentinel);
        size = 0;

        for (int i = 0; i < other.size(); i++){
            addLast((T)other.get(i));
        }
    }

    private StuffNode recursivelink;
    private void InitializeRecursivelink(){
        recursivelink = sentinel.next;
    }

    public T getRecursive(int index) {
        // Same as get, but uses recursion.
        if (index >= size()) return null;
        else {
            InitializeRecursivelink();
            return Recursivebody(index);
        }
    }

    private T Recursivebody(int index){
        if (index == 0) {
            T indexitem = recursivelink.item;
            return indexitem;
        }
        else {
            recursivelink = recursivelink.next;
            return Recursivebody(index-1);
        }
    }

    public static void main(String[] args){
        LinkedListDeque<String> x = new LinkedListDeque<>();
        x.addFirst("Rad");
        x.addFirst("Cute");
        x.addLast("is");
        x.addLast("writing");
        System.out.println(x.get(2));
    }
}
