package Eighth_HW;
import java.util.EmptyStackException;

public class B08_01<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top; // Вершина стеку

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String[] args) {
        B08_01<String> stack = new B08_01<>();

        stack.push("Java");
        stack.push("is");
        stack.push("Awesome");

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}