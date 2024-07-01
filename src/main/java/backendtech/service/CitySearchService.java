package backendtech.service;

import backendtech.model.CitySearch;
import backendtech.persistence.CitySearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service-Klasse für die Verwaltung von CitySearch-Entitäten.
 * Diese Klasse bietet Methoden zum Abrufen, Hinzufügen und Verwalten von CitySearch-Objekten.
 */
@Service
public class CitySearchService {

    @Autowired
    private CitySearchRepository repository;

    /**
     * Ruft einen CitySearch-Eintrag anhand der ID ab.
     *
     * @param id Die ID des CitySearch-Eintrags.
     * @return Ein Optional, das den gefundenen CitySearch-Eintrag enthält, oder leer ist, wenn keiner gefunden wurde.
     */
    public Optional<CitySearch> getCityEntry(Long id) {
        return this.repository.findById(id);
    }

    /**
     * Ruft alle CitySearch-Einträge aus der Datenbank ab.
     *
     * @return Ein Iterable mit allen CitySearch-Objekten.
     */
    public Iterable<CitySearch> getCityEntries() {
        return this.repository.findAll();
    }

    /**
     * Fügt einen neuen CitySearch-Eintrag zur Datenbank hinzu.
     *
     * @param citySearch Der CitySearch-Eintrag, der hinzugefügt werden soll.
     * @return Der hinzugefügte CitySearch-Eintrag.
     */
    public CitySearch addCityEntry(final CitySearch citySearch) {
        return repository.save(citySearch);
    }

    /**
     * Entfernt einen CitySearch-Eintrag anhand der ID aus der Datenbank.
     *
     * @param id Die ID des CitySearch-Eintrags, der entfernt werden soll.
     * @return true, wenn der CitySearch-Eintrag erfolgreich entfernt wurde, false, wenn er nicht gefunden wurde.
     */
    public boolean removeCityEntry(final Long id) {
        final boolean exists = repository.existsById(id);
        if (exists) repository.deleteById(id);
        return exists;
    }
}
