package Tenth_HW;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Підключено до сервера обчислень.");
            System.out.println("Введіть вираз (наприклад: 1 + 2, 3 * -5) або 'exit' для виходу:");

            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Роботу завершено.");
                    break;
                }

                out.println(userInput);

                String response = in.readLine();
                System.out.println("Сервер: " + response);
            }

        } catch (IOException e) {
            System.out.println("Не вдалося підключитися до сервера. Перевірте, чи він запущений.");
        }
    }
}
