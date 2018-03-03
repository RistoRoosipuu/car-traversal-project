package streets;

public class StreetCrossroad {

    private String streetEntryName;
    private boolean carsCanEnter;
    private boolean carWorkShop;

    public StreetCrossroad(String streetEntryName) {
        this.streetEntryName = streetEntryName;
        this.carsCanEnter = false;
        this.carWorkShop = false;
    }

    public StreetCrossroad(String streetEntryName, boolean carsCanEnter, boolean carWorkShop) {
        this.streetEntryName = streetEntryName;
        this.carsCanEnter = carsCanEnter;
        this.carWorkShop = carWorkShop;
    }

    public boolean isCarsCanEnter() {
        return carsCanEnter;
    }

    public boolean isAWorkShop() {
        return carWorkShop;
    }

    @Override
    public String toString() {
        return "StreetCrossroad " + streetEntryName;
    }
}
