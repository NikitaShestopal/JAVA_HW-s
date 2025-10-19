package Fifth_HW.B05_02_F;

public class B05_02 {

    /**
     * a) рядок починається з ненульової цифри, за якою тільки літери і їх кількість дорівнює цій цифрі.
     */
    public static boolean checkPropertyA(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        char firstChar = s.charAt(0);

        if (Character.isDigit(firstChar) && firstChar != '0') {
            int requiredLength = Character.getNumericValue(firstChar);

            if (s.length() - 1 == requiredLength) {
                for (int i = 1; i < s.length(); i++) {
                    if (!Character.isLetter(s.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * b) рядок містить (крім літер) тільки одну цифру, і її числове значення дорівнює довжині рядка.
     */
    public static boolean checkPropertyB(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        int digitCount = 0;
        int foundDigitValue = -1;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
                foundDigitValue = Character.getNumericValue(c);
            } else if (!Character.isLetter(c)) {
                return false;
            }
        }
        return (digitCount == 1) && (foundDigitValue == s.length());
    }

    /**
     * c) сума числових значень цифр, які входять в рядок дорівнює довжині рядка.
     */
    public static boolean checkPropertyC(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        int sumOfDigits = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sumOfDigits += Character.getNumericValue(c);
            } else if (!Character.isLetter(c)) {
                return false;
            }
        }
        return (sumOfDigits == s.length());
    }


    public static void main(String[] args) {
        String[] tests = {
                "5hello",   // a: true, b: false, c: false
                "3bye",     // a: true, b: false, c: false
                "7letters", // a: true, b: false, c: false
                "abc6de",   // a: false, b: true, c: true
                "2a1",      // a: false (бо '1' не літера), b: false, c: true (2+1=3)
                "1a2b3",    // a: false, b: false, c: false (1+2+3=6, len=5)
                "0hello",   // a: false (бо '0')
                "4fail",    // a: false (довжина "fail" 4, але 'l' двічі)
                "4abc",     // a: false (довжина "abc" 3, а не 4)
                "a1b2c3d4", // a: false, b: false, c: false (1+2+3+4=10, len=8)
                "justtext"  // a: false, b: false, c: false (sum=0, len=8)
        };

        System.out.println("--- Результати перевірки властивостей рядків ---");
        for (String test : tests) {
            System.out.printf("Рядок: \"%s\"\n", test);
            System.out.printf("  a) %b\n", checkPropertyA(test));
            System.out.printf("  b) %b\n", checkPropertyB(test));
            System.out.printf("  c) %b\n", checkPropertyC(test));
            System.out.println("--------------------");
        }
    }
}