package backendtech.persistence;
import backendtech.model.CityHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityHistoryRepository extends CrudRepository<CityHistory, Long>{
}
