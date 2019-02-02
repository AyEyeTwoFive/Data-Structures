/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * TenCounter.java
 */

package hw1;

/** A counter for powers of 10. */
public class TenCounter implements ResetableCounter {
    private int val;
    /** Construct a new TenCounter. */
    public TenCounter() {
        this.val = 1;
    }

    @Override
    public void reset() {
        this.val = 1;
    }

    @Override
    public int value() {
        return this.val;
    }

    @Override
    public void up() {
        this.val = this.val * 10;
    }

    @Override
    public void down() {
        this.val = this.val / 10;
        if(this.val < 1) { //round up to nearest integer
            this.val = 1;
        }
    }
}
