package Fifth_HW.B05_01_F;

public class B05_01 {
    public static String removeParenthesesContent(String input) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                openCount++;
                if (openCount > 1) {
                    return "ПОМИЛКА: Знайдено вкладені дужки.";
                }
            } else if (c == ')') {
                openCount--;
                if (openCount < 0) {
                    return "ПОМИЛКА: Неочікувана закриваюча дужка.";
                }
            } else {
                if (openCount == 0) {
                    result.append(c);
                }
            }
        }

        if (openCount > 0) {
            return "ПОМИЛКА: Є незакрита дужка.";
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String test1 = "Це (приклад) тексту з (дужками).";
        System.out.println("'" + test1 + "' -> '" + removeParenthesesContent(test1) + "'");

        String test2 = "Рядок без дужок.";
        System.out.println("'" + test2 + "' -> '" + removeParenthesesContent(test2) + "'");

        String test3 = "Це (приклад (з вкладеністю)) тексту.";
        System.out.println("'" + test3 + "' -> '" + removeParenthesesContent(test3) + "'");

        String test4 = "Це (приклад тексту.";
        System.out.println("'" + test4 + "' -> '" + removeParenthesesContent(test4) + "'");

        String test5 = "Це )приклад( тексту.";
        System.out.println("'" + test5 + "' -> '" + removeParenthesesContent(test5) + "'");

        String test6 = "(Початок) Текст (Кінець)";
        System.out.println("'" + test6 + "' -> '" + removeParenthesesContent(test6) + "'");
    }
}