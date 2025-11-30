package Twelfth_HW;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// --- Visitor Interface ---
interface Visitor {
    void visit(HumanitarianStudent student);
    void visit(NaturalStudent student);
    void visit(MixedStudent student);
}

// --- Element Interface ---
interface Student {
    void accept(Visitor visitor);
    void addCredits(int credits);
    void addMoney(int amount);
    boolean spendMoney(int amount); // Повертає false, якщо грошей не вистачило
    boolean isExpelled();
    int getCredits();
    int getRequiredCredits();
    String getName(); // Для виводу
}
