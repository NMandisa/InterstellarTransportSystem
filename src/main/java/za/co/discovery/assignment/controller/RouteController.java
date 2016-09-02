package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.dto.Direct;
import za.co.discovery.assignment.service.PlanetNodeService;
import za.co.discovery.assignment.service.RouteService;
import za.co.discovery.assignment.service.impl.DefaultDetermineShortestPathService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
@CrossOrigin(allowedHeaders = {"*"}, origins = "*")
@RestController
@RequestMapping(value = "/routes")
public class RouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    PlanetNodeService planetNodeService;

    @Autowired
    DefaultDetermineShortestPathService defaultDetermineShortestPathService;


    //Retrieve all the available route store in the database
    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoute();
    }

    //Retrieve one route store in the database
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Object getRoute(@PathVariable("id") Long id) {
        return routeService.findRoute(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Route> save(@RequestBody Route route) throws Exception {
        routeService.saveRoute(route);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //update route return the update route
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Route routeO) {
        Route route = routeService.updateRoute(id, routeO.getPlanetOrigin(), routeO.getPlanetDestination(), routeO.getDistance());
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    //delete route
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            routeService.deleteRoute(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/path", method = RequestMethod.POST, headers = "Accept=application/json")
    public Object shortestPath(@RequestBody Direct direction) {
        Object paths;
        defaultDetermineShortestPathService.setGraph(planetNodeService.createGraph());
        defaultDetermineShortestPathService.shortestAvailablePath(direction.getStart());
        paths=defaultDetermineShortestPathService.shortestPath(direction.getEnd());
        //paths.add(defaultDetermineShortestPathService.allPaths());
        return paths;
    }


}
