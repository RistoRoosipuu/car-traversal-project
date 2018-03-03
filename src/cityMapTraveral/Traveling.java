package cityMapTraveral;

import cityMap.CityMapGraph;
import cityMap.Vertex;
import enviromentController.EnvironmentController;
import serviceStation.ServiceStation;
import vehicle.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Traveling implements Runnable {

    private Car car;
    private CityMapGraph graph;
    private EnvironmentController controller;
    private Random random = new Random();
    private int reachedIntersection = 0;
    private ServiceStation station;

    public Traveling(Car car, CityMapGraph graph, EnvironmentController controller, ServiceStation station) {
        this.car = car;
        this.graph = graph;
        this.controller = controller;
        this.station = station;
    }


    @Override
    public void run() {
        //Moved to local variables
        int MIN_TIME = 3;
        int MAX_TIME = 20;
        final int FIVE = 5;
        final int SEVEN = 7;

        controller.registerCar(this.car);
        while (!Thread.interrupted()) {

            synchronized (this) {
                List<Vertex> possibleMoves = new ArrayList<>(graph.getAdjVertices(this.car.getLocation()));

                possibleMoves.removeAll(station.getServiceStationsBusy());

                if (!possibleMoves.isEmpty()) {
                    this.car.setLocation(possibleMoves.get(random.nextInt(possibleMoves.size())));


                    this.reachedIntersection++;
                    car.carTraveledToNewLocation();


                    try {

                        if (this.car.willTheCarEnterTheWorkshop()) {

                            station.carHasUsedTheServiceStation(this.car);
                            station.addStationToBusy(this.car.getLocation());
                            System.out.println("Car with the ID: " + this.car.getCarNumber() + " at " + this.car.getLocation() + " is now in a Service Station " +
                                    " Size of busy stations: " + station.getServiceStationsBusy());
                            this.wait(FIVE + (MIN_TIME + random.nextInt(MAX_TIME)));
                            System.out.println("Car: " + this.car.getCarNumber() + " left station");
                            station.removeStationFromBusy(this.car.getLocation());
                        } else {

                            this.wait((MIN_TIME + random.nextInt(MAX_TIME)));
                        }


                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (reachedIntersection == FIVE) {

                        controller.informController(this.car);
                    }

                    if (reachedIntersection == SEVEN) {

                        try {
                            if (!controller.askControllerForPermissionToDrive(this.car)) {

                                controller.addCarToWaitingList(this.car);

                                synchronized (this.car) {
                                    this.car.wait();
                                }

                                controller.removeCarFromWaitingList(this.car);

                                this.reachedIntersection = 0;


                            } else {
                                this.reachedIntersection = 0;
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                    }

                }
            }

        }
    }

    @Override
    public String toString() {
        return "Traveling ToString" + " Car id:" + car.getCarNumber() + " Location: " + car.getLocation() + " Streets passed: " + car.getNumberOfStreetsPassed();
    }
}
