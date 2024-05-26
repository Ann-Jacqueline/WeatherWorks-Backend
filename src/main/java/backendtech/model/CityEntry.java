package backendtech.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
public class CityEntry {
    private String name;
    private int temperatur;
    private String wetterStatus;


}

