// Deque226Test.java

package hw4.tests;

import exceptions.EmptyException;
import hw4.Deque226;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public abstract class Deque226Test {

    // Subclasses implement this to return the instance of the Deque226
    // they are testing.
    protected abstract Deque226<String> createUnit();

    // The unit being tested
    private Deque226<String> dequeue;

    @Before
    public void setupDequeue() {
        this.dequeue = createUnit();
    }

    @Test
    public void newDequeueEmpty() {
       assertTrue(dequeue.empty());
       assertEquals(0, dequeue.length());
    }

    @Test(expected=EmptyException.class)
    public void removeFrontOnEmpty() {
        dequeue.removeFront();
    }

    @Test(expected=EmptyException.class)
    public void removeBackOnEmpty() {
        dequeue.removeBack();
    }

    @Test(expected=EmptyException.class)
    public void getFrontOnEmpty() {
        dequeue.front();
    }

    @Test(expected=EmptyException.class)
    public void getBackOnEmpty() {
        dequeue.back();
    }

    @Test
    public void insertFrontandGetLength() {
        dequeue.insertFront("Test");
        dequeue.insertFront("Test");
        dequeue.insertFront("Test");
        assertEquals(3,dequeue.length());
        assertTrue(!dequeue.empty());
    }

    @Test
    public void insertFrontAndGetFrontAndBack() {
        dequeue.insertFront("Test1");
        dequeue.insertFront("Test2");
        assertEquals(2,dequeue.length());
        assertTrue(!dequeue.empty());
        assertEquals("Test2",dequeue.front());
        assertEquals("Test1",dequeue.back());
    }

    @Test
    public void insertBackAndGetFrontAndBack() {
        dequeue.insertBack("Test1");
        dequeue.insertBack("Test2");
        assertEquals(2,dequeue.length());
        assertTrue(!dequeue.empty());
        assertEquals("Test1",dequeue.front());
        assertEquals("Test2",dequeue.back());
    }

    @Test
    public void removeFrontTest() {
        dequeue.insertFront("Test1");
        dequeue.insertFront("Test2");
        dequeue.insertFront("Test3");
        dequeue.insertFront("Test4");
        dequeue.removeFront();
        assertEquals("Test3", dequeue.front());
    }

    @Test
    public void removeBackTest() {
        dequeue.insertFront("Test1");
        dequeue.insertFront("Test2");
        assertEquals("Test1", dequeue.back());
        dequeue.insertFront("Test3");
        assertEquals("Test1", dequeue.back());
        dequeue.insertFront("Test4");
        assertEquals("Test4", dequeue.front());
        assertEquals("Test1", dequeue.back());
        dequeue.removeBack();
        assertEquals("Test2",dequeue.back());
    }

    @Test
    public void removeBackGetFront() {
        dequeue.insertFront("Test1");
        dequeue.insertBack("Test2");
        dequeue.removeBack();
        assertEquals("Test1", dequeue.front());
    }

    @Test
    public void backAfterDouble() {
        dequeue.insertFront("Test1");
        dequeue.insertFront("Test2");
        dequeue.insertFront("Test3");
        assertEquals("Test1", dequeue.back());
        dequeue.insertFront("Test4");
        dequeue.insertBack("Test5");
        assertEquals("Test5",dequeue.back());
        assertEquals(5, dequeue.length());
        assertEquals("Test4", dequeue.front());
        dequeue.removeBack();
        assertEquals("Test1", dequeue.back());
    }

    @Test
    public void testToString() {
        String empty = dequeue.toString();
        assertEquals("[]", empty);
        dequeue.insertFront("Test1");
        dequeue.insertFront("Test2");
        dequeue.insertFront("Test3");
        String test1 = dequeue.toString();
        assertEquals("[Test3, Test2, Test1]", test1);
        dequeue.removeBack();
        String test2 = dequeue.toString();
        assertEquals("[Test3, Test2]", test2);
        dequeue.insertFront("Test4");
        String test3 = dequeue.toString();
        assertEquals("[Test4, Test3, Test2]", test3);
        dequeue.insertBack("Test5");
        dequeue.insertBack("Test6");
        dequeue.insertBack("Test7");
        dequeue.removeFront();
        assertEquals("Test7", dequeue.back());
        assertEquals("Test3", dequeue.front());
        assertEquals(5, dequeue.length());
        String test4 = dequeue.toString();
        assertEquals("[Test3, Test2, Test5, Test6, Test7]", test4);
    }






    // TODO - Add more tests!
}
