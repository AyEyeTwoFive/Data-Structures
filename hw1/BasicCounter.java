/* TODO - Add your name, JHED, and email.
 * BasicCounter.java
 */

package hw1;

/** A counter that increments and decrements by 1. */
public class BasicCounter implements ResetableCounter {
    private int val; //value

    /** Construct a new BasicCounter. */
    public BasicCounter() {
        // TODO
        this.val = 0; //initialize value to 0 by default
    }

    @Override
    public void reset() {
        // TODO
        this.val = 0; //reset value to 0
    }

    @Override
    public int value() {
        // TODO
        return this.val; //return the current value
    }

    @Override
    public void up() {
        // TODO
        this.val = this.val + 1; //increment by one
    }

    @Override
    public void down() {
        // TODO
        this.val = this.val - 1; //decrement by 1
    }
}
