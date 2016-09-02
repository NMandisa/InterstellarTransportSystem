package za.co.discovery.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.discovery.assignment.domain.Route;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
public interface RouteRepository extends CrudRepository<Route,Long> {
    @Override
    List<Route> findAll();

    @Override
    Route findOne(Long userId);

    List<Route> findByPlanetOrigin(String planetOrigin);
}
