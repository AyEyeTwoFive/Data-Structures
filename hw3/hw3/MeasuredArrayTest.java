/* MeasuredArrayTest.java
*   Asef Islam
 * aislam5
 * aislam5@jhu.edu
 * */


package hw3;

import org.junit.Before;
import org.junit.Test;
import exceptions.IndexException;
import exceptions.LengthException;

import static org.junit.Assert.assertEquals;

/** Test for Measured Array implementation.
 *
 */

public class MeasuredArrayTest {

    private static final int SIZE = 20;
    private static final String VAL = "test";
    private MeasuredArray<String> array;

    /** Initialize a measured array.
     *
     */
    @Before
    public void createArray() {
        this.array = new MeasuredArray<>(SIZE, VAL);
    }

    /** Test that a newly created array starts with 0 mutations.
     *
     */
    @Test
    public void newArrayZeroMutations() {
        assertEquals(0, array.mutations());
    }

    /** Test that a newly created array starts with 0 accesses.
     *
     */
    @Test
    public void newArrayZeroAccesses() {
        assertEquals(0, array.accesses());
    }

    /** Test that length initialization is correct, and length method works.
     *
     */
    @Test
    public void newArrayLength() {
        assertEquals(20, array.length());
    }

    /** Test that getting the length adds 1 to accesses.
     *
     */
    @Test
    public void getLengthIsAccess() {
        assertEquals(20, array.length());
        assertEquals(1, array.accesses());
    }

    /** Test that get works and adds to accesses.
     *
     */
    @Test
    public void testGet() {
        assertEquals("test", array.get(0));
        assertEquals(1, array.accesses());
        assertEquals("test", array.get(13));
        assertEquals(2, array.accesses());
        assertEquals("test", array.get(19));
        assertEquals(3, array.accesses());
    }

    /** Test that put works and increases mutations.
     *
     */
    @Test
    public void testPut() {
        array.put(0, "changed");
        assertEquals("changed", array.get(0));
        assertEquals(1, array.accesses());
        assertEquals(1, array.mutations());
    }

    /** Test that reset makes both access and mutations 0.
     *
     */
    @Test
    public void testReset() {
        array.put(0, "changed");
        assertEquals("changed", array.get(0));
        assertEquals(20, array.length());
        array.reset();
        assertEquals(0, array.mutations());
        assertEquals(0, array.accesses());
    }

    /** Test that count works (correct count + increase of accesses).
     *
     */
    @Test
    public void testCount() {
        array.put(2, "changed");
        array.put(9, "changed");
        array.put(13, "changed");
        array.put(17, "changed");
        assertEquals(4, array.mutations());
        assertEquals(0, array.accesses());
        assertEquals(4, array.count("changed"));
        assertEquals(20, array.accesses());
    }

    /** Test that illegal length throws exceptions.
     *
     */
    @Test
    public void testLengthException() {
        int lengthExcept = 0;
        try {
            this.array = new MeasuredArray<>(-2, VAL);
        }
        catch (LengthException e) {
            lengthExcept = 1;
        }
        assertEquals(1, lengthExcept);
    }

    /** Test that illegal put index throws exception.
     *
     */
    @Test
    public void testIllegalPutIndex() {
        int indExcept1 = 0;
        int indExcept2 = 0;
        try {
            array.put(30, "change");
        }
        catch (IndexException e) {
            indExcept1 = 1;
        }
        try {
            array.put(-4, "change");
        }
        catch (IndexException e) {
            indExcept2 = 1;
        }
        assertEquals(0, array.mutations());
    }

    /** Test that illegal get index throws exception.
     *
     */
    @Test public void testIllegalGetIndex() {
        int indExcept1 = 0;
        int indExcept2 = 0;
        try {
            array.get(30);
        }
        catch (IndexException e) {
            indExcept1 = 1;
        }
        try {
            array.get(-4);
        }
        catch (IndexException e) {
            indExcept2 = 1;
        }
        assertEquals(0, array.accesses());
    }


    // TODO - add more tests
}
