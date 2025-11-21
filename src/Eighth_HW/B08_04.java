package Eighth_HW;
import java.util.PriorityQueue;

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getSquaredDistance() {
        return x * x + y * y;
    }

    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }
}

public class B08_04 {
    public static void main(String[] args) {
        PriorityQueue<Point> pq = new PriorityQueue<>(
                (p1, p2) -> Double.compare(p1.getSquaredDistance(), p2.getSquaredDistance())
        );

        // Додаємо точки
        pq.add(new Point(3, 4));   // Відстань 5
        pq.add(new Point(1, 1));   // Відстань ~1.41
        pq.add(new Point(10, 10)); // Відстань ~14.1
        pq.add(new Point(0, 1));   // Відстань 1

        System.out.println("Точки у порядку зростання відстані до центру:");

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            System.out.println(p + " -> SqrtDist: " + Math.sqrt(p.getSquaredDistance()));
        }
    }
}