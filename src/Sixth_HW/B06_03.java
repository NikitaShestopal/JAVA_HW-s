package Sixth_HW;
public class B06_03 {

    public static void main(String[] args) {
        String regex = "^(\\s*[+-]?\\s*\\d+)(\\s*[*/+-]\\s*[+-]?\\s*\\d+)*$";

        // Приклади виразів
        String validExpr1 = "+2 - 57*33 + 25/ - 4";
        String validExpr2 = "100";
        String validExpr3 = "-10 + 20 * 3";
        String validExpr4 = "5*5/5+5-5";

        String invalidExpr1 = "10 +"; // Завершується оператором
        String invalidExpr2 = "+ - 10"; // Два знаки підряд (без числа)
        String invalidExpr3 = "5 * / 5"; // Два оператори підряд
        String invalidExpr4 = "abc + 10"; // Містить букви

        System.out.println("--- Перевірка виразів ---");

        System.out.printf("'%s': %b\n", validExpr1, validExpr1.matches(regex));
        System.out.printf("'%s': %b\n", validExpr2, validExpr2.matches(regex));
        System.out.printf("'%s': %b\n", validExpr3, validExpr3.matches(regex));
        System.out.printf("'%s': %b\n", validExpr4, validExpr4.matches(regex));

        System.out.println("--- Неправильні вирази ---");
        System.out.printf("'%s': %b\n", invalidExpr1, invalidExpr1.matches(regex));
        System.out.printf("'%s': %b\n", invalidExpr2, invalidExpr2.matches(regex));
        System.out.printf("'%s': %b\n", invalidExpr3, invalidExpr3.matches(regex));
        System.out.printf("'%s': %b\n", invalidExpr4, invalidExpr4.matches(regex));
    }
}