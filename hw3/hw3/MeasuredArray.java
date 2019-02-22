/* MeasuredArray.java
  Asef Islam
* aislam5
* aislam5@jhu.edu
* */

package hw3;

import exceptions.IndexException;
import hw2.SimpleArray;

/**
 * An Array that is able to report the number of accesses and mutations,
 * as well as reset those statistics.
 * @param <T> The type of the array.
 */
public class MeasuredArray<T> extends SimpleArray<T> implements Measured<T> {

    private int accesses;
    private int mutations;
    /**
     * Constructor for a MeasuredArray that calls the SimpleArray constructor.
     * @param n The size of the array.
     * @param t The initial value to set every object to in the array..
     */

    public MeasuredArray(int n, T t) {
        super(n, t);
        this.accesses = 0;
        this.mutations = 0;
        // TODO
    }

    @Override
    public int length() {
        // TODO
        this.accesses += 1;
        return super.length();
    }

    @Override
    public T get(int i) {
        try {
            T tryget = super.get(i);
        }
        catch (IndexException e) {
            throw new IndexException();
        }
        this.accesses += 1;
        return super.get(i);
    }

    @Override
    public void put(int i, T t) throws IndexException {
        try {
            super.put(i, t);
            this.mutations += 1;
        }
        catch (IndexException e) {
            throw new IndexException();
        }
        // TODO
    }

    @Override
    public void reset() {
        this.mutations = 0;
        this.accesses = 0;
        // TODO
    }

    @Override
    public int accesses() {
        // TODO
        return this.accesses;
    }

    @Override
    public int mutations() {
        // TODO
        return this.mutations;
    }

    @Override
    public int count(T t) {
        // TODO
        int num = 0;
        for (int i = 0; i < super.length(); i++) {
            T cur = this.get(i);
            if (cur == t) {
                num += 1;
            }
        }
        return num;
    }
}
