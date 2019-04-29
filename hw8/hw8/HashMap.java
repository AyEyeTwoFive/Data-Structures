package hw8;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

// Nested class defining a node within our HashMap
class HashNode<K, V>
{
    K key;
    V value;
    HashNode<K, V> next; // reference to next

    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}

/**
 * Maps from arbitrary keys to arbitrary values.
 *
 * Implements a Hash Map using separate chaining.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class HashMap<K, V> implements Map<K, V> {
    private ArrayList<HashNode<K, V>> buckets;
    private int numBuckets;
    private int size;

    public HashMap() {
        this.buckets = new ArrayList<>();
        this.numBuckets = 100;
        this.size = 0;
        // initialize our buckets
        for (int i = 0; i < numBuckets; i++)
            buckets.add(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    // hash function: key -> index
    private int getIndex(K k) {
        return k.hashCode() % numBuckets; // use mod to get index
    }

    private HashNode<K,V> find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("null key");
        }
        int index = getIndex(k);
        HashNode<K, V> cur = buckets.get(index);
        while(cur !=null) {
            if (cur.key.equals(k)) {
                return cur;
            }
            cur = cur.next;
        }
        throw new IllegalArgumentException("not found");
    }

    @Override
    public V remove(K k) {
        if (k == null) {
            throw new IllegalArgumentException("null key");
        }
        int index = getIndex(k);
        HashNode<K, V> head = buckets.get(index);
        HashNode<K, V> prev = null;
        while (head != null) { // loop through to find key
            if (head.key.equals(k))
                break;
            prev = head;
            head = head.next;
        }
        if (head == null) { // not found
            throw new IllegalArgumentException("not found");
        }
        this.size--;
        if (prev != null) {
            prev.next = head.next;
        }
        else {
            buckets.set(index, head.next);
        }
        return head.value;
    }

    @Override
    public V get(K k) {
        HashNode<K, V> get = this.find(k);
        return get.value;
    }

    @Override
    public boolean has(K k) {
        try {
            HashNode<K, V> get = this.find(k);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void insert(K k, V v) {
        if (k == null) {
            throw new IllegalArgumentException("null key");
        }
        int index = getIndex(k);
        HashNode<K, V> cur = buckets.get(index);
        while (cur != null) {
            if (cur.key.equals(k)) {
                throw new IllegalArgumentException("already mapped");
            }
            cur = cur.next;
        }
        this.size++;
        cur = buckets.get(index);
        HashNode<K, V> node = new HashNode<K, V>(k, v);
        node.next = cur;
        buckets.set(index, node);
        // If load over 0.5, double
        if ((1.0 * this.size) / numBuckets >= 0.5) {
            ArrayList<HashNode<K, V>> temp = buckets;
            buckets = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            this.size = 0;
            for (int i = 0; i < numBuckets; i++) {
                buckets.add(null);
            }
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    this.insert(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    @Override
    public void put(K k, V v) {
        int index = getIndex(k);
        HashNode<K, V> head = buckets.get(index);
        while (head != null) {
            if (head.key.equals(k)) {
                head.value = v;
                return;
            }
            head = head.next;
        }
        throw new IllegalArgumentException("cannot find key " + k); //not found
    }

    @Override
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        for (HashNode<K, V> n: this.buckets) {
            HashNode<K, V> cur = n;
            while (cur != null) {
                keys.add(cur.key);
                cur = cur.next;
            }
        }
        return keys.iterator();
    }


}