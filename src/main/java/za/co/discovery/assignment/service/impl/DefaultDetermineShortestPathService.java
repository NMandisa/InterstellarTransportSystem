package za.co.discovery.assignment.service.impl;

import org.springframework.stereotype.Service;
import za.co.discovery.assignment.dto.Edge;
import za.co.discovery.assignment.dto.PlanetNodeDTO;
import za.co.discovery.assignment.dto.Vertex;
import za.co.discovery.assignment.service.DetermineShortestPathService;

import java.util.*;

/**
 * Created by NMandisa Mkhungo on 8/15/2016.
 */
@Service
public class DefaultDetermineShortestPathService implements DetermineShortestPathService {

    private  Map<String, Vertex> graph;

    //find distance to multiple nodes at the same time
     //
    // mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * Builds a graph from a set of edges
     */
    public void setGraph(Edge[] edges) {

        graph = new HashMap<>(edges.length);

        //one pass to find all vertices
        for (Edge e : edges) {
            if (!graph.containsKey(e.arc1)) graph.put(e.arc1, new Vertex(e.arc1));
            if (!graph.containsKey(e.arc2)) graph.put(e.arc2, new Vertex(e.arc2));
        }

        //another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.arc1).neighbours.put(graph.get(e.arc2), e.distance);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    public Map<String, Vertex> getGraph() {
        return graph;
    }

    /**
     * Runs dijkstra using a specified source vertex
     */
    @Override
    public void shortestAvailablePath(String startName) {
        if (!graph.containsKey(startName)) {
            System.out.print("Graph doesn't contain start vertex "+ startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> nodeQueue = new TreeSet<>();

        // set-up vertices
        for (Vertex vertex : graph.values()) {
            vertex.previous = vertex == source ? source : null;
            vertex.distance = vertex == source ? 0 : Double.MAX_VALUE;
            nodeQueue.add(vertex);
        }
        shortestPathSorting(nodeQueue);
    }

    /**
     * Implementation of dijkstra's algorithm using a binary heap to find the shortest distance.
     */
    private void shortestPathSorting(final NavigableSet<Vertex> nodeQueue) {
        Vertex solvedVertex, unsolvedVertex;
        while (!nodeQueue.isEmpty()) {
            solvedVertex = nodeQueue.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (solvedVertex.distance == Double.MAX_VALUE)
                break; // we can ignore u (and any other remaining vertices) since they are unreachable
            //look at distances to each neighbour
            for (Map.Entry<Vertex, Double> a : solvedVertex.neighbours.entrySet()) {
                unsolvedVertex = a.getKey(); //the neighbour in this iteration
                final double alternateDistance = solvedVertex.distance + a.getValue();
                if (alternateDistance < unsolvedVertex.distance) { // shorter path to neighbour found
                    nodeQueue.remove(unsolvedVertex);
                    unsolvedVertex.distance = alternateDistance;
                    unsolvedVertex.previous = solvedVertex;
                    nodeQueue.add(unsolvedVertex);
                }
            }
        }
    }

    /**
     * Return a path from the source to the specified vertex
     */
    @Override
    public List<PlanetNodeDTO> shortestPath(String endName) {
        List<PlanetNodeDTO> nodesDistance=new ArrayList<>();
        if (!graph.containsKey(endName)) {
            System.out.print("Graph doesn't contain end vertex "+ endName);
        }

        return graph.get(endName).outputShortestPath(nodesDistance);
    }

    /**
     * Return the path from the source to every vertex (output order is not guaranteed)
     */
    @Override
    public List<PlanetNodeDTO> allPaths() {
        List<PlanetNodeDTO> nodesDistance=new ArrayList<>();
        for (Vertex v : graph.values()) {
            v.outputShortestPath(nodesDistance);
            v.outputShortestPath(nodesDistance);
            System.out.println();
        }
        return nodesDistance;
    }


}