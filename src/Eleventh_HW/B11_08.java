package Eleventh_HW;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B11_08 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву міста англійською (наприклад, Kyiv): ");
        String city = scanner.nextLine().trim();
        String urlString = "https://www.meteoprog.com/ua/weather/" + city + "/";
        System.out.println("Запит до: " + urlString);
        System.out.println("Отримання даних...");
        String htmlContent = fetchHtml(urlString);
        if (htmlContent != null && !htmlContent.isEmpty()) {
            System.out.println("\n------------------------------------------------");
            System.out.println("Поточна дата: " + LocalDate.now());
            System.out.println("Прогноз погоди у місті " + city + " (вибірка значень):");
            System.out.println("------------------------------------------------");

            List<String> temps = extractData(htmlContent, "([+-]?\\d+)°", 8);
            List<String> humidity = extractData(htmlContent, "(\\d+)%", 8);

            System.out.println("Температура (наступні 8 значень): " + temps);
            System.out.println("Вологість   (наступні 8 значень): " + humidity);
        } else {
            System.out.println("Не вдалося отримати дані. Перевірте назву міста.");
        }
    }

    private static String fetchHtml(String urlString) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("Помилка з'єднання: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

    private static List<String> extractData(String html, String regex, int limit) {
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find() && results.size() < limit) {
            String foundValue = matcher.group(1);

            if (regex.contains("°")) {
                results.add(foundValue + "°");
            } else {
                results.add(foundValue + "%");
            }
        }
        return results;
    }
}
