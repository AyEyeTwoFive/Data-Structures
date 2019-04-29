package hw9.tests;

import hw9.Graph;
import hw9.Vertex;
import hw9.Edge;
import org.junit.Before;
import org.junit.Test;
import exceptions.PositionException;
import exceptions.InsertionException;
import exceptions.RemovalException;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public abstract class GraphTest {

    protected Graph<String, String> graph;

    protected abstract Graph<String, String> createGraph();

    @Before
    public void setupGraph() {
        this.graph = createGraph();
    }

    // TODO - Add tests

    @Test
    public void insertTwoVertsOneEdge() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(v1, graph.from(e));
        assertEquals(v2, graph.to(e));
        //assertEquals(e, graph.outgoing(v1));
    }

    @Test(expected=PositionException.class)
    public void insertEdgeNullTo() {
        Vertex<String> v1 = graph.insert("A");
        Edge<String> e = graph.insert(v1,null,"1");
    }


    @Test(expected=PositionException.class)
    public void insertEdgeNullFrom() {
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(null,v2,"1");
    }

    @Test(expected=PositionException.class)
    public void insertEdgeFromNotInGraph() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph2.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
    }

    @Test(expected=PositionException.class)
    public void insertEdgeToNotInGraph() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v1 = graph2.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
    }

    @Test(expected=InsertionException.class)
    public void insertEdgeSelfLoop() {
        Vertex<String> v1 = graph.insert("A");
        Edge<String> e = graph.insert(v1,v1,"1");
    }

    @Test(expected=InsertionException.class)
    public void insertDuplicateEdge() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e1 = graph.insert(v1,v2,"1");
        Edge<String> e2 = graph.insert(v1,v2,"2");
    }

    @Test()
    public void removeVertex() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Vertex<String> v3 = graph.insert("C");
        assertEquals(new ArrayList<Vertex<String>>(Arrays.asList(v1, v2, v3)), graph.vertices());
        assertEquals("B", graph.remove(v2));
        assertEquals(new ArrayList<Vertex<String>>(Arrays.asList(v1, v3)), graph.vertices());
    }

    @Test(expected=PositionException.class)
    public void removeNullVertex() {
        Vertex<String> v1 = graph.insert("A");
        assertEquals("B", graph.remove((Vertex<String>) null));
    }

    @Test(expected=PositionException.class)
    public void removeVertexNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph2.insert("B");
        assertEquals("B", graph.remove(v2));
    }

    @Test(expected=RemovalException.class)
    public void removeVertexWithEdges() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals("B", graph.remove(v2));
    }

    @Test
    public void removeEdge() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e)), graph.edges());
        assertEquals("1", graph.remove(e));
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList()), graph.edges());
    }

    @Test(expected=PositionException.class)
    public void removeEdgeNull() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        assertEquals("1", graph.remove((Edge<String>) null));
    }

    @Test(expected=PositionException.class)
    public void removeEdgeNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        assertEquals("1", graph.remove(e));
    }

    @Test()
    public void vertices() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Vertex<String> v3 = graph.insert("C");
        assertEquals(new ArrayList<Vertex<String>>(Arrays.asList(v1, v2, v3)), graph.vertices());
    }

    @Test()
    public void edges() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Vertex<String> v3 = graph.insert("C");
        Edge<String> e1 = graph.insert(v1,v2,"1");
        Edge<String> e2 = graph.insert(v1,v3,"2");
        Edge<String> e3 = graph.insert(v2,v3,"3");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e1, e2, e3)), graph.edges());
    }

    @Test(expected=PositionException.class)
    public void outgoingNullVertex() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e)), graph.outgoing(null));
    }

    @Test(expected=PositionException.class)
    public void outgoingVertexNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph2.insert(v1,v2,"1");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e)), graph.outgoing(v1));
    }

    @Test()
    public void outgoing() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Vertex<String> v3 = graph.insert("C");
        Edge<String> e1 = graph.insert(v1,v2,"1");
        Edge<String> e2 = graph.insert(v1,v3,"2");
        Edge<String> e3 = graph.insert(v2,v3,"3");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e1, e2)), graph.outgoing(v1));
    }

    @Test(expected=PositionException.class)
    public void incomingNullVertex() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e)), graph.incoming(null));
    }

    @Test(expected=PositionException.class)
    public void incomingVertexNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph2.insert(v1,v2,"1");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e)), graph.outgoing(v2));
    }

    @Test()
    public void incoming() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Vertex<String> v3 = graph.insert("C");
        Edge<String> e1 = graph.insert(v1,v2,"1");
        Edge<String> e2 = graph.insert(v1,v3,"2");
        Edge<String> e3 = graph.insert(v2,v3,"3");
        assertEquals(new ArrayList<Edge<String>>(Arrays.asList(e2, e3)), graph.outgoing(v3));
    }

    @Test(expected=PositionException.class)
    public void fromNullEdge() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(v1, graph.from(null));
    }

    @Test(expected=PositionException.class)
    public void fromEdgeNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        assertEquals(v11, graph.from(e));
    }

    @Test
    public void from() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(v1, graph.from(e));
    }

    @Test(expected=PositionException.class)
    public void toNullEdge() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(v2, graph.to(null));
    }

    @Test(expected=PositionException.class)
    public void toEdgeNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        assertEquals(v12, graph.to(e));
    }

    @Test
    public void to() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        assertEquals(v2, graph.to(e));
    }

    @Test
    public void vertexLabelSetandGet() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        graph.label(v1, "Hi");
        assertEquals("Hi", graph.label(v1));
    }

    @Test(expected=PositionException.class)
    public void vertexSetLabelNull() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        graph.label((Vertex<String>) null, "Hi");
        assertEquals("Hi", graph.label(v1));
    }

    @Test(expected=PositionException.class)
    public void vertexSetLabelNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        graph.label(v21, "Hi");
    }

    @Test(expected=PositionException.class)
    public void vertexGetLabelNull() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        graph.label(v1, "Hi");
        assertEquals("Hi", graph.label((Vertex<String>) null));
    }

    @Test(expected=PositionException.class)
    public void vertexGetLabelNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        graph2.label(v21, "Hi");
        assertEquals("Hi", graph.label(v21));
    }

    @Test
    public void edgeLabelSetandGet() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        graph.label(e, "Hi");
        assertEquals("Hi", graph.label(e));
    }

    @Test(expected=PositionException.class)
    public void edgeSetLabelNull() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        graph.label((Edge<String>) null, "Hi");
        assertEquals("Hi", graph.label(v1));
    }

    @Test(expected=PositionException.class)
    public void edgeSetLabelNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        graph.label(e, "Hi");
    }

    @Test(expected=PositionException.class)
    public void edgeGetLabelNull() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Edge<String> e = graph.insert(v1,v2,"1");
        graph.label(e, "Hi");
        assertEquals("Hi", graph.label((Edge<String>) null));
    }

    @Test(expected=PositionException.class)
    public void edgeGetLabelNotGraphed() {
        Graph<String, String> graph2 = createGraph();
        Vertex<String> v11 = graph.insert("A");
        Vertex<String> v12 = graph.insert("B");
        Vertex<String> v21 = graph2.insert("A");
        Vertex<String> v22 = graph2.insert("B");
        Edge<String> e = graph2.insert(v21,v22,"1");
        graph2.label(e, "Hi");
        assertEquals("Hi", graph.label(e));
    }

    @Test
    public void clearLabels() {
        Vertex<String> v1 = graph.insert("A");
        Vertex<String> v2 = graph.insert("B");
        Vertex<String> v3 = graph.insert("C");
        Edge<String> e1 = graph.insert(v1,v2,"1");
        Edge<String> e2 = graph.insert(v1,v3,"2");
        Edge<String> e3 = graph.insert(v2,v3,"3");
        graph.label(v1, "Hi");
        graph.label(v2, "Yo");
        graph.label(v3, "At");
        graph.label(e1, "To");
        graph.label(e2, "Am");
        graph.label(e3, "Go");
        assertEquals("Hi", graph.label(v1));
        assertEquals("Yo", graph.label(v2));
        assertEquals("At", graph.label(v3));
        assertEquals("To", graph.label(e1));
        assertEquals("Am", graph.label(e2));
        assertEquals("Go", graph.label(e3));
        graph.clearLabels();
        assertEquals(null, graph.label(v1));
        assertEquals(null, graph.label(v2));
        assertEquals(null, graph.label(v3));
        assertEquals(null, graph.label(e1));
        assertEquals(null, graph.label(e2));
        assertEquals(null, graph.label(e3));
    }

}
