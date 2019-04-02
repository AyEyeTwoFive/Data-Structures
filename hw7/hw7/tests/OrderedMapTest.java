package hw7.tests;
import org.junit.Before;
import org.junit.Test;
import hw7.Map;
import hw7.OrderedMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the Ordered Map interface.
 *
 */
public abstract class OrderedMapTest {
    private OrderedMap<Integer, String> map;

    protected abstract OrderedMap<Integer, String> createMap();

    Integer one = 1;
    Integer two = 2;

    @Before
    public void setupOrderedMapTests() {
        map = this.createMap();
    }

    @Test(expected=IllegalArgumentException.class)
    public void insertNullKey() {
        map.insert(null, "A");
    }

    @Test(expected=IllegalArgumentException.class)
    public void insertRepeat() {
        map.insert(one, "A");
        map.insert(one, "B");
    }

    @Test(expected=IllegalArgumentException.class)
    public void removeNullKey() {
        map.insert(one, "A");
        map.remove(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void removeNotMapped() {
        map.insert(one, "A");
        map.remove(two);
    }

    @Test(expected=IllegalArgumentException.class)
    public void putNullKey() {
        map.insert(one, "A");
        map.put(null, "B");
    }

    @Test(expected=IllegalArgumentException.class)
    public void putNotMapped() {
        map.insert(one, "A");
        map.put(two, "B");
    }

    @Test(expected=IllegalArgumentException.class)
    public void getNullKey() {
        map.insert(one, "A");
        map.get(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getNotMapped() {
        map.insert(one, "A");
        map.get(two);
    }

    @Test
    public void insertThenGet() {
        map.insert(one, "A");
        assertEquals("A", map.get(one));
    }

    @Test(expected=IllegalArgumentException.class)
    public void insertThenRemove() {
        map.insert(one, "A");
        map.remove(one);
        assertEquals("A", map.get(one));
    }

    @Test
    public void insertThenPut() {
        map.insert(one, "A");
        map.put(one, "B");
        assertEquals("B", map.get(one));
    }

    @Test
    public void hasEmpty() {
        assertEquals(false, map.has(one));
    }

    @Test
    public void insertThenHas() {
        map.insert(one, "A");
        assertEquals(true, map.has(one));
        assertEquals(false, map.has(two));
    }

    @Test
    public void insertThenRemoveThenHas() {
        map.insert(one, "A");
        map.remove(one);
        assertEquals(false, map.has(one));
    }

    @Test
    public void emptySize() {
        assertEquals(0, map.size());
    }

    @Test
    public void insertSize() {
        map.insert(one, "A");
        map.insert(two, "B");
        assertEquals(2, map.size());
    }

    @Test
    public void testOrdered() {
        map.insert((Integer) 2, "B");
        map.insert((Integer) 9, "S");
        map.insert((Integer) 7, "Q");
        map.insert((Integer) 8, "R");
        map.insert((Integer) 5, "M");
        map.insert((Integer) 10, "T");
        map.insert((Integer) 3, "D");
        map.insert((Integer) 12, "Z");
        map.insert((Integer) 1, "A");
        map.insert((Integer) 4, "J");
        map.insert((Integer) 6, "P");
        map.insert((Integer) 11, "X");
        assertEquals("{1: A, 2: B, 3: D, 4: J, 5: M, 6: P, 7: Q, 8: R, " +
                "9: S, 10: T, 11: X, 12: Z}", map.toString());
    }

    @Test
    public void testOrdered2() {
        map.insert((Integer) 2, "B");
        map.insert((Integer) 9, "S");
        map.insert((Integer) 7, "Q");
        map.insert((Integer) 8, "R");
        map.insert((Integer) 5, "M");
        map.insert((Integer) 10, "T");
        map.insert((Integer) 3, "D");
        map.insert((Integer) 12, "Z");
        map.insert((Integer) 1, "A");
        map.insert((Integer) 4, "J");
        map.insert((Integer) 6, "P");
        map.insert((Integer) 11, "X");
        map.put((Integer) 5, "W");
        map.put((Integer) 8, "F");
        map.remove((Integer) 3);
        map.remove((Integer) 9);
        assertEquals(true, map.has(8));
        assertEquals(false, map.has(9));
        assertEquals("{1: A, 2: B, 4: J, 5: W, 6: P, 7: Q, 8: F, " +
                "10: T, 11: X, 12: Z}", map.toString());
    }


}