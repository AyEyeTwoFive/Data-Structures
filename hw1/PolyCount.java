/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * PolyCount.java
 */

package hw1;

/**
 * Basic tests for {@link hw1.ResetableCounter}s. Notes from phf's
 * PolyCount.java for this assignment.
 *
 * This is really just an example of subtype polymorphism. Note how most of
 * the code is written in terms of interfaces, not classes. The only time
 * classes are mentioned is when we have to actually instantiate the
 * counters.
 */
final class PolyCount {

    // For checkstyle to be happy.
    private PolyCount() {}

    // Test any instance of a ResetableCounter
    private static void testAnyCounter(ResetableCounter counter) {
        int x1 = counter.value();
        assert x1 >= 0;

        counter.up();
        int x2 = counter.value();
        assert x2 >= x1;

        // TODO - Make a more comprehensive test of a ResetableCounter
        counter.down();
        counter.down();
        counter.down();
        int x3 = counter.value();
        assert x3 <= x2;
        if (counter instanceof TenCounter) {
            assert x3 >= 1;
        }

        counter.reset();
        int x4 = counter.value();
        if (counter instanceof BasicCounter) {
            assert x4 == 0;
        }
        else if (counter instanceof TenCounter) {
            assert x4 == 1;
        }

    }

    /**
     * Run tests on the counters using Java assertions. This means you must
     * run this with -enableassertions (-ea). Assertion testing will do for
     * now, but soon we'll learn about JUnit which is a much better approach!
     * @param args Ignored.
     */
    public static void main(String[] args) {

        ResetableCounter[] counters = {
            new BasicCounter(),
            new TenCounter(),
            new FlexibleCounter(5, 7),
        };

        for (ResetableCounter counter : counters) {
            testAnyCounter(counter);
        }
    }
}