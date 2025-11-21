package Nineth_HW;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class B09_09 {

    static final int N = 3;          // Кількість конвеєрів
    static final int K = 2;          // Критичний розмір черги

    // Час прибуття (T1 - T2) в мілісекундах
    static final int T1 = 200;
    static final int T2 = 500;

    // Час обробки (T3 - T4) в мілісекундах
    static final int T3 = 1000;
    static final int T4 = 2000;

    static final int SIMULATION_TIME = 10000; // Час роботи симуляції (10 сек)

    public static void main(String[] args) {
        Factory factory = new Factory(N, K);
        Random random = new Random();
        long startTime = System.currentTimeMillis();

        System.out.println("--- Початок симуляції заводу ---");
        System.out.printf("Конвеєрів: %d, Критична черга K: %d\n", N, K);

        while (System.currentTimeMillis() - startTime < SIMULATION_TIME) {
            Thread part = new Thread(new Part(factory));
            part.start();

            try {
                Thread.sleep(random.nextInt(T2 - T1 + 1) + T1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        factory.printStatistics();
    }
}

class Factory {
    private final Semaphore conveyors;
    private final int K;

    private int currentQueueSize = 0;
    private long totalTimeAboveK = 0;
    private long lastUpdateTime;

    public Factory(int n, int k) {
        this.conveyors = new Semaphore(n, true); // true для FIFO (справедлива черга)
        this.K = k;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public void arrive() {
        updateTimeStats(1); // +1 до черги
    }

    public void occupyConveyor() throws InterruptedException {
        conveyors.acquire();
        updateTimeStats(-1); // -1 з черги
    }

    public void releaseConveyor() {
        conveyors.release();
    }

    private synchronized void updateTimeStats(int change) {
        long currentTime = System.currentTimeMillis();

        if (currentQueueSize >= K) {
            totalTimeAboveK += (currentTime - lastUpdateTime);
        }

        currentQueueSize += change;
        lastUpdateTime = currentTime;

        // Лог для наочності я тут його виключив поки
//         System.out.printf("Queue: %d (Time > K: %d ms)\n", currentQueueSize, totalTimeAboveK);
    }

    public synchronized void printStatistics() {
        System.out.println("\n--- Результати ---");
        System.out.printf("Загальний час, коли в черзі було не менше %d деталей: %d мс\n", K, totalTimeAboveK);
    }
}

class Part implements Runnable {
    private final Factory factory;
    private final Random random = new Random();

    public Part(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        try {
            // 1. Деталь прийшла на завод -> стає в чергу
            factory.arrive();

            // 2. Намагається потрапити на конвеєр (тут потік чекає, якщо зайнято)
            factory.occupyConveyor();

            // 3. Обробка на конвеєрі (T3...T4)
            int processingTime = random.nextInt(B09_09.T4 - B09_09.T3 + 1) + B09_09.T3;
            Thread.sleep(processingTime);

            // 4. Деталь готова
            factory.releaseConveyor();
            System.out.print(".");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}