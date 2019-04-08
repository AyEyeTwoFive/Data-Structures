package hw7.tests;

import hw7.Map;
import hw7.OrderedMap;
import hw7.TreapMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

/** Instantiate the TreapMap to test. */
public class TreapMapTest extends OrderedMapTest {

    @Override
    protected OrderedMap<Integer, String> createMap() {
        return new TreapMap<>();
    }

    protected TreapMap<Integer, String> treap = new TreapMap<>();

    @Test
    public void leftRotation() {
        treap.insert((Integer) 2, "A", 2);
        treap.insert((Integer) 1, "B", 1);
        treap.insert((Integer) 3, "C", 3);
        assertEquals("{1: B, 2: A, 3: C}", treap.toString());
        assertEquals("C", treap.getVal(treap.getRoot()));
        assertEquals(2, treap.height());
        assertEquals(2, treap.balance());
        assertEquals(3, treap.size());
    }

    @Test
    public void rightRotation() {
        treap.insert((Integer) 2, "A", 2);
        treap.insert((Integer) 3, "B", 1);
        treap.insert((Integer) 1, "C", 3);
        assertEquals("{1: C, 2: A, 3: B}", treap.toString());
        assertEquals("C", treap.getVal(treap.getRoot()));
        assertEquals(2, treap.height());
        assertEquals(-2, treap.balance());
        assertEquals(3, treap.size());
    }

    @Test
    public void testTreap() {
        treap.insert((Integer) 2, "B", 1);
        treap.insert((Integer) 9, "S", 2);
        treap.insert((Integer) 7, "Q", 3);
        treap.insert((Integer) 8, "R", 4);
        treap.insert((Integer) 10, "T", 0);
        assertEquals(5, treap.size());
        assertEquals(4, treap.getPriority(treap.getRoot()));
        assertEquals("R", treap.getVal(treap.getRoot()));
    }

    @Test
    public void bigTreapTest() {
        treap.insert((Integer) 1, "A", 5);
        treap.insert((Integer) 3, "B", 9);
        treap.insert((Integer) 5, "C", 10);
        treap.insert((Integer) 7, "D", 30);
        treap.insert((Integer) 9, "E", 25);
        treap.insert((Integer) 11, "F", 27);
        treap.insert((Integer) 13, "G", 19);
        treap.insert((Integer) 15, "H", 35);
        treap.insert((Integer) 17, "I", 35);
        treap.insert((Integer) 19, "J", 77);
        treap.insert((Integer) 21, "K", 96);
        treap.insert((Integer) 23, "L", 42);
        treap.insert((Integer) 25, "M", 13);
        treap.insert((Integer) 27, "N", 17);
        treap.insert((Integer) 29, "O", 46);
        treap.insert((Integer) 31, "P", 44);
        treap.insert((Integer) 2, "Q", 86);
        treap.insert((Integer) 6, "R", 13);
        treap.insert((Integer) 10, "S", 31);
        treap.insert((Integer) 14, "T", 11);
        treap.insert((Integer) 18, "U", 41);
        treap.insert((Integer) 22, "V", 34);
        treap.insert((Integer) 26, "W", 22);
        treap.insert((Integer) 30, "X", 12);
        treap.insert((Integer) 4, "Y", 14);
        treap.insert((Integer) 12, "Z", 34);
        treap.insert((Integer) 20, "!", 64);
        treap.insert((Integer) 28, "@", 45);
        treap.insert((Integer) 8, "#", 32);
        treap.insert((Integer) 16, "$", 11);
        treap.insert((Integer) 24, "%", 34);
        assertEquals(31, treap.size());
        assertEquals(96, treap.getPriority(treap.getRoot()));
        assertEquals("K", treap.getVal(treap.getRoot()));
    }

    @Test
    public void removeTest() {
        treap.insert((Integer) 3, "A", 10);
        treap.insert((Integer) 1, "B", 5);
        treap.insert((Integer) 5, "C", 1);
        assertEquals(10, treap.getPriority(treap.getRoot()));
        assertEquals(5, treap.getPriority(treap.getLeft(treap.getRoot())));
        assertEquals(1, treap.getPriority(treap.getRight(treap.getRoot())));
        assertEquals("A", treap.remove((Integer) 3));
        assertFalse(treap.has(3));
        assertEquals(2, treap.size());
        assertEquals(5, treap.getPriority(treap.getRoot()));
        assertEquals("B", treap.getVal(treap.getRoot()));
    }

    @Test
    public void bigTreapWithRemove() {
        treap.insert((Integer) 1, "A", 5);
        treap.insert((Integer) 3, "B", 9);
        treap.insert((Integer) 5, "C", 10);
        treap.insert((Integer) 7, "D", 30);
        treap.insert((Integer) 9, "E", 25);
        treap.insert((Integer) 11, "F", 27);
        treap.insert((Integer) 13, "G", 19);
        treap.insert((Integer) 15, "H", 35);
        treap.insert((Integer) 17, "I", 35);
        treap.insert((Integer) 19, "J", 77);
        treap.insert((Integer) 21, "K", 96);
        treap.insert((Integer) 23, "L", 42);
        treap.insert((Integer) 25, "M", 13);
        treap.insert((Integer) 27, "N", 17);
        treap.insert((Integer) 29, "O", 46);
        treap.insert((Integer) 31, "P", 44);
        treap.insert((Integer) 2, "Q", 86);
        treap.insert((Integer) 6, "R", 13);
        treap.insert((Integer) 10, "S", 31);
        treap.insert((Integer) 14, "T", 11);
        treap.insert((Integer) 18, "U", 41);
        treap.insert((Integer) 22, "V", 34);
        treap.insert((Integer) 26, "W", 22);
        treap.insert((Integer) 30, "X", 12);
        treap.insert((Integer) 4, "Y", 14);
        treap.insert((Integer) 12, "Z", 34);
        treap.insert((Integer) 20, "!", 64);
        treap.insert((Integer) 28, "@", 45);
        treap.insert((Integer) 8, "#", 32);
        treap.insert((Integer) 16, "$", 11);
        treap.insert((Integer) 24, "%", 34);
        assertEquals("K", treap.remove((Integer) 21));
        assertEquals("U", treap.remove((Integer) 18));
        assertEquals("Q", treap.remove((Integer) 2));
        assertFalse(treap.has(21));
        assertEquals(28, treap.size());
        assertEquals(77, treap.getPriority(treap.getRoot()));
        assertEquals("J", treap.getVal(treap.getRoot()));
    }


}
