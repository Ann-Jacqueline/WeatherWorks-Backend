package backendtech.service;

import backendtech.model.CityHistoryOwner;
import backendtech.persistence.CityHistoryOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service-Klasse für die Verwaltung von CityHistoryOwner-Entitäten.
 * Diese Klasse bietet Methoden zum Abrufen, Hinzufügen und Verwalten von CityHistoryOwner-Objekten.
 */
@Service
public class CityHistoryOwnerService {
    @Autowired
    private CityHistoryOwnerRepository repository;

    /**
     * Ruft einen CityHistoryOwner anhand der ID ab.
     *
     * @param id Die ID des CityHistoryOwner.
     * @return Ein Optional, das den gefundenen CityHistoryOwner enthält, oder leer ist, wenn keiner gefunden wurde.
     */
    public Optional<CityHistoryOwner> getCityHistoryOwner(Long id) {
        return this.repository.findById(id);
    }

    /**
     * Ruft alle CityHistoryOwner aus der Datenbank ab.
     *
     * @return Ein Iterable mit allen CityHistoryOwner-Objekten.
     */
    public Iterable<CityHistoryOwner> getCityHistoryOwners() {
        return this.repository.findAll();
    }

    /**
     * Fügt einen neuen CityHistoryOwner zur Datenbank hinzu.
     *
     * @param cityHistoryOwner Der CityHistoryOwner, der hinzugefügt werden soll.
     * @return Der hinzugefügte CityHistoryOwner.
     */
    public CityHistoryOwner addCityHistoryOwner(final CityHistoryOwner cityHistoryOwner) {
        return repository.save(cityHistoryOwner);
    }

    /**
     * Ruft alle Benutzer (CityHistoryOwner) aus der Datenbank ab.
     *
     * @return Eine Liste mit allen CityHistoryOwner-Objekten.
     */
    public List<CityHistoryOwner> getAllUsers() {
        return (List<CityHistoryOwner>) this.repository.findAll();
    }

    /**
     * Ruft den aktuellen Benutzer anhand des Benutzernamens ab.
     *
     * @param userName Der Benutzername des CityHistoryOwner.
     * @return Ein Optional, das den gefundenen CityHistoryOwner enthält, oder leer ist, wenn keiner gefunden wurde.
     */
    public Optional<CityHistoryOwner> getCurrentUser(String userName) {
        return this.repository.findByUserName(userName);
    }
}
