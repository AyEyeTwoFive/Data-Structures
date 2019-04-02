package hw7.tests;

import hw7.Map;
import hw7.SimpleMap;

/** Instantiate the SimpleMap to test. */
public class SimpleMapTest extends MapTest {

    @Override
    protected Map<Integer, String> createMap() {
        return new SimpleMap<>();
    }

}
