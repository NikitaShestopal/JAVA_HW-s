package Eleventh_HW;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B11_04 {
    public static void main(String[] args) {
        try {
            String urlString = "https://time.is/Kyiv";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                String html = content.toString();
                Pattern pattern = Pattern.compile("<time id=\"clock\">(\\d{2}:\\d{2}:\\d{2})</time>");
                Matcher matcher = pattern.matcher(html);

                if (matcher.find()) {
                    String siteTimeString = matcher.group(1);

                    LocalTime siteTime = LocalTime.parse(siteTimeString);
                    LocalTime localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

                    System.out.println("Час на сайті (Kyiv): " + siteTime);
                    System.out.println("Локальний час ПК:    " + localTime);
                    System.out.println("------------------------------");

                    if (siteTime.equals(localTime)) {
                        System.out.println("Результат: Час співпадає ідеально!");
                    } else {
                        long diff = ChronoUnit.SECONDS.between(localTime, siteTime);
                        System.out.println("Результат: Час відрізняється на " + Math.abs(diff) + " сек.");
                    }
                } else {
                    System.out.println("Помилка: Не вдалося знайти тег <time> на сторінці.");
                }

            } else {
                System.out.println("Помилка з'єднання. Код відповіді: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}