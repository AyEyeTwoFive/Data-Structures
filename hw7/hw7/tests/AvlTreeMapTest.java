package hw7.tests;

import hw7.Map;
import hw7.OrderedMap;
import hw7.AvlTreeMap;

/** Instantiate the AvlTreeMap to test. */
public class AvlTreeMapTest extends OrderedMapTest {

    @Override
    protected OrderedMap<Integer, String> createMap() {
        return new AvlTreeMap<>();
    }

}
