package hw7;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/** Treap implementation of Map.
 *
 * @param <K> The key type
 * @param <V> The value type
 */
public class TreapMap<K extends Comparable<? super K>, V>
        implements OrderedMap<K, V> {

    private class Node {
        Node left;
        Node right;
        K key;
        V value;
        int priority;
        int height;

        // Constructor to make node creation easier to read.
        Node(K k, V v) {
            // left and right default to null
            this.key = k;
            this.value = v;
            this.priority = rand.nextInt(100000);
            this.height = 0;
        }

        // Constructor with priority argument,
        Node(K k, V v, int p) {
            // left and right default to null
            this.key = k;
            this.value = v;
            this.priority = p;
            this.height = 0;
        }

        // Just for debugging purposes.
        public String toString() {
            return "Node<key: " + this.key
                    + "; value: " + this.value
                    + ">";
        }

        // Return the balance factor for this node's subtree
        public int balance() {
            return height(this.left) - height(this.right);
        }

    }

    private Node root;
    private int size;
    private StringBuilder stringBuilder;
    private Random rand = new Random();


    @Override
    public int size() {
        return this.size;
    }

    /** Method to return balance factor of the tree.
     *
     * @return the balance factor
     */
    public int balance() {
        return this.root.balance();
    }

    /** Method to return height of the tree.
     *
     * @return the height
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

    /** Method to return pointer to root node.
     *
     * @return the root of the tree
     */

    public Node getRoot() {
        return this.root;
    }

    /** Method to return pointer to left child of node.
     *
     * @param n the node
     * @return the left child of the node
     */
    public Node getLeft(Node n) {
        return n.left;
    }

    /** Method to return pointer to right child of node.
     *
     * @param n the node
     * @return the right child of the node
     */
    public Node getRight(Node n) {
        return n.right;
    }

    /** Method to get the value contained within a node.
     *
     * @param n the node
     * @return the value
     */
    public V getVal(Node n) {
        return n.value;
    }

    /** Method to return priority of a node.
     *
     * @param n the node
     * @return the priority
     */
    public int getPriority(Node n) {
        return n.priority;
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


    @Override
    public boolean has(K k) {
        if (k == null) {
            return false;
        }
        return this.find(k) != null;
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
    public void put(K k, V v) {
        Node n = this.findForSure(k);
        n.value = v;
    }

    @Override
    public V get(K k) {
        Node n = this.findForSure(k);
        return n.value;
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

    private Node treapify(Node n) {
        if (n.left != null
                && n.left.priority > n.priority) {
            return rotateRight(n);
        } else if (n.right != null
                && n.right.priority > n.priority) {
            return rotateLeft(n);
        }
        return n;
    }


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
        return treapify(n);
    }

    private Node insert(Node n, K k, V v, int p) {
        if (n == null) {
            return new Node(k, v, p);
        }

        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.insert(n.left, k, v, p);

        } else if (cmp > 0) {
            n.right = this.insert(n.right, k, v, p);

        } else {
            throw new IllegalArgumentException("duplicate key " + k);
        }
        n.height = 1 + Math.max(height(n.left), height(n.right));
        return treapify(n);
    }

    @Override
    public void insert(K k, V v) throws IllegalArgumentException {
        if (k == null) {
            throw new IllegalArgumentException("null key");
        }
        this.root = this.insert(this.root, k, v);
        this.size++;
    }

    /** Insert method with priority as an argument, for testing purposes.
     *
     * @param k key
     * @param v value
     * @param p priority
     * @throws IllegalArgumentException if key is null
     */
    public void insert(K k, V v, int p) throws IllegalArgumentException {
        if (k == null) {
            throw new IllegalArgumentException("null key");
        }
        this.root = this.insert(this.root, k, v, p);
        this.size++;
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
        n.priority = -1;
        root = remove(root, k);
        return val;
    }

    /**
     * Method to remove from a subtree.
     * @param k the item to remove.
     * @param n the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private Node remove(Node n, K k) {
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }
        int cmp = k.compareTo(n.key);
        if (cmp < 0) {
            n.left = this.remove(n.left, k);
        } else if (cmp > 0) {
            n.right = this.remove(n.right, k);
        } else if (n.left == null) {
            Node temp = n.right;
            n = null;
            n = temp;
        } else if (n.right == null) {
            Node temp = n.left;
            n = null;
            n = temp;
        } else {
            int leftp = 0;
            if (n.left != null) {
                leftp = n.left.priority;
            }
            int rightp = 0;
            if (n.right != null) {
                rightp = n.right.priority;
            }
            if (leftp < rightp) {
                n = rotateLeft(n);
                n.left = remove(n.left, k);
            } else {
                n = rotateRight(n);
                n.right = remove(n.right, k);
            }
        }
        return n;
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