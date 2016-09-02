package za.co.discovery.assignment.service;

import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.dto.Edge;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
public interface PlanetNodeService {

    List<PlanetNode> getAllPlanetNode();

    PlanetNode savePlanetNode(PlanetNode planetNode);

    void generateAllPlanetNodes()throws Exception;

    PlanetNode updatePlanetNode(Long planetNodeId,String planetName,String PlanetNode);

    List<Route> findRoutesByPlanetOrigin(String planetOrigin);

    PlanetNode findPlanetNode(Long planetNodeId);

    void deletePlanetNode(Long planetNodeId);

    Edge[] createGraph();


}
