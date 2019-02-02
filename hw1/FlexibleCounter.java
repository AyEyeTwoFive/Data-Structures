/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * FlexibleCounter.java
 */

package hw1;

/** Class for a counter with flexible starting and incrementing values. */
public class FlexibleCounter implements ResetableCounter {
    private int val; //the current value
    private int init; //the initial value
    private int inc; //the increment
    /**
     * Construct a new FlexibleCounter.
     * @param initialValue The value to start at.
     * @param incrementValue The value to increment the counter by.
     * @throws IllegalArgumentException If incrementValue is negative.
     */
    public FlexibleCounter(int initialValue, int incrementValue) {
        this.init = initialValue;
        this.val = initialValue;
        this.inc = incrementValue;
    }

    @Override
    public void reset() {
        this.val = this.init;
    }

    @Override
    public int value() {
        return this.val;
    }

    @Override
    public void up() {
        this.val = this.val + this.inc;
    }

    @Override
    public void down() {
        this.val = this.val - this.inc;
    }
}
