package Twelfth_HW;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParsingMain {

    public static void main(String[] args) {
        processStudentFile("input01.txt");
    }

    public static void processStudentFile(String filename) {
        System.out.println("--- Processing " + filename + " ---");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // 1. Читання параметрів студента
            String typeLine = br.readLine();     // Наприклад: "Humanitarian"
            String creditsLine = br.readLine();  // Наприклад: "30"
            String moneyLine = br.readLine();    // Наприклад: "1000"

            if (typeLine == null || creditsLine == null || moneyLine == null) return;

            int reqCredits = Integer.parseInt(creditsLine.trim());
            int startMoney = Integer.parseInt(moneyLine.trim());
            String type = typeLine.trim().toLowerCase();

            // Фабрика студентів
            Student student = null;
            switch (type) {
                case "humanitarian": student = new HumanitarianStudent(reqCredits, startMoney); break;
                case "natural": student = new NaturalStudent(reqCredits, startMoney); break;
                case "natural-humanitarian": // Або як у завданні може бути "mixed"
                case "mixed": student = new MixedStudent(reqCredits, startMoney); break;
                default:
                    System.out.println("Unknown student type: " + type);
                    return;
            }

            // 2. Читання та виконання подій (Command parsing -> Visitor creation)
            String line;
            while ((line = br.readLine()) != null && !student.isExpelled()) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String command = parts[0];

                Visitor visitor = null;

                try {
                    switch (command) {
                        case "teach":
                            visitor = new TeacherVisitor(parts[1], Integer.parseInt(parts[2]));
                            break;

                        case "pay":
                            visitor = new ExpenseVisitor(parts[1], Integer.parseInt(parts[2]));
                            break;

                        case "obtain":
                            visitor = new IncomeVisitor(parts[1], Integer.parseInt(parts[2]));
                            break;
                    }

                    if (visitor != null) {
                        student.accept(visitor);
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                }
            }

            System.out.println("--- Result ---");
            if (student.isExpelled()) {
                System.out.println("Student was EXPELLED.");
            } else if (student.getCredits() >= student.getRequiredCredits()) {
                System.out.println("Congratulations! Student received a DIPLOMA. (Credits: " + student.getCredits() + ")");
            } else {
                System.out.println("Student finished semester but did NOT get diploma (Not enough credits: " + student.getCredits() + "/" + student.getRequiredCredits() + ")");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}