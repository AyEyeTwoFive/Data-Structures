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
        //assertEquals(8, treap.getRoot());
    }


}
