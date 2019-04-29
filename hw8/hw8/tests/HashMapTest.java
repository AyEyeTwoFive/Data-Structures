package hw8.tests;

import hw8.Map;
import hw8.HashMap;

/** Instantiate the HashMap to test. */
public class HashMapTest extends MapTest {

    @Override
    protected Map<Integer, String> createMap() {
        return new HashMap<>();
    }

}
