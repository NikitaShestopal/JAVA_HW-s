package Third_HW.B03_08;

import java.util.*;

class Line implements Comparable<Line> {
    private double A, B, C;

    // Базовий конструктор
    public Line(double A, double B, double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    // Конструктор копіювання
    public Line(Line other) {
        this.A = other.A;
        this.B = other.B;
        this.C = other.C;
    }

    // Getters
    public double getA() { return A; }
    public double getB() { return B; }
    public double getC() { return C; }

    // Setters
    public void setA(double A) { this.A = A; }
    public void setB(double B) { this.B = B; }
    public void setC(double C) { this.C = C; }

    // Метод перевірки паралельності
    public boolean isParallel(Line other) {
        return this.A * other.B == this.B * other.A;
    }

    // Метод знаходження точки перетину
    public double[] intersection(Line other) {
        double det = this.A * other.B - other.A * this.B;
        if (det == 0) return null; // паралельні або співпадають
        double x = (this.B * other.C - other.B * this.C) / det;
        double y = (other.A * this.C - this.A * other.C) / det;
        return new double[]{x, y};
    }

    // Метод toString()
    public String toString() {
        return String.format("%.2fx + %.2fy + %.2f = 0", A, B, C);
    }

    // Метод equals() (порівняння з об’єктом іншого типу)
    public boolean equals(Object obj) {
        if (!(obj instanceof Line)) return false;
        Line other = (Line) obj;
        return this.A == other.A && this.B == other.B && this.C == other.C;
    }

    // Comparable (для сортування, наприклад, за коефіцієнтом A)
    public int compareTo(Line other) {
        return Double.compare(this.A, other.A);
    }

    // Метод групування паралельних прямих
    public static Map<Integer, List<Line>> groupParallels(Line[] lines) {
        Map<Integer, List<Line>> groups = new HashMap<>();
        int groupId = 0;

        boolean[] used = new boolean[lines.length];

        for (int i = 0; i < lines.length; i++) {
            if (used[i]) continue;
            groups.put(groupId, new ArrayList<>());
            groups.get(groupId).add(lines[i]);
            used[i] = true;

            for (int j = i + 1; j < lines.length; j++) {
                if (!used[j] && lines[i].isParallel(lines[j])) {
                    groups.get(groupId).add(lines[j]);
                    used[j] = true;
                }
            }
            groupId++;
        }

        return groups;
    }
}

class Main {
    public static void main(String[] args) {
        Line l1 = new Line(1, -1, 0);  // x - y = 0
        Line l2 = new Line(2, -2, 3);  // 2x - 2y + 3 = 0
        Line l3 = new Line(0, 1, -2);  // y - 2 = 0
        Line l4 = new Line(1, 1, -4);  // x + y - 4 = 0

        double[] point = l1.intersection(l4);
        if (point != null)
            System.out.println("Intersection: (" + point[0] + ", " + point[1] + ")");
        else
            System.out.println("Lines are parallel");

        Line[] arr = {l1, l2, l3, l4};

        Map<Integer, List<Line>> groups = Line.groupParallels(arr);
        for (var entry : groups.entrySet()) {
            System.out.println("Group " + entry.getKey() + ": " + entry.getValue());
        }

        Arrays.sort(arr);
        System.out.println("Sorted lines: " + Arrays.toString(arr));
    }
}
