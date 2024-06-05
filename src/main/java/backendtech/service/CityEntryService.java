package backendtech.service;

import backendtech.model.CityEntry;
import backendtech.persistence.CityEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityEntryService {

    @Autowired
    private CityEntryRepository repository;

    public Optional<CityEntry> getCityEntry(Long id) {
        return this.repository.findById(id);
    }

    public Iterable<CityEntry> getCityEntries() {
        return this.repository.findAll();
    }

    public CityEntry addCityEntry(final CityEntry cityEntry) {
        return repository.save(cityEntry);
    }


    public boolean removeCityEntry(final Long id) {
        final boolean exists = repository.existsById(id);
        if (exists) repository.deleteById(id);
        return exists;
    }
}