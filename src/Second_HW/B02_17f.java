package Second_HW;

public class B02_17f {
    public static double cosine(double x, int terms) {
        double result = 0.0;

        for (int k = 0; k < terms; k++) {
            int sign = (k % 2 == 0) ? 1 : -1;
            double term = Math.pow(x, 2 * k) / factorial(2 * k);
            result += sign * term;
        }

        return result;
    }

    public static long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        double x = 1; // x
        int terms = 10; // Кількість членів

        double approx = cosine(x, terms);
        System.out.println("cos(" + x + ") ≈ " + approx);
    }
}
