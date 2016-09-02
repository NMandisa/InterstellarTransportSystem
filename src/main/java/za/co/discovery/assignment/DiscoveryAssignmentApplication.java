package za.co.discovery.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.dto.Edge;
import za.co.discovery.assignment.service.PlanetNodeService;
import za.co.discovery.assignment.service.RouteService;
import za.co.discovery.assignment.service.impl.DefaultDetermineShortestPathService;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class DiscoveryAssignmentApplication {

	@Autowired
	RouteService routeService;

	@Autowired
	PlanetNodeService planetNodeService;


	public static void main(String[] args) throws Exception{
		SpringApplication.run(DiscoveryAssignmentApplication.class, args);

	}

	@PostConstruct
	public void insertInstance() throws Exception{
		planetNodeService.generateAllPlanetNodes();
		routeService.generateAllRoute();
	}

}

