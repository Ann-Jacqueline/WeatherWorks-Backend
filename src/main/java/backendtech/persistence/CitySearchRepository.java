package backendtech.persistence;

import backendtech.model.CitySearch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository für den Zugriff auf die CitySearch-Datenbanktabelle.
 * Diese Schnittstelle ermöglicht CRUD-Operationen für CitySearch-Entitäten.
 */
@Repository
public interface CitySearchRepository extends CrudRepository<CitySearch, Long> {
}
