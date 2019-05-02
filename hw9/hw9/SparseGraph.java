package hw9;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;

import java.util.ArrayList;
import java.util.List;

/**
    An implementation of a directed graph using incidence lists
    for sparse graphs where most things aren't connected.
    @param <V> Vertex element type.
    @param <E> Edge element type.
*/
public class SparseGraph<V, E> implements Graph<V, E> {

    // Class for a vertex of type V
    private final class VertexNode<V> implements Vertex<V>,
            Comparable<VertexNode<V>> {
        V data;
        Graph<V, E> owner;
        List<Edge<E>> outgoing;
        List<Edge<E>> incoming;
        Object label;
        double dist;

        VertexNode(V v) {
            this.data = v;
            this.outgoing = new ArrayList<>();
            this.incoming = new ArrayList<>();
            this.label = null;
        }

        @Override
        public V get() {
            return this.data;
        }

        @Override
        public void put(V v) {
            this.data = v;
        }

        @Override
        public int compareTo(VertexNode<V> v) {
            if (dist > v.dist) {
                return 1;
            }
            else if (dist < v.dist) {
                return -1;
            }
            return 0;
        }

    }

    //Class for an edge of type E
    private final class EdgeNode<E> implements Edge<E> {
        E data;
        Graph<V, E> owner;
        VertexNode<V> from;
        VertexNode<V> to;
        Object label;

        // Constructor for a new edge
        EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
            this.from = f;
            this.to = t;
            this.data = e;
            this.label = null;
        }

        @Override
        public E get() {
            return this.data;
        }

        @Override
        public void put(E e) {
            this.data = e;
        }
    }

    private List<Vertex<V>> vertices;
    private List<Edge<E>> edges;

    /** Constructor for instantiating a graph. */
    public SparseGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    // Checks vertex belongs to this graph
    private void checkOwner(VertexNode<V> toTest) {
        if (toTest.owner != this) {
            throw new PositionException();
        }
    }

    // Checks edge belongs to this graph
    private void checkOwner(EdgeNode<E> toTest) {
        if (toTest.owner != this) {
            throw new PositionException();
        }
    }

    public void setDist(Vertex<V> v, double dist) {
        convert(v).dist = dist;
    }

    public double getDist(Vertex<V> v) {
        if (v == null) {
            throw new PositionException();
        }
        return convert(v).dist;
    }


    // Converts the vertex back to a VertexNode to use internally
    private VertexNode<V> convert(Vertex<V> v) throws PositionException {
        try {
            VertexNode<V> gv = (VertexNode<V>) v;
            this.checkOwner(gv);
            return gv;
        } catch (ClassCastException ex) {
            throw new PositionException();
        }
    }

    // Converts and edge back to a EdgeNode to use internally
    private EdgeNode<E> convert(Edge<E> e) throws PositionException {
        try {
            EdgeNode<E> ge = (EdgeNode<E>) e;
            this.checkOwner(ge);
            return ge;
        } catch (ClassCastException ex) {
            throw new PositionException();
        }
    }

    @Override
    public Vertex<V> insert(V v) {
        // TODO
        VertexNode<V> vert = new VertexNode<V>(v);
        vert.owner = this;
        Vertex<V> vertex = (Vertex<V>) vert;
        this.vertices.add(vertex);
        return vertex;
    }

    @Override
    public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
            throws PositionException, InsertionException {
        // TODO
        if (from == to) { // self loop
            throw new InsertionException();
        }
        for (Edge<E> edge : this.edges) {
            if (this.convert(edge).from == from
                    && this.convert(edge).to == to) {
                throw new InsertionException();
            }
        }
        if (from == null || to == null || !this.vertices.contains(from)
                || !this.vertices.contains(to)) {
            // either to/from null or not contained in graph
            throw new PositionException();
        }
        EdgeNode<E> edge = new EdgeNode<E>(this.convert(from),
                this.convert(to), e);
        edge.owner = this;
        Edge<E> ed = (Edge<E>) edge;
        this.edges.add(ed);
        this.convert(from).outgoing.add(ed);
        this.convert(to).outgoing.add(ed);
        return ed;
    }

    @Override
    public V remove(Vertex<V> v) throws PositionException,
            RemovalException {
        // TODO
        if (v == null || !this.vertices.contains(v)) { // v null/ not graphed
            throw new PositionException();
        }
        if (!this.convert(v).outgoing.isEmpty() ||
                !this.convert(v).outgoing.isEmpty()) { // incident edges
            throw new RemovalException();
        }
        V val = this.convert(v).get();
        this.vertices.remove(v);
        return val;
    }

    @Override
    public E remove(Edge<E> e) throws PositionException {
        // TODO
        if (e == null || !this.edges.contains(e)) { // e null or not graphed
            throw new PositionException();
        }
        E element = e.get();
        this.convert(e).from.outgoing.remove(e);
        this.convert(e).to.incoming.remove(e);
        this.edges.remove(e);
        return element;
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        // TODO
        return this.vertices;
    }

    @Override
    public Iterable<Edge<E>> edges() {
        // TODO
        return this.edges;
    }

    @Override
    public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
        // TODO
        if (v == null || !this.vertices.contains(v)) { // v null/ not graphed
            throw new PositionException();
        }
        return this.convert(v).outgoing;
    }

    @Override
    public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
        // TODO
        if (v == null || !this.vertices.contains(v)) { // v null/ not graphed
            throw new PositionException();
        }
        return this.convert(v).incoming;
    }

    @Override
    public Vertex<V> from(Edge<E> e) throws PositionException {
        // TODO
        if (e == null || !this.edges.contains(e)) { // e null or not graphed
            throw new PositionException();
        }
        return this.convert(e).from;
    }

    @Override
    public Vertex<V> to(Edge<E> e) throws PositionException {
        // TODO
        if (e == null || !this.edges.contains(e)) { // e null or not graphed
            throw new PositionException();
        }
        return this.convert(e).to;
    }

    @Override
    public void label(Vertex<V> v, Object l) throws PositionException {
        // TODO
        if (v == null || !this.vertices.contains(v)) { // v null/ not graphed
            throw new PositionException();
        }
        this.convert(v).label = l;
    }

    @Override
    public void label(Edge<E> e, Object l) throws PositionException {
        // TODO
        if (e == null || !this.edges.contains(e)) { // e null or not graphed
            throw new PositionException();
        }
        this.convert(e).label = l;
    }

    @Override
    public Object label(Vertex<V> v) throws PositionException {
        // TODO
        if (v == null || !this.vertices.contains(v)) { // v null/ not graphed
            throw new PositionException();
        }
        return this.convert(v).label;
    }

    @Override
    public Object label(Edge<E> e) throws PositionException {
        // TODO
        if (e == null || !this.edges.contains(e)) { // e null or not graphed
            throw new PositionException();
        }
        return this.convert(e).label;
    }

    @Override
    public void clearLabels() {
        // TODO
        for (Vertex<V> vert : this.vertices) {
            this.convert(vert).label = null;
        }
        for (Edge<E> edge : this.edges) {
            this.convert(edge).label = null;
        }
    }

    private String vertexString(Vertex<V> v) {
        return "\"" + v.get() + "\"";
    }

    private String verticesToString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<V> v : this.vertices) {
            sb.append("  ").append(vertexString(v)).append("\n");
        }
        return sb.toString();
    }

    private String edgeString(Edge<E> e) {
        return String.format("%s -> %s [label=\"%s\"]",
                this.vertexString(this.from(e)),
                this.vertexString(this.to(e)),
                e.get());
    }

    private String edgesToString() {
        String edgs = "";
        for (Edge<E> e : this.edges) {
            edgs += "    " + this.edgeString(e) + ";\n";
        }
        return edgs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n")
                .append(this.verticesToString())
                .append(this.edgesToString())
                .append("}");
        return sb.toString();
    }
}
