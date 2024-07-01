package backendtech.model;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;

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

    private String cityName;
    private String country;
    private int temperature;
    private String localTime;
    private boolean deleted;
    private boolean setAsDefault;



    @JoinColumn(name = "owner_id")
    private String owner;

    public CityHistory(String cityName, String country, int temperature, String localTime, boolean deleted, String owner,boolean setAsDefault) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.localTime = localTime;
        this.deleted = deleted;
        this.owner = owner;
        this.setAsDefault =setAsDefault;
    }


}
