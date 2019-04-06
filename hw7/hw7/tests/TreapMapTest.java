package hw7.tests;

import hw7.Map;
import hw7.OrderedMap;
import hw7.TreapMap;

/** Instantiate the TreapMap to test. */
public class TreapMapTest extends OrderedMapTest {

    @Override
    protected OrderedMap<Integer, String> createMap() {
        return new TreapMap<>();
    }

}
