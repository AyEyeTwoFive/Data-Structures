package hw7.tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import hw7.Map;
import hw7.OrderedMap;
import hw7.AvlTreeMap;
import java.util.Random;
import java.util.ArrayList;

/** Instantiate the AvlTreeMap to test. */
public class AvlTreeMapTest extends OrderedMapTest {

    @Override
    protected OrderedMap<Integer, String> createMap() {
        return new AvlTreeMap<>();
    }

    protected AvlTreeMap<Integer, String> avl = new AvlTreeMap<>();

    Integer three = 3;
    Random rand = new Random();

    @Test
    public void leftRotation() {
        avl.insert(one, "A");
        avl.insert(two, "B");
        avl.insert(three,"C");
        assertEquals("{1: A, 2: B, 3: C}", avl.toString());
        assertEquals(1, avl.height());
        assertEquals(0, avl.balance());
    }


    @Test
    public void rightRotation() {
        avl.insert(three, "A");
        avl.insert(two, "B");
        avl.insert(one,"C");
        assertEquals("{1: C, 2: B, 3: A}", avl.toString());
        assertEquals(1, avl.height());
        assertEquals(0, avl.balance());
    }

    @Test
    public void LRRotation() {
        avl.insert(three, "A");
        avl.insert(one, "B");
        avl.insert(two,"C");
        assertEquals("{1: B, 2: C, 3: A}", avl.toString());
        assertEquals(1, avl.height());
        assertEquals(0, avl.balance());
    }

    @Test
    public void RLRotation() {
        avl.insert(one, "A");
        avl.insert(three, "B");
        avl.insert(two,"C");
        assertEquals("{1: A, 2: C, 3: B}", avl.toString());
        assertEquals(1, avl.height());
        assertEquals(0, avl.balance());
    }

    @Test
    public void bigTreeTest() {
        avl.insert((Integer) 1, "A");
        avl.insert((Integer) 3, "B");
        avl.insert((Integer) 5, "C");
        avl.insert((Integer) 7, "D");
        avl.insert((Integer) 9, "E");
        avl.insert((Integer) 11, "F");
        avl.insert((Integer) 13, "G");
        avl.insert((Integer) 15, "H");
        avl.insert((Integer) 17, "I");
        avl.insert((Integer) 19, "J");
        avl.insert((Integer) 21, "K");
        avl.insert((Integer) 23, "L");
        avl.insert((Integer) 25, "M");
        avl.insert((Integer) 27, "N");
        avl.insert((Integer) 29, "O");
        avl.insert((Integer) 31, "P");
        avl.insert((Integer) 2, "Q");
        avl.insert((Integer) 6, "R");
        avl.insert((Integer) 10, "S");
        avl.insert((Integer) 14, "T");
        avl.insert((Integer) 18, "U");
        avl.insert((Integer) 22, "V");
        avl.insert((Integer) 26, "W");
        avl.insert((Integer) 30, "X");
        avl.insert((Integer) 4, "Y");
        avl.insert((Integer) 12, "Z");
        avl.insert((Integer) 20, "!");
        avl.insert((Integer) 28, "@");
        avl.insert((Integer) 8, "#");
        avl.insert((Integer) 16, "$");
        avl.insert((Integer) 24, "%");
        assertEquals(31, avl.size());
        assertTrue(avl.height() <= 2*(Math.log(31) / Math.log(2))); // height <= 2* log base 2 of N
        assertTrue(Math.abs(avl.balance()) <= 1);
    }

    @Test
    public void randomInsertAndRemove() {
        for (int i = 1; i < 100; i++) { // start with 100 inserts
            avl.insert((Integer) i, "A");
        }
        assertTrue(avl.height() <= 2*(Math.log(31) / Math.log(2)));
        assertTrue(Math.abs(avl.balance()) <= 1);
        assertEquals(99, avl.size());
        int added = 99;
        ArrayList<Integer> removed = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            int decision = rand.nextInt(2); // remove or insert?
            if (decision == 0) { // let's insert
                added += 1;
                avl.insert((Integer) added, "A");
                assertTrue(avl.height() <= 2 * (Math.log(31) / Math.log(2)));
                assertTrue(Math.abs(avl.balance()) <= 1);
            }
            else { //let's remove
                int which = rand.nextInt(added); //pick random
                while (removed.contains(which) || which == 0) { //already removed
                    which = rand.nextInt(added);
                }
                removed.add(which);
                String temp = avl.get(which);
                assertEquals(temp, avl.remove(which));
            }
            assertTrue(avl.height() <= 2*(Math.log(31) / Math.log(2)));
            assertTrue(Math.abs(avl.balance()) <= 1);
        }

    }

}
