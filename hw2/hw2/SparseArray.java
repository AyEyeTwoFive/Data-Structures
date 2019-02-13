/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * SparseArray.java
 */

package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;


/**
 * TODO - Write a good Javadoc description for potential clients.
 * Array implementation that keeps track of only stored values that have
 *  changed from the default value.
 * Useful (more memory-efficient) in the case that your array
 *  will store a large amount of elements that have
 *  the same (default) value, and only few that are non-default.
 * If your array has mostly unique values, it is not much of an
 *  advantage to use sparse array over simple array.
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

    private static class Node<T> {
        T data;
        int position;
        Node<T> next;
    }

    // TODO - You'll need this to implement the iterator() method
    private class SparseArrayIterator implements Iterator<T> {
        private Node<T> current; //current position
        private Node<T> defnode; //node for default values

        SparseArrayIterator() {
            defnode = new Node<T>();
            this.defnode.data = SparseArray.this.defval;
            Node<T> n = SparseArray.this.list;
            while (n != null) {
                //look through to see if the 1st element is non-default
                if (n.position == 0) {
                    this.current = n; //start the iterator here
                    return;
                }
                n = n.next;
            }
            this.current = defnode; //else, iterator starts at default node
            defnode.position = 0;
        }

        @Override
        public boolean hasNext() {
            // TODO
            return this.current.position < SparseArray.this.len;
            //there's a next element
        }

        @Override
        public T next() {
            int nextind = this.current.position + 1;
            //what the next index should be
            Node<T> n = SparseArray.this.list;
            while (n != null) {
                //look if the next index has a non-default value
                if (n.position == nextind) {
                    T temp = this.current.data;
                    this.current = n;
                    //this is where the iterator is now
                    return temp;
                }
                n = n.next;
            }
            T temp = this.current.data;
            this.current = defnode; //else, it's at the default node
            defnode.position = nextind;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * An array that is meant to be filled primarily with a default value
     * that is not going to change - with the benefit of that default
     * value not being stored numerous times as opposed to once.
     * @param length The number of indexes the array should have.
     * @param defaultValue The default value for the array.
    */

    private Node<T> list;
    private int len;
    private T defval;

    /** Constructor of a Sparse Array.
     * @param length the number of indexes the array should have
     * @param defaultValue the default value for the array
     * @throws LengthException if length is not > 0
     */
    public SparseArray(int length, T defaultValue) throws LengthException {
        // TODO
        if (length <= 0) {
            throw new LengthException();
        }
        this.len = length;
        defval = defaultValue;
        this.list = null;
    }

    @Override
    public int length() {
        // TODO
        return this.len;
    }

    @Override
    public T get(int i) throws IndexException {
        // TODO
        if (i >= this.len || i < 0) { //out of bounds
            throw new IndexException();
        }
        Node<T> n = this.list;
        while (n != null) {
            if (n.position == i) {
                return n.data;
            }
            n = n.next;
        }
        return this.defval;
    }

    @Override
    public void put(int i, T t) throws IndexException {
        if (i >= this.len || i < 0) { //out of bounds
            throw new IndexException();
        }
        if (t == this.defval) { //default value
            return; //do nothing
        }
        Node<T> n = this.list;
        Node<T> thisnode = new Node<T>();
        if (n == null) { //empty
            Node<T>  firstnode = new Node<T>();
            firstnode.data = t;
            firstnode.position = i;
            firstnode.next = null;
            this.list = firstnode;
            return;
        }
        while (n != null) {
            if (n.position == i) {
                n.data = t;
                return;
            }
            thisnode = n;
            n = n.next;
        }
        n = new Node<T>();
        n.data = t;
        n.position = i;
        n.next = null;
        thisnode.next = n;
        return;
        // TODO
    }

    @Override
    public Iterator<T> iterator() {
        // TODO
        return new SparseArrayIterator();
    }
}
