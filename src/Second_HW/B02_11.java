package Second_HW;
import java.util.Scanner;

public class B02_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле число (0..255): ");
        int number = scanner.nextInt();

        System.out.print("Введіть позицію біта для видалення (0..7): ");
        int i = scanner.nextInt();

        int lowerBits = number & ((1 << i) - 1);
        int higherBits = (number >> (i + 1)) << i;

        int result = higherBits | lowerBits;

        System.out.println("Результат: " + result);
    }
}
