package za.co.discovery.assignment.service;

import za.co.discovery.assignment.domain.Route;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
public interface RouteService {

    List<Route> getAllRoute();

    Route saveRoute(Route route);

    Route findRoute(Long routeId);

    void generateAllRoute()throws Exception;

    Route updateRoute(Long routeId,String planetOrigin,String planetDestination,Double distance);

    void deleteRoute(Long routeId);
}
