package Sixth_HW;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class B06_01 {
    public static void main(String[] args) {
        String inputText = "Сьогодні 01.01.2025, а завтра буде 02.01.2025. " +
                "Будь ласка, впишіть дату сюди: __.__.____. " +
                "Старий запис: 15.10.2023.";

        String regex = "\\d{2}\\.\\d{2}\\.\\d{4}|_{2}\\._{2}\\._{4}";

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String replacement = today.format(formatter);

        String resultText = inputText.replaceAll(regex, replacement);

        System.out.println("--- Початковий текст ---");
        System.out.println(inputText);
        System.out.println("\n--- Текст після заміни ---");
        System.out.println(resultText);
    }
}