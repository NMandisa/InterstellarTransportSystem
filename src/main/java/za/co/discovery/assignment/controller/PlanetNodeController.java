package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.service.PlanetNodeService;
import za.co.discovery.assignment.service.RouteService;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
@CrossOrigin(allowedHeaders = {"*"}, origins = "*")
@RestController
@RequestMapping(value = "/planetNodes")
public class PlanetNodeController {
    @Autowired
    PlanetNodeService planetNodeService;

    @Autowired
    RouteService routeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PlanetNode> planetNodes() {
        return planetNodeService.getAllPlanetNode();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<PlanetNode> save(@RequestBody PlanetNode planetNode) {
        planetNodeService.savePlanetNode(planetNode);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PlanetNode planetNodeO) {
        try {
            PlanetNode planetNode = planetNodeService.updatePlanetNode(id, planetNodeO.getPlanetName(), planetNodeO.getPlanetNode());
            return new ResponseEntity<>(planetNode, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            planetNodeService.deletePlanetNode(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Retrieve one route store in the database
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Object getRoute(@PathVariable("id") Long id) {
        return planetNodeService.findPlanetNode(id);
    }




}
