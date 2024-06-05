package backendtech.persistence;

import backendtech.model.CityEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityEntryRepository extends CrudRepository<CityEntry, Long> {
}
