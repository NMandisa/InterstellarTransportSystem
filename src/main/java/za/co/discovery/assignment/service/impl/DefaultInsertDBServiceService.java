package za.co.discovery.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;
import za.co.discovery.assignment.readwrite.ExcelDataParser;
import za.co.discovery.assignment.service.ReadInsertDBService;

import java.util.List;


@Service
public class DefaultInsertDBServiceService implements ReadInsertDBService {

    @Autowired
    private ExcelDataParser excelDataParser;

    @Override
    public List<PlanetNode> retrieveDBEntityPlanetNode() throws Exception {
        return excelDataParser.readPlanetNode();
    }
    @Override
    public List<Route> retrieveDBEntityRoute() throws Exception {
        return excelDataParser.readRoute();
    }

}
