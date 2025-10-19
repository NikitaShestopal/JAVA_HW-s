package Fifth_HW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class B05_10 {
    public record Fruit(String type, String variety, String country) {
        public static Fruit fromCsvLine(String line) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                return new Fruit(parts[0].trim(), parts[1].trim(), parts[2].trim());
            }
            return null;
        }
        @Override
        public String toString() {
            return type + "," + variety + "," + country;
        }
    }

    public static void main(String[] args) {
        String inputFile = "fruits.txt";
        String outputFileA = "fruits_by_country.txt";
        String outputFileB = "fruit_counts.txt";

        try {
            createTestInputFile(inputFile);
            System.out.println("Створено тестовий файл: " + inputFile);
        } catch (IOException e) {
            System.err.println("Не вдалося створити тестовий файл: " + e.getMessage());
            return;
        }

        try {
            List<Fruit> fruits = readFruitsFromFile(inputFile);
            System.out.println("Успішно зчитано " + fruits.size() + " фруктів.");

            String targetCountry = "Іспанія";

            findFruitsByCountry(fruits, targetCountry, outputFileA);
            System.out.println("Завдання (a) виконано. Результат у файлі: " + outputFileA);

            countFruitsByType(fruits, outputFileB);
            System.out.println("Завдання (b) виконано. Результат у файлі: " + outputFileB);

        } catch (IOException e) {
            System.err.println("Сталася помилка під час обробки файлів: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ЗМІНА: Зчитує файл за допомогою BufferedReader.
     */
    public static List<Fruit> readFruitsFromFile(String filename) throws IOException {
        List<Fruit> fruits = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Fruit fruit = Fruit.fromCsvLine(line);
                if (fruit != null) {
                    fruits.add(fruit);
                }
            }
        }
        return fruits;
    }

    /**
     * Записує результат завдання (a)
     */
    public static void findFruitsByCountry(List<Fruit> fruits, String targetCountry, String outputFilename) throws IOException {
        List<String> resultLines = fruits.stream()
                .filter(fruit -> fruit.country().equalsIgnoreCase(targetCountry))
                .map(Fruit::toString)
                .collect(Collectors.toList());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (String line : resultLines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * Записує результат завдання (b)
     */
    public static void countFruitsByType(List<Fruit> fruits, String outputFilename) throws IOException {
        Map<String, Long> typeCounts = fruits.stream()
                .collect(Collectors.groupingBy(Fruit::type, Collectors.counting()));

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilename))) {

            List<String> sortedTypes = typeCounts.keySet().stream().sorted().toList();

            for (String type : sortedTypes) {
                Long count = typeCounts.get(type);
                writer.printf("%s: %d\n", type, count);
            }
        }
    }

    private static void createTestInputFile(String filename) throws IOException {
        List<String> lines = List.of(
                "Яблуко,Гала,Польща",
                "Банан,Кавендіш,Еквадор",
                "Апельсин,Навел,Іспанія",
                "Яблуко,Голден,Україна",
                "Банан,Бебі,Колумбія",
                "Яблуко,Гала,Україна",
                "Апельсин,Валенсія,Іспанія",
                "Манго,Кент,Перу"
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
}