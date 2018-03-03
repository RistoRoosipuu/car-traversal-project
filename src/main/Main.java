package main;

import cityMap.CityMapGraph;
import cityMap.Vertex;
import cityMapTraveral.Traveling;
import enviromentController.EnvironmentController;
import serviceStation.ServiceStation;
import vehicle.Car;
import vehicle.engine.DieselEngine;
import vehicle.engine.ElectricEngine;
import vehicle.engine.GasEngine;
import vehicle.engine.LemonadeEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    /**
     * Risto Roosipuu 2017
     *
     * @param args
     */
    public static void main(String[] args) {
        CityMapGraph graph = new CityMapGraph();
        Random random = new Random();

        ExecutorService executorService = Executors.newCachedThreadPool();
        EnvironmentController controller = new EnvironmentController();
        //Streams out all Possible Entrances from the graph
        List<Vertex> allEntrances = graph.getVertices().stream().filter(i -> i.getEntryPoint().isCarsCanEnter()).collect(Collectors.toList());
        List<Traveling> travelingList = new ArrayList<>();
        ServiceStation station = new ServiceStation();
        /**
         * Create all Car Objects within the Runnable Traveling Objects.
         * Then we can just execute all the Runnable objects within the list
         */
        for (int i = 0; i < 200; i++) {
            if (i < 80) {
                Car car = new Car(i, allEntrances.get(random.nextInt(allEntrances.size())), new DieselEngine());
                travelingList.add(new Traveling(car, graph, controller, station));
            } else if (i < 170) {
                Car car = new Car(i, allEntrances.get(random.nextInt(allEntrances.size())), new GasEngine());
                travelingList.add(new Traveling(car, graph, controller, station));
            } else if (i < 180) {
                Car car = new Car(i, allEntrances.get(random.nextInt(allEntrances.size())), new LemonadeEngine());
                travelingList.add(new Traveling(car, graph, controller, station));
            } else {
                Car car = new Car(i, allEntrances.get(random.nextInt(allEntrances.size())), new ElectricEngine());
                travelingList.add(new Traveling(car, graph, controller, station));
            }
        }

        for (Traveling t : travelingList) {
            executorService.submit(t);
        }


        executorService.shutdown();

        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (executorService.isShutdown()) {
            System.out.println("Registered cars: " + controller.getAllRegisteredCars().size());
            System.out.println("Cars who used service station:  " + station.getAllServicedCars().size());
            System.out.println(station.findSpecificInformation(i -> i.isDieselEngine()));
            controller.shutdownTwoSecondDelay();
        }


    }
}
