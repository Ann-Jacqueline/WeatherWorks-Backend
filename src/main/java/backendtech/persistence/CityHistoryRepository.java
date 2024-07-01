package backendtech.persistence;

import backendtech.model.CityHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository für den Zugriff auf die CityHistory-Datenbanktabelle.
 * Diese Schnittstelle ermöglicht CRUD-Operationen für CityHistory-Entitäten.
 */
@Repository
public interface CityHistoryRepository extends CrudRepository<CityHistory, Long> {
}
