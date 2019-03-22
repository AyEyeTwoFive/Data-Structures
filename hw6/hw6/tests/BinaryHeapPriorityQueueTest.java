package hw6.tests;

import hw6.BinaryHeapPriorityQueue;
import hw6.PriorityQueue;

/** Instantiate the BinaryHeapPriorityQueue to test. */
public class BinaryHeapPriorityQueueTest extends PriorityQueueTest {
    @Override
    protected PriorityQueue<String> createUnit() {
        return new BinaryHeapPriorityQueue<>();
    }
}
