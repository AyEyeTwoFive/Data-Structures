package hw9;

import exceptions.InsertionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Search for the shortest path between two endpoints using
 * Djikstra's. We use a HashMap to store all the vertices so we can
 * find them by name (i.e. their coordinates) when inserting for a
 * fast duplicates check.
 * 
 * Vertex data is the coordinates, stored as a String.
 * Vertex label is the Edge into it on the path found.
 * Edge data is the road name, stored as a String.
 * Edge label is the road length, stored as a Double.
 *
 */
public final class StreetSearcher {
    
    // useful for marking distance to nodes, or use Double.POSITIVE_INFINITY
    private static final double MAX_DISTANCE = 1e18;

    // Global variables, bleh
    private static Map<String, Vertex<String>> vertices = new HashMap<>();
    private static SparseGraph<String, String> graph = new SparseGraph<>();

    // Silencing checkstyle
    private StreetSearcher() {}


    // Get the path by tracing labels back from end to start.
    private static List<Edge<String>> getPath(Vertex<String> end,
                                              Vertex<String> start) {
        if (graph.label(end) != null) {
            List<Edge<String>> path = new ArrayList<>();

            Vertex<String> cur = end;
            Edge<String> road;
            while (cur != start) {
                road = (Edge<String>) graph.label(cur);  // unchecked cast ok
                path.add(road);
                cur = graph.from(road);
            }
            return path;
        }
        return null;
    }


    // Print the path found.
    private static void printPath(List<Edge<String>> path,
                                  double totalDistance) {
        if (path == null) {
            System.out.println("No path found");
            return;
        }

        System.out.println("Total Distance: " + totalDistance);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).get() + " "
                               + graph.label(path.get(i)));
        }
    }

    // Djikstra's Algorithm to find shortest path.
    private static void findShortestPath(String startName, String endName) {
        Vertex<String> start = vertices.get(startName);
        Vertex<String> end = vertices.get(endName);

        double totalDist = -1;

        // TODO - write this!
        /* 
           The biggest issue here is how to create and maintain an
           adaptable priority queue somehow. You do not have to write
           the most efficient implementation possible. It does need to
           be correct. You will need to keep track of distances for
           vertices. Feel free to update the SparseGraph
           implementation to handle that for you. Another option is to
           create a nested class here for comparable objects that have
           vertex info and distance info to use in a standard priority
           queue.
        */
        for (Vertex<String> v : vertices.values()) {
            graph.setDist(v, MAX_DISTANCE);
            graph.label(v, null);
        }
        graph.setDist(start, 0);
        graph.label(start, null);

        PriorityQueue<Vertex<String>> pathfind = new PriorityQueue<>();

        for (Vertex<String> v : vertices.values()) {
            pathfind.add(v);
        }

        while (!pathfind.isEmpty()) {
            Vertex<String> u = pathfind.peek();
            pathfind.remove();
            Iterable<Edge<String>> out = graph.outgoing(u);
            Iterator<Edge<String>> it = out.iterator();
            while (it.hasNext()) {
                Edge<String> e = it.next();
                Vertex<String> nb = graph.to(e);
                double uDist = graph.getDist(u);
                double nbDist = graph.getDist(nb);
                double edgeDist = (Double) graph.label(e);
                if ((uDist + edgeDist) < nbDist) {
                    pathfind.remove(nb);
                    graph.setDist(nb, uDist + edgeDist);
                    graph.label(nb, e);
                    pathfind.add(nb);
                }
                if (end.equals(nb)) {
                    totalDist = graph.getDist(nb);
                    break;
                }
            }

        }

        if (totalDist == -1) {
            System.err.println("No Path Found.");
            return;
        }

        // These method calls will create and print the path for you
        List<Edge<String>> path = getPath(end, start);
        printPath(path, totalDist);
    }


    // Add an endpoint to the network if it is a new endpoint
    private static Vertex<String> addLocation(String name) {
        if (!vertices.containsKey(name)) {
            Vertex<String> v = graph.insert(name);
            vertices.put(name, v);
            return v;
        }
        return vertices.get(name);
    }


    // Load network from fileName, returns number of roads
    private static int loadNetwork(String fileName)
            throws FileNotFoundException {

        int numRoads = 0;

        // Read in from file fileName
        Scanner input = new Scanner(new FileInputStream(new File(fileName)));
        while (input.hasNext()) {

            // Parse the line in to <end1> <end2> <road-distance> <road-name>
            String[] tokens = input.nextLine().split(" ");
            String fromName = tokens[0];
            String toName = tokens[1];
            double roadDistance = Double.parseDouble(tokens[2]);
            String roadName = tokens[3];

            // Get the from and to endpoints, adding if necessary
            Vertex<String> from = addLocation(fromName);
            Vertex<String> to =  addLocation(toName);

            // Add the road to the network - We assume all roads are two-way and
            // ignore if we've already added the road as a reverse of another
            try {

                Edge<String> road = graph.insert(from, to, roadName);
                Edge<String> backwardsRoad = graph.insert(to, from, roadName);
                numRoads += 2;

                // Label each road with it's weight
                graph.label(road, roadDistance);
                graph.label(backwardsRoad, roadDistance);

            } catch (InsertionException ignored) {
                // Nothing to do.
            }
        }

        return numRoads;
    }

    private static void checkValidEndpoint(String endpointName) {
        if (!vertices.containsKey(endpointName)) {
            throw new IllegalArgumentException(endpointName);
        }
    }

    /**
     * Main method.
     * @param args See usage.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: " +
                    "StreetSearcher <map_name> <start_coords> <end_coords>");
            return;
        }

        String fileName  = args[0];
        String startName = args[1];
        String endName   = args[2];

        try {

            int numRoads = loadNetwork(fileName);
            System.out.println("Network Loaded!");
            System.out.println("Loaded " + numRoads + " roads");
            System.out.println("Loaded " + vertices.size() + " endpoints");

            checkValidEndpoint(startName);
            checkValidEndpoint(endName);

        } catch (FileNotFoundException e) {
            System.err.println("Could not find file " + fileName);
            return;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Endpoint: " + e.getMessage());
            return;
        }

        findShortestPath(startName, endName);
    }
}
