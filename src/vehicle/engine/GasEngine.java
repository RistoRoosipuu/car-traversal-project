package vehicle.engine;

public class GasEngine implements Engine {
    private String engineType = "GE";

    @Override
    public String engineType() {
        return engineType;
    }

    @Override
    public String engineDescription() {
        return "Gas Engine";
    }
}
