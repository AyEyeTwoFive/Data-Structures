// ArrayDeque226.java

package hw4;

import exceptions.EmptyException;
import hw2.SimpleArray;

/**
 * An implementation of Deque226 using an Array.
 * @param <T> The type of the queue
 */
public class ArrayDeque226<T> implements Deque226<T> {
    private SimpleArray<T> data;
    private int used;
    /**
     * Constructor to create a new ArrayDeque226.
     */
    public ArrayDeque226() {
        this.data = new SimpleArray<T>(1, null);
    }

    @Override
    public boolean empty() {
        return this.used == 0;
    }

    @Override
    public int length() {
        return this.used;
    }

    @Override
    public T front() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        else {
            return this.data.get(0);
        }
    }

    @Override
    public T back() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        else {
            return this.data.get(this.used - 1);
        }
    }

    @Override
    public void insertFront(T t) {
        if (this.used == this.data.length()) { // out of space
            SimpleArray<T> temp = new SimpleArray<T>(2 * this.used, null);
            for (int i = 1; i <= this.used; i++) {
                temp.put(i, this.data.get(i - 1));
            }
            this.data = temp;
        }
        else {
            SimpleArray<T> temp = new SimpleArray<T>(1 + this.used, null);
            for (int i = 1; i <= this.used; i++) {
                temp.put(i, this.data.get(i - 1));
            }
            this.data = temp;
        }
        this.data.put(0, t);
        this.used += 1;
    }

    @Override
    public void insertBack(T t) {
        if (this.used == this.data.length()) { // out of space
            SimpleArray<T> temp = new SimpleArray<T>(2 * this.used, null);
            for (int i = 0; i < this.used; i++) {
                temp.put(i,this.data.get(i));
            }
            this.data = temp;
        }
        this.data.put(this.used, t);
        this.used += 1;
    }

    @Override
    public void removeFront() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        for (int i = 0; i < this.used - 1; i++) {
            this.data.put(i, this.data.get(i + 1));
        }
        this.used -= 1;
        /*SimpleArray<T> temp = new SimpleArray<T>(this.used - 1, null);
        for (int i = 1; i < this.used; i++) {
            temp.put(i - 1, this.data.get(i));
        }
        this.data = temp; */
    }

    @Override
    public void removeBack() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        this.data.put(this.used - 1, null);
        this.used -= 1;
        /*SimpleArray<T> temp = new SimpleArray<T>(this.used - 1, null);
        for (int i = 0; i < this.used - 1; i++) {
            temp.put(i,this.data.get(i));
        }
        this.data = temp; */
    }

    @Override
    public String toString() {
        String print = "[";
        for (int i = 0; i < this.used; i++) {
            print = print + this.data.get(i);
            print = print + ", ";
        }
        return print;
    }
}
