package za.co.discovery.assignment.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RSS on 8/11/2016.
 */

@Entity
public class Route implements Serializable {

    @Id
    private Long routeId;
    private String planetOrigin;
    private String planetDestination;
    private Double distance;


    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Double routeId) {
        this.routeId = routeId.longValue();
    }

    public String getPlanetOrigin() {
        return planetOrigin;
    }

    public void setPlanetOrigin(String planetOrigin) {
        this.planetOrigin = planetOrigin;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getPlanetDestination() {
        return planetDestination;
    }

    public void setPlanetDestination(String planetDestination) {
        this.planetDestination = planetDestination;
    }

}
