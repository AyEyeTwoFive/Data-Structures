// ArrayDeque226.java

package hw4;

import exceptions.EmptyException;

/**
 * An implementation of Deque226 using an Array.
 * @param <T> The type of the queue
 */
public class ArrayDeque226<T> implements Deque226<T> {
    private T[] data;
    private int used;
    /**
     * Constructor to create a new ArrayDeque226.
     */
    public ArrayDeque226() {
        this.data = (T[]) new Object[1];
    }

    @Override
    public boolean empty() {
        return this.used == 0;
    }

    @Override
    public int length() {
        return this.data.length;
    }

    @Override
    public T front() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        else {
            return this.data[0];
        }
    }

    @Override
    public T back() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        else {
            return this.data[this.used - 1];
        }
    }

    @Override
    public void insertFront(T t) {
        if (this.used == this.data.length) { // out of space
            T[] temp = (T[]) new Object[2 * this.used];
            for (int i = 1; i < this.used; i++) {
                temp[i] = this.data[i];
            }
            this.data = temp;
        }
        else {
            T[] temp = (T[]) new Object[1 + this.used];
            for (int i = 1; i < this.used; i++) {
                temp[i] = this.data[i];
            }
            this.data = temp;
        }
        this.data[0] = t;
        this.used += 1;
    }

    @Override
    public void insertBack(T t) {
        if (this.used == this.data.length) { // out of space
            T[] temp = (T[]) new Object[2 * this.used];
            for (int i = 0; i < this.used; i++) {
                temp[i] = this.data[i];
            }
            this.data = temp;
        }
        this.data[used] = t;
        this.used += 1;
    }

    @Override
    public void removeFront() throws EmptyException {
        T[] temp = (T[]) new Object[this.used - 1];
        for (int i = 1; i < this.used; i++) {
            temp[i - 1] = this.data[i];
        }
        this.data = temp;
    }

    @Override
    public void removeBack() throws EmptyException {
        T[] temp = (T[]) new Object[this.used - 1];
        for (int i = 0; i < this.used - 1; i++) {
            temp[i] = this.data[i];
        }
        this.data = temp;
    }

    @Override
    public String toString() {
        String print = "[";
        for (int i = 0; i < this.used; i++) {
            print = print + this.data[i];
            print = print + ", ";
        }
        return print;
    }
}
