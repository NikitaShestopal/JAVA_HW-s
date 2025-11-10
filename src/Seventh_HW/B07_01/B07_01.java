package Seventh_HW.B07_01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B07_01 {

    /**
     * [Частина 1] Створює бінарний файл F та записує в нього масив дійсних чисел.
     */
    public static void createFileF(String filename, double[] numbers) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            for (double num : numbers) {
                dos.writeDouble(num);
            }
            System.out.println("Файл " + filename + " успішно створено.");
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл F: " + e.getMessage());
        }
    }

    /**
     * [Частина 2] Описує функцію, що зчитує масив дійсних чисел з файлу.
     */
    public static double[] readDoublesFromFile(String filename) {
        List<Double> numbersList = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    double num = dis.readDouble();
                    numbersList.add(num);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні файлу: " + e.getMessage());
        }

        double[] numbersArray = new double[numbersList.size()];
        for (int i = 0; i < numbersList.size(); i++) {
            numbersArray[i] = numbersList.get(i);
        }
        return numbersArray;
    }

    /**
     * [Частина 3] Побудова файлу G з числами з F, що більші за 'a'.
     */
    public static void createFileG(String fileF, String fileG, double a) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileF));
             DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileG))) {

            System.out.println("\nФільтруємо числа, більші за " + a + "...");

            while (true) {
                try {
                    double num = dis.readDouble();
                    if (num > a) {
                        dos.writeDouble(num);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Файл " + fileG + " успішно створено.");

        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Вхідний файл не знайдено: " + fileF);
        } catch (IOException e) {
            System.err.println("Помилка при копіюванні файлів: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileF = "F.dat";
        String fileG = "G.dat";
        double a = 10.0;
        double[] initialData = {1.5, 12.3, 5.0, 25.5, -3.0, 10.0, 10.1};

        createFileF(fileF, initialData);

        double[] dataFromF = readDoublesFromFile(fileF);
        System.out.println("Вміст файлу F: " + Arrays.toString(dataFromF));

        createFileG(fileF, fileG, a);

        double[] dataFromG = readDoublesFromFile(fileG);
        System.out.println("Вміст файлу G (числа > " + a + "): " + Arrays.toString(dataFromG));
    }
}