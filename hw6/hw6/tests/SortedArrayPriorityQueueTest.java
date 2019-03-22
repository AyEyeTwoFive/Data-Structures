package hw6.tests;

import hw6.PriorityQueue;
import hw6.SortedArrayPriorityQueue;

/** Instantiate the SortedArrayPriorityQueue to test. */
public class SortedArrayPriorityQueueTest extends PriorityQueueTest {
    @Override
    protected PriorityQueue<String> createUnit() {
        return new SortedArrayPriorityQueue<>();
    }
}
