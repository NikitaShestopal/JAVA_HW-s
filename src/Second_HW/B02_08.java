package Second_HW;
import java.util.Scanner;

public class B02_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість елементів у масиві: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        System.out.println("Введіть елементи масиву:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int signChanges = 0;
        for (int i = 1; i < n; i++) {
            if ((arr[i - 1] > 0 && arr[i] < 0) || (arr[i - 1] < 0 && arr[i] > 0)) {
                signChanges++;
            }
        }

        System.out.println("Кількість змін знаку: " + signChanges);
    }

}
