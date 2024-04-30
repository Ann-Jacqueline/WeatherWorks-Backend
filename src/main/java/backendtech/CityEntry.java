package backendtech;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityEntry {
    private String name;
    private double längengrad;
    private double breitengrad;
    private boolean isDeleted;

    public CityEntry(String name, double längengrad, double breitengrad, boolean isDeleted) {
        this.name = name;
        this.längengrad = längengrad;
        this.breitengrad = breitengrad;
        this.isDeleted = isDeleted;
    }
}

