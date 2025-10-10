package Fourth_HW;

import java.util.List;

public class AirlineDemo {
    public static void main(String[] args) {
        Airline airline = new Airline("Aurora Airways");

        airline.addAircraft(new PassengerAircraft("Sukhoi Su-24", 2, 8000, 2775, 9500, 2));
        airline.addAircraft(new PassengerAircraft("Sukhoi Su-25", 1, 4400, 2500, 7500, 2));
        airline.addAircraft(new PassengerAircraft("Mikoyan MiG-29", 1, 3500, 2100, 8000, 2));
        airline.addAircraft(new PassengerAircraft("General Dynamics F-16", 1, 7700, 4200, 7000, 1));
        airline.addAircraft(new CargoAircraft("Antonov An-26", 5, 5500, 1100, 1800, 2));
        airline.addAircraft(new PassengerAircraft("Bayraktar TB2", 0, 150, 300, 10, 1));

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
