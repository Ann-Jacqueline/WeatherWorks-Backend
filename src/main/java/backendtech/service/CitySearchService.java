package backendtech.service;

import backendtech.model.CitySearch;
import backendtech.persistence.CitySearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitySearchService {

    @Autowired
    private CitySearchRepository repository;

    public Optional<CitySearch> getCityEntry(Long id) {
        return this.repository.findById(id);
    }

    public Iterable<CitySearch> getCityEntries() {
        return this.repository.findAll();
    }

    public CitySearch addCityEntry(final CitySearch citySearch) {
        return repository.save(citySearch);
    }


    public boolean removeCityEntry(final Long id) {
        final boolean exists = repository.existsById(id);
        if (exists) repository.deleteById(id);
        return exists;
    }
}