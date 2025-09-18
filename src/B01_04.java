import java.util.Scanner;

public class B01_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть три цілих числа: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        double geomMean = Math.cbrt(a * b * c);
        System.out.printf("Середнє геометричне = %.4f%n", geomMean);
    }
}

