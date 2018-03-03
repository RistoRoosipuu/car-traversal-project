package vehicle.engine;

public class ElectricEngine implements Engine {
    private String engineType = "EE";

    @Override
    public String engineType() {
        return engineType;
    }

    @Override
    public String engineDescription() {
        return "Electric Engine";
    }
}
