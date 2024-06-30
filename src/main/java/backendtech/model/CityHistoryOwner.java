package backendtech.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Entity
/**
 *
 * Besitzer/ User einer spezifischen CityHistory und defaultSearch
 */

public class CityHistoryOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    public CityHistoryOwner(String userName) {
        this.userName = userName;
    }


    /**
     *cityhistoryOwner Attribute:
     * username: String
     */
}
