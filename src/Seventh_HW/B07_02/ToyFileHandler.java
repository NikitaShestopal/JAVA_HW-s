package Seventh_HW.B07_02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToyFileHandler {
    public static void createToyFile(String filename, List<Toy> toys) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Toy toy : toys) {
                oos.writeObject(toy);
            }
            System.out.println("Файл іграшок " + filename + " успішно створено.");
        } catch (IOException e) {
            System.err.println("Помилка при записі файлу іграшок: " + e.getMessage());
        }
    }

    public static List<Toy> readToyFile(String filename) {
        List<Toy> toys = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Toy toy = (Toy) ois.readObject();
                    toys.add(toy);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("Помилка: Клас Toy не знайдено при десеріалізації.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Файл іграшок не знайдено: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні файлу іграшок: " + e.getMessage());
        }
        return toys;
    }

    public static void filterToysByAgeAndSave(String inputFile, String outputFile, int childAge) {
        List<Toy> allToys = readToyFile(inputFile);

        List<Toy> filteredToys = new ArrayList<>();

        for (Toy toy : allToys) {
            if (toy.isSuitableForAge(childAge)) {
                filteredToys.add(toy);
            }
        }

        System.out.println("\nЗнайдено " + filteredToys.size() + " іграшок для віку " + childAge + ".");

        createToyFile(outputFile, filteredToys);
    }

    public static void main(String[] args) {
        List<Toy> toysList = List.of(
                new Toy("М'яч", 150.0, 3, 10),
                new Toy("Лялька", 450.5, 4, 8),
                new Toy("Конструктор LEGO", 1200.0, 6, 99),
                new Toy("Брязкальце", 80.0, 0, 2),
                new Toy("Настільна гра", 700.0, 8, 14)
        );

        String allToysFile = "toys.dat";
        String filteredToysFile = "filtered_toys.dat";
        int childAge = 7;

        // a) Створюємо файл з усіма іграшками
        createToyFile(allToysFile, toysList);

        // b) Зчитуємо файл (для перевірки)
        List<Toy> readToys = readToyFile(allToysFile);
        System.out.println("\nВсі іграшки у файлі " + allToysFile + ":");
        readToys.forEach(System.out::println);

        filterToysByAgeAndSave(allToysFile, filteredToysFile, childAge);

        List<Toy> filteredList = readToyFile(filteredToysFile);
        System.out.println("\nВідфільтровані іграшки у файлі " + filteredToysFile + ":");
        filteredList.forEach(System.out::println);
    }
}
