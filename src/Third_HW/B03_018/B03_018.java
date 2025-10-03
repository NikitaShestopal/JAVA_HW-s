package Third_HW.B03_018;

import java.util.*;

class Bus {
    private String driverName;
    private int busNumber;
    private int routeNumber;
    private int startYear;

    public Bus(String driverName, int busNumber, int routeNumber, int startYear) {
        this.driverName = driverName;
        this.busNumber = busNumber;
        this.routeNumber = routeNumber;
        this.startYear = startYear;
    }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public int getBusNumber() { return busNumber; }
    public void setBusNumber(int busNumber) { this.busNumber = busNumber; }

    public int getRouteNumber() { return routeNumber; }
    public void setRouteNumber(int routeNumber) { this.routeNumber = routeNumber; }

    public int getStartYear() { return startYear; }
    public void setStartYear(int startYear) { this.startYear = startYear; }

    public String toString() {
        return "Bus{" +
                "Driver='" + driverName + '\'' +
                ", BusNumber=" + busNumber +
                ", RouteNumber=" + routeNumber +
                ", StartYear=" + startYear +
                '}';
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Bus)) return false;
        Bus other = (Bus) obj;
        return this.busNumber == other.busNumber &&
                this.routeNumber == other.routeNumber &&
                this.startYear == other.startYear &&
                this.driverName.equals(other.driverName);
    }

    // a) Повертає автобуси для заданого маршруту відсортовані за роком експлуатації
    public static List<Bus> getBusesByRouteSorted(List<Bus> buses, int routeNumber) {
        List<Bus> result = new ArrayList<>();
        for (Bus b : buses) {
            if (b.getRouteNumber() == routeNumber) {
                result.add(b);
            }
        }
        result.sort(Comparator.comparingInt(Bus::getStartYear));
        return result;
    }

    // b) Повертає автобуси відсортовані за номером автобуса
    public static List<Bus> sortByBusNumber(List<Bus> buses) {
        List<Bus> sorted = new ArrayList<>(buses);
        sorted.sort(Comparator.comparingInt(Bus::getBusNumber));
        return sorted;
    }
}

class Main {
    public static void main(String[] args) {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("Ivan Petrov", 102, 5, 2010));
        buses.add(new Bus("Petro Shevchenko", 56, 5, 2015));
        buses.add(new Bus("Oleh Ivanov", 33, 7, 2012));
        buses.add(new Bus("Serhiy Bondarenko", 78, 7, 2008));
        buses.add(new Bus("Mykola Kravets", 45, 5, 2020));

        System.out.println("=== Автобуси маршруту 5, відсортовані за роком експлуатації ===");
        List<Bus> route5 = Bus.getBusesByRouteSorted(buses, 5);
        for (Bus b : route5) {
            System.out.println(b);
        }

        System.out.println("\n=== Усі автобуси, відсортовані за номером автобуса ===");
        List<Bus> sortedByNumber = Bus.sortByBusNumber(buses);
        for (Bus b : sortedByNumber) {
            System.out.println(b);
        }
    }
}
