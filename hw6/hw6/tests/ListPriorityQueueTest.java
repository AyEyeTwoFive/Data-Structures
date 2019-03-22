package hw6.tests;

import hw6.ListPriorityQueue;
import hw6.PriorityQueue;

/** Instantiate the ListPriorityQueue to test. */
public class ListPriorityQueueTest extends PriorityQueueTest {
    @Override
    protected PriorityQueue<String> createUnit() {
        return new ListPriorityQueue<>();
    }
}
