package backendtech.service;

import backendtech.model.CityHistory;
import backendtech.persistence.CityHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityHistoryService {
    @Autowired
    private CityHistoryRepository repository;

    public Optional<CityHistory> getCityHistory(Long id) {
        return this.repository.findById(id);
    }

    public Iterable<CityHistory> getCityHistories() {
        return this.repository.findAll();
    }

    public CityHistory addCityHistory(final CityHistory cityHistory) {
        return repository.save(cityHistory);
    }

    public boolean removeCityHistory(final Long id) {
        final boolean exists = repository.existsById(id);
        if (exists) repository.deleteById(id);
        return exists;
    }
}
