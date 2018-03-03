package vehicle.engine;

public class DieselEngine implements Engine {

    private String engineType = "DE";

    @Override
    public String engineType() {
        return engineType;
    }

    @Override
    public String engineDescription() {
        return "Diesel Engine";
    }
}
