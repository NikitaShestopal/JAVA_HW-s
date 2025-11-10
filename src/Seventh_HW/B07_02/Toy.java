package Seventh_HW.B07_02;

import java.io.Serializable;
public class Toy implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private double price;
    private int minAge;
    private int maxAge;

    public Toy(String name, double price, int minAge, int maxAge) {
        this.name = name;
        this.price = price;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public boolean isSuitableForAge(int age) {
        return age >= this.minAge && age <= this.maxAge;
    }

    @Override
    public String toString() {
        return "Іграшка {" +
                "назва='" + name + '\'' +
                ", ціна=" + price +
                ", вік=" + minAge + "-" + maxAge +
                '}';
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getMinAge() { return minAge; }
    public int getMaxAge() { return maxAge; }
}