package serviceStation;

import cityMap.Vertex;
import vehicle.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceStation {

    private Set<Car> allServicedCars = new HashSet<>();
    private List<Vertex> serviceStationsBusy = new ArrayList<>();

    public synchronized void addStationToBusy(Vertex serviceLocation) {
        serviceStationsBusy.add(serviceLocation);

    }

    public synchronized void removeStationFromBusy(Vertex serviceLocation) {
        serviceStationsBusy.remove(serviceLocation);

    }

    public synchronized void carHasUsedTheServiceStation(Car car) {
        allServicedCars.add(car);
    }

    public synchronized List<Vertex> getServiceStationsBusy() {
        return serviceStationsBusy;
    }

    public synchronized Set<Car> getAllServicedCars() {
        return allServicedCars;
    }

    public synchronized List<Car> findSpecificInformation(Predicate<Car> specificQuery) {
        return allServicedCars.stream().filter(specificQuery).collect(Collectors.toList());

    }
}
