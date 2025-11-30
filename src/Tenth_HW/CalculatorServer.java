package Tenth_HW;
import java.io.*;
import java.net.*;

public class CalculatorServer {
    public static void main(String[] args) {
        int port = 5000; // Порт для підключення

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущено на порту " + port + ". Очікування клієнта...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клієнт підключився!");

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Помилка сервера: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String response = processExpression(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Зв'язок з клієнтом втрачено.");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String processExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return "Порожній запит.";
        }

        String[] parts = expression.trim().split("\\s+");

        if (parts.length != 3) {
            return "Помилка: Невірний формат. Використовуйте формат 'A op B' (наприклад: 1 + 2)";
        }

        try {
            double num1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double num2 = Double.parseDouble(parts[2]);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) return "Помилка: Ділення на нуль!";
                    result = num1 / num2;
                    break;
                default:
                    return "Помилка: Невідомий оператор '" + operator + "'.";
            }

            if (result == (long) result) {
                return String.format("%d", (long) result);
            } else {
                return String.valueOf(result);
            }

        } catch (NumberFormatException e) {
            return "Помилка: Не вдалося розпізнати числа.";
        } catch (Exception e) {
            return "Невідома помилка обчислення.";
        }
    }
}