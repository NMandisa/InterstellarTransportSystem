package za.co.discovery.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.dto.Edge;
import za.co.discovery.assignment.repository.PlanetNodeRepository;
import za.co.discovery.assignment.repository.RouteRepository;
import za.co.discovery.assignment.service.PlanetNodeService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
@Service
public class DefaultPlanetNodeService implements PlanetNodeService {


    @Autowired
    PlanetNodeRepository planetNodeRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    DefaultInsertDBServiceService defaultInsertDBService;

    @Autowired
    DefaultDetermineShortestPathService defaultDetermineShortestPathService;



    @Override
    public List<PlanetNode> getAllPlanetNode() {
        return planetNodeRepository.findAll();
    }

    @Override
    public PlanetNode savePlanetNode(PlanetNode planetNode) {
        return planetNodeRepository.save(planetNode);
    }

    @Override
    public void generateAllPlanetNodes() throws Exception {
        List<PlanetNode> retrievePlanetNodes = defaultInsertDBService.retrieveDBEntityPlanetNode();//Retrieve All Data Stored in Planet Excel Sheet
        Iterator<PlanetNode> itera = retrievePlanetNodes.iterator();
        while (itera.hasNext()) {
            PlanetNode node = itera.next();
            if (node.getPlanetNode() != null && node != null) {//Check if the Node Object is not null
                List<Route> routes = defaultInsertDBService.retrieveDBEntityRoute();//Retrieve all available routes
                Iterator<Route> routeIter = routes.iterator();
                List<Route> routesToAdd = new ArrayList<>();//Store the found routes
                while (routeIter.hasNext()) {
                    Route tempRoute = routeIter.next();//Temporary Route Object
                    if (node.getPlanetNode().equalsIgnoreCase(tempRoute.getPlanetOrigin())) {//Check the relationship of both route and planet node
                        routesToAdd.add(tempRoute);//Adds the found node matching
                    }
                    node.setConnections(routesToAdd);//Set All the connected arc
                    planetNodeRepository.save(node);//save the planet node with appropriate data
                }
            }

        }
    }


    @Override
    public PlanetNode updatePlanetNode(Long planetNodeId, String planetName, String PlanetNode) {
        PlanetNode planetNode;
        try {
            planetNode = planetNodeRepository.findOne(planetNodeId);
            if (planetNode != null) {
                planetNode.setPlanetName(planetName);
                planetNode.setPlanetNode(PlanetNode);
                planetNodeRepository.save(planetNode);
                return planetNode;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public List<Route> findRoutesByPlanetOrigin(String planetOrigin) {
        return routeRepository.findByPlanetOrigin(planetOrigin);
    }

    @Override
    public void deletePlanetNode(Long planetNodeId) {
        PlanetNode planetNode;
        try {
            planetNode = planetNodeRepository.findOne(planetNodeId);
            if (planetNode != null) {
                planetNodeRepository.delete(planetNode);
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public Edge[] createGraph() {
        final Edge[] GRAPH = new Edge[45];
        List<PlanetNode> retrievePlanetNodes = getAllPlanetNode();//Retrieve All Data Stored in Planet Excel Sheet
        Iterator<PlanetNode> planetNodeIterator = retrievePlanetNodes.iterator();
        int i = 0;// initializing index i for navigating true the array
        while (planetNodeIterator.hasNext()){
            PlanetNode node = planetNodeIterator.next();
            if (node != null) {//Check if the Node from planet Object is not null
                List<Route> routesByPlanetOrigin = findRoutesByPlanetOrigin(node.getPlanetNode());
                Iterator<Route> routesByPlanetOriginIterator = routesByPlanetOrigin.iterator();
                while (routesByPlanetOriginIterator.hasNext()) {

                    Route tempRoute = routesByPlanetOriginIterator.next();//Temporary Route Object
                    if (node.getPlanetNode().equalsIgnoreCase(tempRoute.getPlanetOrigin())) {//Check the relationship of both route and planet node
                        GRAPH[i] = new Edge(tempRoute.getPlanetOrigin(), tempRoute.getPlanetDestination(), tempRoute.getDistance());
                    }
                    i++;
                }
            }
        }
        defaultDetermineShortestPathService.setGraph(GRAPH);//Creating the graph with all possible connections
        return GRAPH;
    }

    @Override
    public  PlanetNode findPlanetNode(Long planetNodeId){
        return planetNodeRepository.findOne(planetNodeId);
    }



}
