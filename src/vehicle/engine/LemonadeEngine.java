package vehicle.engine;

public class LemonadeEngine implements Engine {
    private String engineType = "LE";

    @Override
    public String engineType() {
        return engineType;
    }

    @Override
    public String engineDescription() {
        return "Lemonade Engine";
    }
}
