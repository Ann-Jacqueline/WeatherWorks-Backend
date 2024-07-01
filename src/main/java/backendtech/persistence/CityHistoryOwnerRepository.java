package backendtech.persistence;

import backendtech.model.CityHistoryOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityHistoryOwnerRepository extends CrudRepository<CityHistoryOwner, Long> {
    Optional<CityHistoryOwner> findByUserName(String userName);
}
