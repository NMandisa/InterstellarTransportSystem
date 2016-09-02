package za.co.discovery.assignment.service;


import za.co.discovery.assignment.dto.PlanetNodeDTO;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/15/2016.
 */
public interface DetermineShortestPathService {
    //find distance to multiple nodes at the same time
    void shortestAvailablePath(String startName);

    List<PlanetNodeDTO> shortestPath(String endName);

   List<PlanetNodeDTO> allPaths();



}
