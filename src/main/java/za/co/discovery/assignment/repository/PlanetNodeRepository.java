package za.co.discovery.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.discovery.assignment.domain.PlanetNode;
import za.co.discovery.assignment.domain.Route;

import java.util.List;

/**
 * Created by NMandisa Mkhungo on 8/11/2016.
 */
public interface PlanetNodeRepository extends CrudRepository<PlanetNode,Long> {

    @Override
    List<PlanetNode> findAll();

    @Override
    PlanetNode findOne(Long planetNodeId);


}
