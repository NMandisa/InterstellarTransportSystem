package za.co.discovery.assignment.dto;

/**
 * Created by RSS on 8/16/2016.
 */
public class Edge {
    /**
     * One edge of the graph (only used by Graph constructor)
     */
    public final String arc1, arc2;
    public final double distance;

    public Edge(String arc1, String arc2, double distance) {
        this.arc1 = arc1;
        this.arc2 = arc2;
        this.distance = distance;
    }
}
