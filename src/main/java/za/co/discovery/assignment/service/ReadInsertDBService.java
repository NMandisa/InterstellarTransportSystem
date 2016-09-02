package za.co.discovery.assignment.service;

import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
public interface ReadInsertDBService {

    List<PlanetNode> retrieveDBEntityPlanetNode() throws Exception;

    List<Route> retrieveDBEntityRoute() throws Exception;

}
