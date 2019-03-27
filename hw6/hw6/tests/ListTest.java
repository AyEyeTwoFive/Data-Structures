package hw6.tests;

import exceptions.EmptyException;
import exceptions.PositionException;
import hw5.List;
import hw5.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the List interface.
 *
 * The tests defined here apply to all implementations of the List
 * interface. However, they cannot be run directly as we don't know
 * which implementation to test or how to create an instance of it.
 *
 * The solution is to define a "template method" called createList()
 * that subclasses of this test override. The LinkedListTest.java class,
 * for example, creates a suitable LinkedList instance to be tested.
 *
 * Note that we (somewhat arbitrarily) choose to test lists of strings.
 * We could have gone for lists of integers or lists of whatever, but
 * strings seem convenient in any case: You can pick strings in such a
 * way as to make your test cases more readable.
 */
public abstract class ListTest {
    private List<String> list;

    protected abstract List<String> createList();

    @Before
    public void setupListTests() {
        list = this.createList();
    }

    @Test
    public void newListEmpty() {
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertEquals("[]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test(expected=EmptyException.class)
    public void newListNoFront() {
        Position<String> p = list.front();
    }

    @Test(expected=EmptyException.class)
    public void newListNoBack() {
        Position<String> p = list.back();
    }

    @Test
    public void insertFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Three, Two, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertBackWorks() {
        list.insertBack("One");
        list.insertBack("Two");
        list.insertBack("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[One, Two, Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertFrontBackConsistent() {
        Position<String> f = list.insertFront("Front");
        assertEquals("Front", f.get());
        Position<String> b = list.insertBack("Back");
        assertEquals("Back", b.get());

        assertNotEquals(f, b);

        assertTrue(list.first(f));
        assertTrue(list.last(b));

        Position<String> x;

        x = list.front();
        assertEquals(f, x);

        x = list.back();
        assertEquals(b, x);
    }

    @Test
    public void removeFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeFront();
        list.removeFront();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    @Test
    public void removeBackWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeBack();
        list.removeBack();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    // TODO You need to add *many* more test cases here, ideally before you
    // even start working on LinkedList!

    @Test
    public void addFrontRemoveBack() {
        list.insertFront("One");
        assertEquals(list.front(),list.back());
        list.removeBack();
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertEquals("[]", list.toString());
        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void removeThenAdd() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        list.removeBack();
        Position<String> four = list.insertBack("Four");
        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Three, Two, Four]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }


    @Test
    public void removeWorks() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        list.remove(two);

        assertFalse(list.empty());
        assertEquals(2, list.length());
        assertEquals("[Three, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(2, c);

    }

    @Test(expected=PositionException.class)
    public void removeWhenEmpty() {
        assertTrue(list.empty());
        Position<String> one = list.insertFront("One");
        list.remove(one);
        list.remove(one);
    }

    @Test(expected=EmptyException.class)
    public void removeFrontWhenEmpty() {
        Position<String> one = list.insertFront("One");
        list.remove(one);
        assertTrue(list.empty());
        list.removeFront();
    }

    @Test(expected=EmptyException.class)
    public void removeBackWhenEmpty() {
        Position<String> one = list.insertFront("One");
        list.remove(one);
        assertTrue(list.empty());
        list.removeBack();
    }

    @Test
    public void insertBeforeWorks() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        Position<String> four = list.insertBefore(three, "Four");
        assertFalse(list.empty());
        assertEquals(4, list.length());
        assertEquals("[Four, Three, Two, One]", list.toString());
        assertTrue(list.first(four));
        assertTrue(list.last(one));

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(4, c);
    }

    @Test
    public void insertAfterWorks() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        Position<String> four = list.insertAfter(three, "Four");
        assertFalse(list.empty());
        assertEquals(4, list.length());
        assertEquals("[Three, Four, Two, One]", list.toString());
        assertTrue(list.first(three));
        assertTrue(list.last(one));

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(4, c);
    }

    @Test
    public void nextWorks() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        Position<String> afterTwo = list.next(two);
        assertEquals(one, afterTwo);
    }

    @Test
    public void previousWorks() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        Position<String> beforeTwo = list.previous(two);
        assertEquals(three, beforeTwo);
    }

    @Test(expected=PositionException.class)
    public void gettingNextAtBack() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        assertTrue(list.last(one));
        Position<String> afterOne = list.next(one);
    }

    @Test//(expected=PositionException.class)
    public void gettingPreviousAtFront() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        assertTrue(list.first(two));
        Position<String> beforeTwo = list.previous(two);
    }

    @Test(expected=PositionException.class)
    public void insertAfterInvalid() {
        Position<String> one = list.insertFront("One");
        list.removeFront();
        Position<String> two = list.insertAfter(one, "Two");
    }

    @Test(expected=PositionException.class)
    public void insertBeforeInvalid() {
        Position<String> one = list.insertFront("One");
        list.removeFront();
        Position<String> two = list.insertBefore(one, "Two");
    }

    @Test(expected=PositionException.class)
    public void firstInvalid() {
        Position<String> one = list.insertFront("One");
        list.removeFront();
        assertTrue(list.first(one));
    }

    @Test(expected=PositionException.class)
    public void lastInvalid() {
        Position<String> one = list.insertFront("One");
        list.removeFront();
        assertTrue(list.last(one));
    }

    @Test(expected=EmptyException.class)
    public void frontWhenEmpty() {
        assertTrue(list.empty());
        list.front();
    }

    @Test(expected=EmptyException.class)
    public void backWhenEmpty() {
        assertTrue(list.empty());
        list.back();
    }

    @Test
    public void forwardIteration() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void backwardIteration() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        int c = 3;
        for (String s: list) {
            c--;
        }
        assertEquals(0, c);
    }


}
