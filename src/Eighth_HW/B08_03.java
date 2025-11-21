package Eighth_HW;
import java.util.*;

public class B08_03<T> {
    private Map<T, List<T>> adjVertices = new HashMap<>();

    public void addVertex(T vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(T vertex) {
        adjVertices.values().forEach(e -> e.remove(vertex));
        adjVertices.remove(vertex);
    }

    public void addEdge(T source, T destination) {
        if (!adjVertices.containsKey(source)) addVertex(source);
        if (!adjVertices.containsKey(destination)) addVertex(destination);

        adjVertices.get(source).add(destination);
        adjVertices.get(destination).add(source);
    }

    public void removeEdge(T source, T destination) {
        List<T> srcList = adjVertices.get(source);
        List<T> destList = adjVertices.get(destination);

        if (srcList != null) srcList.remove(destination);
        if (destList != null) destList.remove(source);
    }

    @Override
    public String toString() {
        return adjVertices.toString();
    }

    public static void main(String[] args) {
        B08_03<String> graph = new B08_03<>();
        graph.addEdge("Київ", "Львів");
        graph.addEdge("Київ", "Одеса");
        graph.addEdge("Львів", "Одеса");

        System.out.println("Граф: " + graph);

        graph.removeVertex("Одеса");
        System.out.println("Після видалення Одеси: " + graph);
    }
}