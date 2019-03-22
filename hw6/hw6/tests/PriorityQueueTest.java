package hw6.tests;

import exceptions.EmptyException;
import hw6.PriorityQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the PriorityQueue interface.
 */
public abstract class PriorityQueueTest {
    private PriorityQueue<String> unit;

    protected abstract PriorityQueue<String> createUnit();

    @Before
    public void setupTests() {
        unit = this.createUnit();
    }

    @Test
    public void newQueueEmpty() {
        assertTrue(unit.empty());
    }

    @Test(expected=EmptyException.class)
    public void newQueueNoBest() {
        String s = unit.best();
    }

    @Test(expected=EmptyException.class)
    public void newQueueNoRemove() {
        unit.remove();
    }

    @Test
    public void insertNotEmpty() {
        unit.insert("Paul");
        assertFalse(unit.empty());
    }

    @Test
    public void insertBest() {
        unit.insert("Paul");
        assertEquals("Paul", unit.best());

        unit.insert("Peter");
        assertEquals("Peter", unit.best());

        unit.insert("Mary");
        assertEquals("Peter", unit.best());
    }

    @Test
    public void insertRemoveInOrder() {
        String d[] = {"Zion", "Tom", "Karen", "Ed", "Billy", "Beverly"};
        for (int i = d.length-1; i >= 0; i--) {
            unit.insert(d[i]);
        }

        for (String s: d) {
            assertEquals(s, unit.best());
            unit.remove();
        }
    }
}
