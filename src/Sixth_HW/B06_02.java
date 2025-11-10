package Sixth_HW;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B06_02 {
    public static void main(String[] args) {
        String textWithPhones = "Контакти: +380 (99) 123-45-67, 044-555-1234, та (050) 111 22 33. " +
                "Ще є номер 123-45-67. Неправильний: + (12) --. " +
                "Ще один +380123456789.";

        String regex = "\\+?[\\d\\(][\\d\\s\\(\\)-]{5,}\\d";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textWithPhones);

        System.out.println("Знайдені номери телефонів:");

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}