package backendtech.model;
import lombok.Getter;
import lombok.Setter;

public class CityEntryWithId extends CityEntry{
    private Long id;

    public CityEntryWithId(String name, int temperatur, String wetterStatus, Long id){
        super(name,temperatur,wetterStatus);
        this.id = id;
    }
}
