package vehicle;

import cityMap.Vertex;
import vehicle.engine.Engine;

import java.util.concurrent.atomic.AtomicInteger;

public class Car {

    private int carNumber;
    private Engine engine;
    private Vertex location;
    private AtomicInteger numberOfStreetsPassed = new AtomicInteger();

    public Car(int carNumber, Vertex location, Engine engine) {
        this.carNumber = carNumber;
        this.engine = engine;
        this.location = location;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public Engine getEngine() {
        return engine;
    }

    public Vertex getLocation() {
        return location;
    }


    public void carTraveledToNewLocation() {
        numberOfStreetsPassed.incrementAndGet();
    }

    public void setLocation(Vertex location) {
        this.location = location;
    }


    public AtomicInteger getNumberOfStreetsPassed() {
        return numberOfStreetsPassed;
    }

    public boolean isDieselEngine() {
        return engine.engineType().endsWith("DE");
    }

    public boolean isGasEngine() {
        return engine.engineType().endsWith("GE");
    }

    public boolean isElectricEngine() {
        return engine.engineType().endsWith("EE");
    }

    public boolean isLemonadeEngine() {
        return engine.engineType().endsWith("LE");
    }

    public String getCarEngineDescription() {
        return engine.engineDescription();
    }

    @Override
    public String toString() {
        return "Car id: " + this.carNumber + " and its engine is: " + getCarEngineDescription();
    }

    public boolean willTheCarEnterTheWorkshop() {
        return getLocation().getEntryPoint().isAWorkShop();
    }
}
