package enviromentController;

import vehicle.Car;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnvironmentController {

    private BigDecimal airConditionLevel;
    private BigDecimal airConditionLevelDuringBan;
    private List<Car> parkedCars = new ArrayList<>();
    private List<Car> allRegisteredCars = new ArrayList<>();
    private ScheduledExecutorService twoSecondDelayExecutor = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean canTimerRun = true;
    private BigDecimal FORTY_PERCENT = BigDecimal.valueOf(40);
    private BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private final int FIVE_STREETS = 5;
    private final int TWO_STREETS = 2;

    public EnvironmentController() {
        this.airConditionLevel = BigDecimal.ZERO;
    }

    public synchronized void registerCar(Car car) {
        System.out.println(car + " is about to enter the city");
        allRegisteredCars.add(car);
    }

    public synchronized void informController(Car car) {
        System.out.println(car.getCarNumber() + " is still traveling in the city with the engine type of: " + car.getCarEngineDescription() + ".This car has changed locations: " + car.getNumberOfStreetsPassed() + " times");
        increaseAirConditionLevel(FIVE_STREETS, car);

    }

    public synchronized boolean askControllerForPermissionToDrive(Car car) {
        increaseAirConditionLevel(TWO_STREETS, car);

        boolean drivePermission;
        drivePermission = canThisCarStillDrive(car);

        if (!drivePermission) {
            checkIfYouCanStartDelayTimer();
            return drivePermission;
        } else {
            return drivePermission;
        }
    }

    private synchronized void checkIfParkedCarsCanAwaken() {

        for (Car car : parkedCars) {
            if ((car.isGasEngine() && airConditionLevel.compareTo(BigDecimal.valueOf(500)) < 0) ||
                    (car.isDieselEngine() && airConditionLevel.compareTo(BigDecimal.valueOf(400)) < 0)) {

                synchronized (car) {
                    car.notifyAll();
                }
            }
        }


        /**
         * Awakening with streams;
         if(parkedCars.size() > 0 && airConditionLevel.compareTo(BigDecimal.valueOf(500)) < 0){
         parkedCars.stream().filter(Car::isGasEngine).forEach(car -> { synchronized(car) { car.notifyAll(); } });
         }

         if(parkedCars.size() > 0 && airConditionLevel.compareTo(BigDecimal.valueOf(400)) < 0){
         parkedCars.stream().filter(Car::isDieselEngine).forEach(car -> { synchronized(car) { car.notifyAll(); } });
         }
         **/
    }

    public synchronized void checkIfYouCanStartDelayTimer() {
        if (canTimerRun) {
            startThreadAndWaitTwoSeconds();
        }

    }

    private synchronized void increaseAirConditionLevel(int numberOfStreetsPassed, Car car) {
        if (car.isDieselEngine()) {
            int airPollution = numberOfStreetsPassed * 3;
            airConditionLevel = airConditionLevel.add(BigDecimal.valueOf(airPollution));
        } else if (car.isGasEngine()) {
            int airPollution = numberOfStreetsPassed * 2;
            airConditionLevel = airConditionLevel.add(BigDecimal.valueOf(airPollution));
        } else if (car.isLemonadeEngine()) {
            double airPollution = numberOfStreetsPassed * 0.5;
            airConditionLevel = airConditionLevel.add(BigDecimal.valueOf(airPollution));
        } else {
            double airPollution = numberOfStreetsPassed * 0.1;
            airConditionLevel = airConditionLevel.add(BigDecimal.valueOf(airPollution));
        }
    }

    public synchronized boolean canThisCarStillDrive(Car car) {
        if (car.isDieselEngine() && greaterThanFourHundred()) {
            return false;
        } else return !car.isGasEngine() || !greaterThanFiveHundred();

    }

    private synchronized void startThreadAndWaitTwoSeconds() {
        if (canTimerRun) {
            canTimerRun = false;
            airConditionLevelDuringBan = airConditionLevel;
            System.out.println("Before 2 second delay: " + airConditionLevelDuringBan);
            twoSecondDelayExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Two seconds are over, configuring airPollution levels");
                    configureAirPollutionBan();
                    canTimerRun = true;
                }
            }, 2, TimeUnit.SECONDS);

        }

    }

    private synchronized boolean greaterThanFiveHundred() {
        return airConditionLevel.compareTo(BigDecimal.valueOf(500)) > 0;
    }

    private synchronized boolean greaterThanFourHundred() {
        return airConditionLevel.compareTo(BigDecimal.valueOf(400)) > 0;
    }

    public synchronized void addCarToWaitingList(Car car) {
        parkedCars.add(car);

    }

    public synchronized void removeCarFromWaitingList(Car car) {
        parkedCars.remove(car);

    }

    public synchronized List<Car> getParkedCars() {
        return parkedCars;
    }


    private synchronized void configureAirPollutionBan() {
        synchronized (parkedCars) {
            if (parkedCars.size() < 70) {
                airPollutionLowCarCount();
            } else {
                airPollutionBanHighCarCount();
            }
        }

    }


    private synchronized void airPollutionLowCarCount() {
        airConditionLevel = BigDecimal.ZERO;
        System.out.println("AIR CONDITION RESET TO ZERO");
        checkIfParkedCarsCanAwaken();
    }


    private synchronized void airPollutionBanHighCarCount() {

        BigDecimal fortyPercentAirCondition = airConditionLevelDuringBan.multiply(FORTY_PERCENT).divide(HUNDRED);
        airConditionLevel = fortyPercentAirCondition.setScale(2, RoundingMode.CEILING);
        System.out.println("AIR CONDITION " + airConditionLevelDuringBan + " SET TO 40% " + fortyPercentAirCondition);
        checkIfParkedCarsCanAwaken();
    }

    public synchronized List<Car> getAllRegisteredCars() {
        return allRegisteredCars;
    }

    public synchronized void shutdownTwoSecondDelay() {
        twoSecondDelayExecutor.shutdown();
    }
}
