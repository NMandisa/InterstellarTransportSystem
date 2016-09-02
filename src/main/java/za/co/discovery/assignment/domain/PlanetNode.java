package za.co.discovery.assignment.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
@Entity
public class PlanetNode implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planetNodeId;

    private String planetName;
    private String PlanetNode;

    @OneToMany(targetEntity = Route.class, cascade =CascadeType.ALL)
    List<Route> connections;


    public Long getPlanetNodeId() {
        return planetNodeId;
    }

    public void setPlanetNodeId(Long planetNodeId) {
        this.planetNodeId = planetNodeId;
    }

    public List<Route> getConnections() {
        return connections;
    }

    public void setConnections(List<Route> connections) {
        this.connections = connections;
    }

    public String getPlanetNode() {
        return PlanetNode;
    }

    public void setPlanetNode(String planetNode) {
        PlanetNode = planetNode;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }


}