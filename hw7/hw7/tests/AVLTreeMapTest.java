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

    Integer three = 3;

    @Test
    public void leftRotation() {
        map.insert(one, "A");
        map.insert(two, "B");
        map.insert(three,"C");
        assertEquals("{1: A, 2: B, 3: C}", map.toString());
        assertEquals(2, map.height());
        assertEquals(0, map.balance());
    }


    @Test
    public void rightRotation() {
        map.insert(three, "A");
        map.insert(two, "B");
        map.insert(one,"C");
        assertEquals("{3: A, 2: B, 1: C}", map.toString());
        assertEquals(2, map.height());
        assertEquals(0, map.balance());
    }

    @Test
    public void LRRotation() {
        map.insert(three, "A");
        map.insert(one, "B");
        map.insert(two,"C");
        assertEquals("{3: A, 1: B, 2: C}", map.toString());
        assertEquals(2, map.height());
        assertEquals(0, map.balance());
    }

    @Test
    public void RLRotation() {
        map.insert(one, "A");
        map.insert(three, "B");
        map.insert(two,"C");
        assertEquals("{1: A, 3: B, 2: C}", map.toString());
        assertEquals(2, map.height());
        assertEquals(0, map.balance());
    }
}
