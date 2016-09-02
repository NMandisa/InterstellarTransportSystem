package za.co.discovery.assignment.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RSS on 8/16/2016.
 */
public class Vertex implements Comparable<Vertex> {

    /**
     * One vertex of the graph, complete with mappings to neighbouring vertices
     */

    public final String name;
    public double distance = Double.MAX_VALUE; // MAX_VALUE assumed to be infinity
    public Vertex previous = null;
    public final Map<Vertex, Double> neighbours = new HashMap<>();
    public static String path;

    public Vertex(String name) {
        this.name = name;
    }

    public List<PlanetNodeDTO> outputShortestPath(List<PlanetNodeDTO> nodesDistance) {
        //List<String> possiblePaths = new ArrayList<>();
        if (this == this.previous) {
            System.out.print("Your Starting and Point is " + this.name);
            path = "Your Starting And Ending Point is " + this.name;
            nodesDistance.add(new PlanetNodeDTO(this.name));

           //possiblePaths.add(path);

        } else if (this.previous == null) {
            System.out.print(this.name);
            path = "(unreached)"+this.name;
            nodesDistance.add(new PlanetNodeDTO(this.name));
            //possiblePaths.add(path);

        } else {
            this.previous.outputShortestPath(nodesDistance);
            System.out.print(" -> " + this.name + "(" + this.distance + ")");
            //path += " -> " + this.name + "(" + this.distance + ")";
            nodesDistance.add(new PlanetNodeDTO(this.name, this.distance));
            //possiblePaths.add(path);
        }
        return nodesDistance;
    }

//    public List<NodeDTO> printPath(List<NodeDTO> nodeDTOs) {
//
//        if (this == this.previous) {
//            //System.out.print(" " + this.name);
//            nodeDTOs.add(new NodeDTO(this.name));
//        } else if (this.previous == null) {
//            // System.out.print(" (unreached) " + this.name);
//            nodeDTOs.add(new NodeDTO(this.name));
//        } else {
//            this.previous.printPath(nodeDTOs);
//            // System.out.print(" -> " + this.name + " (" + this.distance + ")");
//            nodeDTOs.add(new NodeDTO(this.name, this.distance));
//        }
//        return nodeDTOs;

    public int compareTo(Vertex other) {
        return Double.compare(distance, other.distance);
    }


}
