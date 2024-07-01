package backendtech.service;

import backendtech.model.CityHistory;
import backendtech.persistence.CityHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service-Klasse für die Verwaltung von CityHistory-Entitäten.
 * Diese Klasse bietet Methoden zum Abrufen, Hinzufügen und Verwalten von CityHistory-Objekten.
 */
@Service
public class CityHistoryService {
    @Autowired
    private CityHistoryRepository repository;

    /**
     * Ruft eine CityHistory anhand der ID ab.
     *
     * @param id Die ID der CityHistory.
     * @return Ein Optional, das die gefundene CityHistory enthält, oder leer ist, wenn keine gefunden wurde.
     */
    public Optional<CityHistory> getCityHistory(Long id) {
        return this.repository.findById(id);
    }

    /**
     * Ruft alle CityHistory-Einträge aus der Datenbank ab.
     *
     * @return Ein Iterable mit allen CityHistory-Objekten.
     */
    public Iterable<CityHistory> getCityHistories() {
        return this.repository.findAll();
    }

    /**
     * Fügt eine neue CityHistory zur Datenbank hinzu.
     *
     * @param cityHistory Die CityHistory, die hinzugefügt werden soll.
     * @return Die hinzugefügte CityHistory.
     */
    public CityHistory addCityHistory(final CityHistory cityHistory) {
        return repository.save(cityHistory);
    }

    /**
     * Entfernt eine CityHistory anhand der ID aus der Datenbank.
     *
     * @param id Die ID der CityHistory, die entfernt werden soll.
     * @return true, wenn die CityHistory erfolgreich entfernt wurde, false, wenn sie nicht gefunden wurde.
     */
    public boolean removeCityHistory(final Long id) {
        final boolean exists = repository.existsById(id);
        if (exists) repository.deleteById(id);
        return exists;
    }
}
