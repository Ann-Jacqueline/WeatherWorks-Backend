package backendtech.service;
import backendtech.model.CityHistoryOwner;
import backendtech.persistence.CityHistoryOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityHistoryOwnerService {
    @Autowired
    private CityHistoryOwnerRepository repository;

    public Optional<CityHistoryOwner> getCityHistoryOwner(Long id) {
        return this.repository.findById(id);
    }

    public Iterable<CityHistoryOwner> getCityHistoryOwners() {
        return this.repository.findAll();
    }

    public CityHistoryOwner addCityHistoryOwner(final CityHistoryOwner cityHistoryOwner) {
        return repository.save(cityHistoryOwner);
    }
    public List<CityHistoryOwner> getAllUsers() {
        return (List<CityHistoryOwner>) this.repository.findAll();
    }

}
