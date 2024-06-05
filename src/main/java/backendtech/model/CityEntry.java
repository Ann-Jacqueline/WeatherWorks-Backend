package backendtech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class CityEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int temperatur;
    private String wetterStatus;

    public CityEntry(String wetterStatus, int temperatur, String name) {
        this.wetterStatus = wetterStatus;
        this.temperatur = temperatur;
        this.name = name;
    }
}

