package hw7.tests;
import org.junit.Before;
import org.junit.Test;
import hw7.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the Map interface.
 *
 */
public abstract class MapTest {
    private Map<Integer, String> map;

    protected abstract Map<Integer, String> createMap();

    Integer one = 1;
    Integer two = 2;

    @Before
    public void setupMapTests() { map = this.createMap(); }

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

}