package backendtech.service;
import backendtech.model.CityEntry;
import backendtech.model.CityEntryWithId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class CityEntryService {
    private final HashMap<Long, CityEntryWithId> data = new HashMap<>() {{
        put(1L, new CityEntryWithId("Berlin", 26, "Sonnig",1L));
        put(2L, new CityEntryWithId("Paris", 15, "Wolkig", 2L));
        put(3L, new CityEntryWithId("Madrid", 5, "Regen", 3L));
    }};
    private long currentId = data.size() + 1;

    public CityEntryWithId getCityEntry(Long id) {
        return data.get(id);
    }

    public List<CityEntryWithId> getCityEntrys() {
        return data.values().stream().toList();
    }


    public CityEntryWithId addCityEntry(final CityEntry CityEntry) {
        final long id = currentId++;
        final CityEntryWithId cityEntryWithId = new CityEntryWithId(CityEntry.getName(), CityEntry.getTemperatur(), CityEntry.getWetterStatus(), id);
        data.put(id, cityEntryWithId);
        return cityEntryWithId;
    }
    public boolean removeCityEntry(final Long id) {
        return data.remove(id) != null;
    }

}