package lab9;

import edu.princeton.cs.algs4.BST;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;
        private int size;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v, int size) {
            key = k;
            value = v;
            this.size = size;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return getHelper(key, x.left);
        else if (cmp > 0) return getHelper(key, x.right);
        else {return x.value;}
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Can not find null in BSTMap");
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node x) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {x.left = putHelper(key, value, x.left);}
        else if (cmp > 0) {x.right = putHelper(key, value, x.right);}
        else {x.value = value;}

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Can not put null into BSTMap");
        if (value == null) {
            remove(key);
            return;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x==null) return 0;
        else return x.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        if (root == null) return null;
        else {
            Set keyset = new HashSet();
            keySet(root, keyset);
            return keyset;
        }
    }

    private void keySet(Node x, Set set) {
        if (x == null) return;
        else {
            set.add(x.key);
            keySet(x.left, set);
            keySet(x.right, set);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Can not remove null");
        root = removeHelper(root, key);
        if (removeItem == null) return null;
        else {
            V removeValue = removeItem.value;
            removeItem = null;
            return removeValue;
        }
    }

    private Node removeItem;

    private Node removeHelper(Node x, K key) {
        if (x == null) {
            removeItem = null;
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = removeHelper(x.left, key);
        else if (cmp > 0) x.right = removeHelper(x.right, key);
        else {
            if (x.right == null) {
                removeItem = x;
                return x.left;
            }
            if (x.left == null) {
                removeItem = x;
                return x.right;
            }
            removeItem = x;
            x = min(removeItem.right);
            x.right = removeMin(removeItem.right);
            x.left = removeItem.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node removeMin(Node x) {
        if (x.left == null) return x.right;
        x.left = removeMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Can not remove null");
        if (value == null) throw new IllegalArgumentException("All value in BSTMap is not null");
        root = removeHelper(key, value, root);
        if (removeItem == null) return null;
        else {
            V removeValue = removeItem.value;
            removeItem = null;
            return removeValue;
        }
    }

    private Node removeHelper(K key, V value, Node x) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = removeHelper(key, value, x.left);
        else if (cmp > 0) x.right = removeHelper(key, value, x.right);
        else {
            if (!x.value.equals(value)) return x;
            if (x.right == null) {
                removeItem = x;
                return x.left;
            }
            if (x.left == null) {
                removeItem = x;
                return x.right;
            }
            removeItem = x;
            x = min(removeItem.right);
            x.right = removeMin(removeItem.right);
            x.left = removeItem.left;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args){
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);

        Set x = bstmap.keySet();
        System.out.println(x.size());
        Iterator Itr = x.iterator();
        while (Itr.hasNext()) {
            System.out.println(Itr.next());
        }

    }
}

