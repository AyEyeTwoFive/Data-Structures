package hw7;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Avl Tree implementation of ordered map interface.
 * @param <K> key
 * @param <V> value
 */
public class AvlTreeMap<K extends Comparable<? super K>, V>
        implements OrderedMap<K, V> {

    // Inner node class, each holds a key (which is what we sort the
    // BST by) as well as a value. We don't need a parent pointer as
    // long as we use recursive insert/remove helpers.
    private class Node {
        Node parent;
        Node left;
        Node right;
        K key;
        V value;
        int height;

        // Constructor to make node creation easier to read.
        Node(K k, V v) {
            // left and right default to null
            this.key = k;
            this.value = v;
            this.height = 0;
        }

        // Return the balance factor for this node's subtree
        public int balance() {
            return height(this.left) - height(this.right);
        }

        // Just for debugging purposes.
        public String toString() {
            return "Node<key: " + this.key
                    + "; value: " + this.value
                    + ">";
        }
    }

    private Node root;
    private int size;
    private StringBuilder stringBuilder;

    @Override
    public int size() {
        return this.size;
    }

    /** Method to return balance factor of the tree.
     *
     * @return balance factor
     */
    public int balance() {
        return this.root.balance();
    }

    /** Method to balance the tree after adding
     *
     * @param n node of subtree to balance
     * @return node with balanced subtree
     */
    private Node balance(Node n) {
        if (n.balance() < -1) { // rotate left
            if (n.right.balance() > 0) { // right rotate
                n.right = rotateRight(n.right);
            }
            n = rotateLeft(n);
        }
        else if (n.balance() > 1) { // right rotate
            if (n.left.balance() < 0) { // left rotate
                n.left = rotateLeft(n.left);
            }
            n = rotateRight(n);
        }
        return n;
    }

    /** Method to return height of the tree.
     *
     * @return height
     */
    public int height() {
        return height(this.root);
    }

    /** Method to get height of a node
     *
     * @param n the node
     * @return height
     */
    private int height(Node n) {
        if (n == null) {
            return -1;
        }
        return n.height;
    }

    @Override
    public void insert(K k, V v) {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }
        this.root = this.insert(this.root, k, v);
        this.size += 1;
    }

    // Insert given key and value into subtree rooted
    // at given node; return changed subtree with new
    // node added. (Doing this recursively makes it
    // easier to add fancy rebalancing code later.)
    private Node insert(Node n, K k, V v) {
        if (n == null) {
            return new Node(k, v);
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.insert(n.left, k, v);
        } else if (cmp > 0) {
            n.right = this.insert(n.right, k, v);
        } else {
            throw new IllegalArgumentException("duplicate key " + k);
        }
        n.height = 1 + Math.max(height(n.left), height(n.right));
        return balance(n);
    }


    /** Method to rotate the subtree to the right.
     *
     * @param n Node of subtree
     * @return node with rotated subtree
     */
    private Node rotateRight(Node n) {
        Node temp = n.left;
        n.left = temp.right;
        temp.right = n;
        n.height = 1 + Math.max(height(n.left), height(n.right));
        temp.height = 1 + Math.max(height(temp.left), height(temp.right));
        return temp;
    }

    /** Method to rotate the subtree to the left.
     *
     * @param n Node of subtree
     * @return node with rotated subtree
     */
    private Node rotateLeft(Node n) {
        Node temp = n.right;
        n.right = temp.left;
        temp.left = n;
        n.height = 1 + Math.max(height(n.left), height(n.right));
        temp.height = 1 + Math.max(height(temp.left), height(temp.right));
        return temp;
    }

    // Return node for given key. This one is iterative
    // but the recursive one from lecture would work as
    // well. (For simply finding a node there's no big
    // advantage to using recursion; I did recursion in
    // lecture to get you into the right mindset.)
    private Node find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }
        Node n = this.root;
        while (n != null) {
            int cmp = k.compareTo(n.key);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    // Return node for given key, throw an exception
    // if the key is not in the tree.
    private Node findForSure(K k) {
        Node n = this.find(k);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }
        return n;
    }

    @Override
    public V get(K k) {
        Node n = this.findForSure(k);
        return n.value;
    }

    @Override
    public boolean has(K k) {
        if (k == null) {
            return false;
        }
        return this.find(k) != null;
    }

    @Override
    public void put(K k, V v) {
        Node n = this.findForSure(k);
        n.value = v;
    }

    // Return node with maximum key in subtree rooted
    // at given node. (Iterative version because once
    // again recursion has no advantage here.)
    private Node max(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private Node min(Node n) {
        if (n == null) {
            return n;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    @Override
    public V remove(K k) {
        this.size -= 1;
        Node n = this.findForSure(k);
        V val = n.value;
        root = remove(k, root);
        return val;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private Node remove(K k, Node n) {
        if (n == null) {
            return n;   // Item not found; do nothing
        }
        int compareResult = k.compareTo(n.key);
        if (compareResult < 0) {
            n.left = remove(k, n.left);
        }
        else if (compareResult > 0) {
            n.right = remove(k, n.right);
        }
        else if (n.left != null && n.right != null) { // Two children
            n.key = min(n.right).key;
            n.value = min(n.right).value;
            n.right = remove(n.key, n.right);
        }
        else {
            if (n.left == null) {
                n = n.right;
            }
            else {
                n = n.left;
            }
            return n;
        }
        return balance(n);
    }

    // Recursively add keys from subtree rooted at given node
    // into the given list.
    private void iteratorHelper(Node n, List<K> keys) {
        if (n == null) {
            return;
        }
        this.iteratorHelper(n.left, keys);
        keys.add(n.key);
        this.iteratorHelper(n.right, keys);
    }

    @Override
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        this.iteratorHelper(this.root, keys);
        return keys.iterator();
    }

    // If we don't have a StringBuilder yet, make one;
    // otherwise just reset it back to a clean slate.
    private void setupStringBuilder() {
        if (this.stringBuilder == null) {
            this.stringBuilder = new StringBuilder();
        } else {
            this.stringBuilder.setLength(0);
        }
    }

    // Recursively append string representations of keys and
    // values from subtree rooted at given node.
    private void toStringHelper(Node n, StringBuilder s) {
        if (n == null) {
            return;
        }
        this.toStringHelper(n.left, s);
        s.append(n.key);
        s.append(": ");
        s.append(n.value);
        s.append(", ");
        this.toStringHelper(n.right, s);
    }

    @Override
    public String toString() {
        this.setupStringBuilder();
        this.stringBuilder.append("{");

        this.toStringHelper(this.root, this.stringBuilder);

        int length = this.stringBuilder.length();
        if (length > 1) {
            // If anything was appended at all, get rid of
            // the last ", " the toStringHelper put in.
            this.stringBuilder.setLength(length - 2);
        }
        this.stringBuilder.append("}");

        return this.stringBuilder.toString();
    }
}
