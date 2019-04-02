package hw7.tests;

import hw7.Map;
import hw7.OrderedMap;
import hw7.BinarySearchTreeMap;

/** Instantiate the BSTMap to test. */
public class BinarySearchTreeMapTest extends OrderedMapTest {

    @Override
    protected OrderedMap<Integer, String> createMap() {
        return new BinarySearchTreeMap<>();
    }

}
