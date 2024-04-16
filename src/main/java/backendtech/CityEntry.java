package backendtech;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLängengrad() {
        return längengrad;
    }

    public void setLängengrad(double längengrad) {
        this.längengrad = längengrad;
    }

    public double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}