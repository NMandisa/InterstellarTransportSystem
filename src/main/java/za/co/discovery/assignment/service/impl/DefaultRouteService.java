package za.co.discovery.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.repository.RouteRepository;
import za.co.discovery.assignment.service.RouteService;

import java.util.Iterator;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
@Service
public class DefaultRouteService implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    DefaultInsertDBServiceService defaultInsertDBService;


    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route findRoute(Long routeId){
        return routeRepository.findOne(routeId);

    }

    @Override
    public Route saveRoute(Route route){
        return routeRepository.save(route);
    }

    @Override
    public void generateAllRoute() throws Exception   {
        List<Route> saveRouteList = defaultInsertDBService.retrieveDBEntityRoute();
            Iterator<Route> iter = saveRouteList.iterator();
            while(iter.hasNext())
            {
                Route route = iter.next();
                routeRepository.save(route);
            }
    }

    @Override
    public  Route updateRoute(Long routeId,String planetOrigin,String planetDestination,Double distance){
        Route route;
        try {
            route = routeRepository.findOne(routeId);
            if(route != null){route.setPlanetOrigin(planetOrigin);
                route.setPlanetDestination(planetDestination);
                route.setDistance(distance);
                routeRepository.save(route);
                return route;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public void deleteRoute(Long routeId){
        try {
            Route route;
            route = routeRepository.findOne(routeId);
            if (route != null) routeRepository.delete(route);
        }catch ( Exception routeEx){
            routeEx.getStackTrace();
        }

    }
}
