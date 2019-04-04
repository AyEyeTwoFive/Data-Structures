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

/** Instantiate the AvlTreeMap to test. */
public class AvlTreeMapTest extends OrderedMapTest {

    @Override
    protected OrderedMap<Integer, String> createMap() {
        return new AvlTreeMap<>();
    }

    protected AvlTreeMap<Integer, String> avl = new AvlTreeMap<>();

    Integer three = 3;

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
}
