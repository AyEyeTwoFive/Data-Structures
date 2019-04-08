package hw7;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
    Random rand = new Random();
    protected ArrayList<Node> nodeList;

    @Override
    public int size() {
        return this.size;
    }

    public int balance() {
        return this.root.balance();
    }

    public int height() {
        return height(this.root);
    }

    public Node getRoot() {return this.root;};

    public Node getLeft(Node n) {
        return n.left;
    }

    public Node getRight(Node n) {
        return n.right;
    }

    public V getVal(Node n) {
        return n.value;
    }

    public int getPriority(Node n) {
        return n.priority;
    }

    public ArrayList<Node> inorder(Node root, ArrayList<Node> list) {
        if (root == null) {
            return list;
        }
        inorder(root.left,list);
        list.add(root);
        inorder(root.right,list);
        return list;
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

    /*@Override
    public V remove(K k) {
        return null;
    }

    @Override
    public void insert(K k, V v) {

    }*/

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
    private Node min(Node n)
    {
        if(n == null )
            return n;

        while(n.left != null)
            n = n.left;
        return n;
    }

    @Override
    public V remove(K k)
    {
        this.size -= 1;
        Node n = this.findForSure(k);
        V val = n.value;
        root = remove(k, root);
        return val;
    }

    /*@Override
    public V remove( K x )
    {
        this.size--;
        Node n = this.findForSure(x);
        V val = n.value;
        root = remove( x, root );
        return val;
    }*/

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private Node remove(K k, Node n) {
        if(n == null)
            return n;   // Item not found; do nothing
        int compareResult = k.compareTo(n.key);
        if(compareResult < 0)
            n.left = remove(k, n.left);
        else if(compareResult > 0)
            n.right = remove(k, n.right);
        else if(n.left != null && n.right != null ) // Two children
        {
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
        return treapify(n);
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    /*private Node remove(K x, Node t )
    {
        if( t != null )
        {
            int compareResult = x.compareTo( t.key );

            if( compareResult < 0 )
                t.left = remove( x, t.left );
            else if( compareResult > 0 )
                t.right = remove( x, t.right );
            else
            {
                // match -- one child or leaf?
                if ( t.left == null ) return t.right;
                if ( t.right ==  null) return t.left;

                // Match found, two children
                if( t.left.priority < t.right.priority ) {
                    t = rotateWithLeftChild( t );
                    t.right = remove (x, t.right);
                } else {
                    t = rotateWithRightChild( t );
                    t.left = remove( x, t.left);
                }
            }
        }
        return t;
    }*/

    /*private Node remove(K k, Node n) {
        if (n.left == null && n.right == null) {
            n = null;
            return n;
        }
        else if ( n.left != null && n.right != null) {
            if (n.left.priority > n.right.priority) { // rotate right
                Node temp = n.left;
                n.left = temp.right;
                temp.right = n;
                n.height = 1 + Math.max(height(n.left), height(n.right));
                temp.height = 1 + Math.max(height(temp.left), height(temp.right));
            }
            else { // rotate left
                Node temp = n.right;
                n.right = temp.left;
                temp.left = n;
                n.height = 1 + Math.max(height(n.left), height(n.right));
                temp.height = 1 + Math.max(height(temp.left), height(temp.right));
            }
        }
        else if (n.left != null) { // rotate left
            Node temp = n.left;
            n.left = temp.right;
            temp.right = n;
            n.height = 1 + Math.max(height(n.left), height(n.right));
            temp.height = 1 + Math.max(height(temp.left), height(temp.right));
        }
        else {
            Node temp = n.right;
            n.right = temp.left;
            temp.left = n;
            n.height = 1 + Math.max(height(n.left), height(n.right));
            temp.height = 1 + Math.max(height(temp.left), height(temp.right));
        }
        return remove(k, n);
    }*/

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    /*private Node remove(K x, Node t )
    {
        if( t != null )
        {
            int compareResult = x.compareTo( t.key );

            if( compareResult < 0 )
                t.left = remove( x, t.left );
            else if( compareResult > 0 )
                t.right = remove( x, t.right );
            else
            {
                // match -- one child or leaf?
                if ( t.left == null ) return t.right;
                if ( t.right ==  null) return t.left;

                // Match found, two children
                if( t.left.priority < t.right.priority ) {
                    t = rotateWithLeftChild( t );
                    t.right = remove (x, t.right);
                } else {
                    t = rotateWithRightChild( t );
                    t.left = remove( x, t.left);
                }
            }
        }
        return t;
    }*/

    /**
     * Rotate binary tree node with left child.
     */
    private Node rotateWithLeftChild(Node k2 )
    {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private Node rotateWithRightChild(Node k1 )
    {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
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