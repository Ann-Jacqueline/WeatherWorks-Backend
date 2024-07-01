package backendtech.persistence;

import backendtech.model.CityHistoryOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository für den Zugriff auf die CityHistoryOwner-Datenbanktabelle.
 * Diese Schnittstelle ermöglicht CRUD-Operationen und benutzerdefinierte Abfragen
 * für CityHistoryOwner-Entitäten.
 */
@Repository
public interface CityHistoryOwnerRepository extends CrudRepository<CityHistoryOwner, Long> {
    /**
     * Sucht einen CityHistoryOwner anhand des Benutzernamens.
     *
     * @param userName Der Benutzername des CityHistoryOwner.
     * @return Ein Optional, das den gefundenen CityHistoryOwner enthält, oder leer ist, wenn keiner gefunden wurde.
     */
    Optional<CityHistoryOwner> findByUserName(String userName);
}
