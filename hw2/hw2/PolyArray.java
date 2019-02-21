/* TODO - Add your name, JHED, and email.
    Asef Islam
    aislam5
    aislam5@jhu.edu
 * PolyArray.java
 */

package hw2;

import exceptions.LengthException;
import exceptions.IndexException;
import java.util.ArrayList; // see note in main() below
import java.util.Iterator;


/**
 * Simple polymorphic test framework for arrays.
 * See last week's PolyCount. You need to add more test cases (meaning more
 * methods like testNewLength and testNewWrongLength below) to make sure all
 * preconditions and axioms are indeed as expected from the specification.
*/
public final class PolyArray {
    private static final int LENGTH = 113;
    private static final int INITIAL = 7;
    private static final int ITERL = 20;
    private static final int ITERI = 5;

    private PolyArray() {}

    private static void testNewLength(Array<Integer> a) {
        assert a.length() == LENGTH;
    }

    // TODO - Add more axiom tests

    private static void testPutandGet(Array<Integer> a) {
        Integer testint = new Integer(5);
        a.put(26, testint);
        Integer getint = a.get(26);
        assert testint == getint;
        Integer testint2 = new Integer(44);
        a.put(26, testint2);
        Integer getint2 = a.get(26);
        assert testint2 == getint2;
        Integer testint3 = new Integer(2);
        a.put(0, testint3);
        Integer getint3 = a.get(0);
        assert testint3 == getint3;
    }

    private static void itertest(Array<Integer> a) {
        assert a.get(10) == 5;
        a.put(10, 12);
        assert a.get(10) == 12;
        a.put(15, 11);
        a.put(18, 3);
        Iterator<Integer> testiter = a.iterator();
        int currpos = 0;
        while (testiter.hasNext()) {
            if (currpos == 10) {
                assert testiter.next() == 12;
            }
            else if (currpos == 15) {
                assert testiter.next() == 11;
            }
            else if (currpos == 18) {
                assert testiter.next() == 3;
            }
            else {
                assert testiter.next() == 5;
            }
            currpos += 1;
        }
    }

    private static void testSparse() {
        SparseArray<Integer> sparseTest2 = new SparseArray<Integer>(10, 3);
        sparseTest2.put(0, 9);
        assert sparseTest2.get(0) == 9;
        Iterator<Integer> testiter2 = sparseTest2.iterator();
        assert testiter2.next() == 9;
        assert testiter2.next() == 3;
    }


    private static void testNewWrongLength() {
        try {
            Array<Integer> a = new SimpleArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
    }

    private static void testIllegalGetIndex(Array<Integer> a) {
        try {
            a.get(113); //should throw exception
            assert false;
        }
        catch (IndexException e) {
            // passed
        }
        try {
            a.get(-5); //should throw exception
            assert false;
        }
        catch (IndexException e) {
            // passed
        }
    }

    private static void testIllegalPutIndex(Array<Integer> a) {
        try {
            Integer testint = new Integer(5);
            a.put(113, testint); //should throw exception
            assert false;
        }
        catch (IndexException e) {
            // passed
        }
        try {
            Integer testint2 = new Integer(5);
            a.put(-5, testint2); //should throw exception
            assert false;
        }
        catch (IndexException e) {
            // passed
        }
    }




    // TODO - Add more exception tests

    /**
     * Run (mostly polymorphic) tests on various array implementations.
     * Make sure you run this with -enableassertions! We'll learn a much
     * better approach to unit testing later.
     *
     * @param args Command line arguments (ignored).
    */
    public static void main(String[] args) {
        // For various technical reasons, we cannot use a plain Java array here
        // like we did in PolyCount. Sorry.
        ArrayList<Array<Integer>> arrays = new ArrayList<>();
        arrays.add(new SimpleArray<>(LENGTH, INITIAL));
        arrays.add(new ListArray<>(LENGTH, INITIAL));
        arrays.add(new SparseArray<>(LENGTH, INITIAL));
        // Test all the axioms. We can do that nicely in a loop. In the test
        // methods, keep in mind that you are handed the same object over and
        // over again! I.e., order matters!
        ArrayList<Array<Integer>> arrays2 = new ArrayList<>();
        arrays2.add(new SimpleArray<>(ITERL, ITERI));
        arrays2.add(new ListArray<>(ITERL, ITERI));
        arrays2.add(new SparseArray<>(ITERL, ITERI));
        for (Array<Integer> a: arrays) {
            testNewLength(a);
            testPutandGet(a);
            // TODO - Call your axiom test methods
        }
        for (Array<Integer> a: arrays2) {
            itertest(a);
            // TODO - Call your axiom test methods
        }
        // Exception testing. Sadly we have to code each one of these
        // out manually, not even Java's reflection API would help...
        testNewWrongLength();
        testSparse();
        for (Array<Integer> a: arrays) {
            testIllegalGetIndex(a);
            testIllegalPutIndex(a);
        }
        // TODO - Call your exception test methods
    }
}
