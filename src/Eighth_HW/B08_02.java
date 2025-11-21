package Eighth_HW;
import java.util.Stack;

public class B08_02 {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char lastOpen = stack.pop();

                if (!isPair(lastOpen, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        String text1 = "{[()]}";
        String text2 = "{[(])}";

        System.out.println(text1 + " -> " + isValid(text1));
        System.out.println(text2 + " -> " + isValid(text2));
    }
}