public class ArrayDeque<T> {

    private int nextfirst;
    private int nextlast;
    private T[] arraydeque;
    private int size;

    public ArrayDeque(){
        arraydeque = (T[]) new Object[8];
        nextfirst = 3;
        nextlast = 4;
        size = 0;
    }
    private int getnextfirst(int first){
        if (first == 0) return arraydeque.length-1;
        else return first-1;
    }

    private int getnextlast(int last){
        if (last == arraydeque.length-1) return 0;
        else return last+1;
    }

    public void addFirst(T item){
        if (nextfirst == nextlast){
            resize(arraydeque.length*2);
        }
        arraydeque[nextfirst] = item;
        nextfirst = getnextfirst(nextfirst);
        size++;
    }

    public void addLast(T item){
        if (nextfirst == nextlast){
            resize(arraydeque.length*2);
        }
        arraydeque[nextlast] = item;
        nextlast = getnextlast(nextlast);
        size++;
    }


    //public void addLast(T item){}
    private void resize(int newsize){
        T[] resizearray = (T[]) new Object[newsize];
        if(nextfirst < nextlast) {
            System.arraycopy(arraydeque,nextfirst+1,resizearray,0,size);
        }
        else{
            System.arraycopy(arraydeque,nextfirst+1,resizearray,0,(arraydeque.length-nextfirst-1));
            System.arraycopy(arraydeque,0,resizearray,arraydeque.length-nextfirst-1,nextlast);
        }
        nextfirst = newsize-1;
        nextlast = size;
        arraydeque = resizearray;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    private T[] copyArray(){
        T[] copyarray = (T[]) new Object[size];
        if(nextfirst < nextlast) {
            System.arraycopy(arraydeque,nextfirst+1,copyarray,0,arraydeque.length);
        }
        else{
            System.arraycopy(arraydeque,nextfirst+1,copyarray,0,(arraydeque.length-nextfirst-1));
            System.arraycopy(arraydeque,0,copyarray,arraydeque.length-nextfirst-1,nextlast);
        }
        return copyarray;
    }
    public void printDeque(){
        T[] copyarray = copyArray();
            for (int i = 0; i < size; i++){
                System.out.print(copyarray[i] + " ");
            }
    }

    public T removeFirst(){
        if (size == 0) throw new ArrayIndexOutOfBoundsException("array is empty");
        else {
            T oldfirst = arraydeque[getnextlast(nextfirst)];
            arraydeque[getnextlast(nextfirst)] = null;
            nextfirst = getnextlast(nextfirst);
            size--;
            if (size <= arraydeque.length/4) {
                resize(size*2);
            }
            return oldfirst;
        }
    }
    public T removeLast() {
        if (size == 0) throw new ArrayIndexOutOfBoundsException("array is empty");
        else {
            T oldfirst = arraydeque[getnextfirst(nextlast)];
            arraydeque[getnextfirst(nextlast)] = null;
            nextlast = getnextfirst(nextlast);
            size--;
            if (size <= arraydeque.length / 4) {
                resize(size * 2);
            }
            return oldfirst;
        }
    }
    public T get(int index){
        T[] copyarray = copyArray();
        return copyarray[index];
    }
    //public LinkedListDeque(LinkedListDeque other){}

}