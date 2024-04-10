package backendtech;

public class CityEntry {
    private String city;
    private String country;
    private String description;
    private int temperature;
    public CityEntry (String city, String country, String description, int temperature) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.temperature = temperature;
    }
    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public int getTemperature() {
        return temperature;
    }
}

