package backendtech.persistence;

import backendtech.model.CitySearch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CitySearchRepository extends CrudRepository<CitySearch, Long> {
}
