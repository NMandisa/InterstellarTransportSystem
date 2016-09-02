package za.co.discovery.assignment.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

/**
 * Created by RSS on 8/19/2016.
 */
@JsonAutoDetect
public class PlanetNodeDTO implements Serializable{

    private String name;
    private Double distance;

    public PlanetNodeDTO(String name, Double distance) {
        this.name = name;
        this.distance = distance;
    }

    public PlanetNodeDTO(String name) {
        this.name = name;
        this.distance = 0.0;
    }

    public PlanetNodeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
