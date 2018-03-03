package streets;

public class Street {

    private String streetName;

    public Street(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "StreetName " + streetName;
    }
}
