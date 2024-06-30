package backendtech.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CityHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean setAsDefault;
    private String cityName;
    private int temperature;
    private String localTime;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private CityHistoryOwner owner;

    public CityHistory(boolean setAsDefault, String cityName, int temperature, String localTime, boolean deleted, CityHistoryOwner owner) {
        this.setAsDefault = setAsDefault;
        this.cityName = cityName;
        this.temperature = temperature;
        this.localTime = localTime;
        this.deleted = deleted;
        this.owner = owner;
    }


}

