package Fourth_HW;

import java.util.*;
import java.util.stream.Collectors;

enum AircraftType {
    PASSENGER,
    CARGO,
    MIXED
}

abstract class Aircraft {
    protected String model;
    protected int passengerCapacity;
    protected double payloadKg;
    protected double rangeKm;
    protected double fuelConsumption;
    protected AircraftType type;

    public Aircraft(String model, int passengerCapacity, double payloadKg,
                    double rangeKm, double fuelConsumption, AircraftType type) {
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.payloadKg = payloadKg;
        this.rangeKm = rangeKm;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
    }

    public String getModel() { return model; }
    public int getPassengerCapacity() { return passengerCapacity; }
    public double getPayloadKg() { return payloadKg; }
    public double getRangeKm() { return rangeKm; }
    public double getFuelConsumption() { return fuelConsumption; }
    public AircraftType getType() { return type; }

    @Override
    public String toString() {
        return String.format("%s [%s] cap=%d pax, payload=%.0f kg, range=%.0f km, fuel=%.2f",
                model, type, passengerCapacity, payloadKg, rangeKm, fuelConsumption);
    }
}

class PassengerAircraft extends Aircraft {
    private int numCabinClasses;

    public PassengerAircraft(String model, int passengerCapacity, double payloadKg,
                             double rangeKm, double fuelConsumption, int numCabinClasses) {
        super(model, passengerCapacity, payloadKg, rangeKm, fuelConsumption, AircraftType.PASSENGER);
        this.numCabinClasses = numCabinClasses;
    }

    public int getNumCabinClasses() { return numCabinClasses; }
}

class CargoAircraft extends Aircraft {
    private double maxVolumeM3;

    public CargoAircraft(String model, int passengerCapacity, double payloadKg,
                         double rangeKm, double fuelConsumption, double maxVolumeM3) {
        super(model, passengerCapacity, payloadKg, rangeKm, fuelConsumption, AircraftType.CARGO);
        this.maxVolumeM3 = maxVolumeM3;
    }

    public double getMaxVolumeM3() { return maxVolumeM3; }
}

class MixedAircraft extends Aircraft {
    public MixedAircraft(String model, int passengerCapacity, double payloadKg,
                         double rangeKm, double fuelConsumption) {
        super(model, passengerCapacity, payloadKg, rangeKm, fuelConsumption, AircraftType.MIXED);
    }
}

class Airline {
    private String name;
    private List<Aircraft> fleet = new ArrayList<>();

    public Airline(String name) {
        this.name = name;
    }

    public void addAircraft(Aircraft a) {
        fleet.add(a);
    }

    public List<Aircraft> getFleet() {
        return Collections.unmodifiableList(fleet);
    }

    public int totalPassengerCapacity() {
        return fleet.stream().mapToInt(Aircraft::getPassengerCapacity).sum();
    }

    public double totalPayloadKg() {
        return fleet.stream().mapToDouble(Aircraft::getPayloadKg).sum();
    }

    public List<Aircraft> getFleetSortedByRangeAsc() {
        return fleet.stream()
                .sorted(Comparator.comparingDouble(Aircraft::getRangeKm))
                .collect(Collectors.toList());
    }

    public List<Aircraft> findByFuelConsumption(double min, double max) {
        return fleet.stream()
                .filter(a -> a.getFuelConsumption() >= min && a.getFuelConsumption() <= max)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getTypeCounts() {
        return fleet.stream()
                .map(a -> a.getClass().getSimpleName())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public String getName() { return name; }
}

