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
    private int front;
    private int back;

    /**
     * Constructor to create a new ArrayDeque226.
     */
    public ArrayDeque226() {
        this.data = new SimpleArray<T>(1, null);
    }


    /** Method to double the array.
     *
     * @return
     */
    public void doubleArray() {
        int prevLen = this.data.length();
        SimpleArray<T> temp = new SimpleArray<T>(2 * this.used, null);
        for (int i = front; i < prevLen; i++) {
            temp.put(i - front, this.data.get(i));
        }
        for (int i = 0; i < back; i++) {
            temp.put(prevLen + i, this.data.get(i));
        }
        this.data = temp;
        this.front = 0;
        this.back = prevLen - 1;
    }

    /** move all elements to the right to create space to insert in front.
     *
     * @return
     */
    public void shiftRight() {
        for (int i = this.used; i > 0; i--) {
            this.data.put(i, this.data.get(i - 1));
        }
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
            return this.data.get(front);
        }
    }

    @Override
    public T back() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        else {
            return this.data.get(back);
        }
    }

    @Override
    public void insertFront(T t) {
        if (this.used == this.data.length()) { // out of space
            doubleArray();
            shiftRight();
            this.data.put(front, t);
            this.used += 1;
            this.back += 1;
        }
        else if (front == 0) { // space in the back
            shiftRight();
            this.data.put(front, t);
            if (this.used == 0) { // first time add
                this.used += 1;
            }
            else {
                this.used += 1;
                this.back += 1;
            }
            //this.front -= 1;
        }
        else { // space in the front
            this.data.put(front - 1, t);
            this.used += 1;
            this.front -= 1;
        }
    }

    @Override
    public void insertBack(T t) {
        if (this.used == this.data.length()) { // out of space in back
            doubleArray();
            this.data.put(back + 1, t);
            this.used += 1;
            this.back += 1;
        }
        else if (front == 0) { // just add it to the back
            if (this.used == 0) { // empty array
                this.data.put(back, t);
                this.used += 1;
            }
            else {
                this.data.put(back + 1, t);
                this.used += 1;
                back += 1;
            }
        }
        else { // open space in front, apply circular array
            this.data.put(front - 1, t);
            back = front - 1;
            this.used += 1;
        }
    }

    @Override
    public void removeFront() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        this.data.put(front, null);
        this.front += 1;
        this.used -= 1;
    }

    @Override
    public void removeBack() throws EmptyException {
        if (this.used == 0) {
            throw new EmptyException();
        }
        this.data.put(back, null);
        this.back -= 1;
        this.used -= 1;
    }

    @Override
    public String toString() {
        String print = "[";
        int cap = this.data.length();
        int rem = this.used;
        for (int i = front; i < cap; i++) {
            if (rem == 0) {
                break;
            }
            if (this.data.get(i) != null) {
                print = print + this.data.get(i);
                print = print + ", ";
                rem -= 1;
            }
        }
        for (int i = 0; i <= back; i++) {
            if (rem == 0) {
                break;
            }
            if (this.data.get(i) != null) {
                print = print + this.data.get(i);
                print = print + ", ";
                rem -= 1;
            }
        }
        if (print.length() > 2) {
            print = print.substring(0, print.length() - 2); // del. last comma
        }
        print += "]";
        return print;
    }
}
