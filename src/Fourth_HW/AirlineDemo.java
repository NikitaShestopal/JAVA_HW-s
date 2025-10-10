package Fourth_HW;

import java.util.List;

public class AirlineDemo {
    public static void main(String[] args) {
        Airline airline = new Airline("Aurora Airways");

        airline.addAircraft(new PassengerAircraft("Boeing 737-800", 162, 20000, 5600, 2400, 2));
        airline.addAircraft(new PassengerAircraft("Airbus A320", 150, 18000, 6100, 2200, 2));
        airline.addAircraft(new CargoAircraft("Boeing 747-8F", 4, 137000, 8000, 12000, 850.0));
        airline.addAircraft(new MixedAircraft("Antonov An-124", 10, 150000, 4800, 14000));
        airline.addAircraft(new PassengerAircraft("Embraer 190", 100, 9000, 4000, 900, 1));

        System.out.println("Авіакомпанія: " + airline.getName());
        System.out.println("Ієрархія (класи і їх кількість):");
        airline.getTypeCounts().forEach((cls, cnt) -> System.out.println("  " + cls + " : " + cnt));
        System.out.println();

        System.out.println("Загальна пасажиромісткість: " + airline.totalPassengerCapacity() + " місць");
        System.out.println("Загальна вантажопідйомність: " + airline.totalPayloadKg() + " кг");
        System.out.println();

        System.out.println("Флот, відсортований за дальністю польоту (зростання):");
        airline.getFleetSortedByRangeAsc().forEach(a -> System.out.println("  " + a));
        System.out.println();

        double minFuel = 800;
        double maxFuel = 2500;
        System.out.printf("Літаки зі споживанням пального в інтервалі [%.0f, %.0f]:\n", minFuel, maxFuel);
        List<Aircraft> found = airline.findByFuelConsumption(minFuel, maxFuel);
        if (found.isEmpty()) {
            System.out.println("  Немає відповідних літаків.");
        } else {
            found.forEach(a -> System.out.println("  " + a));
        }
    }
}
