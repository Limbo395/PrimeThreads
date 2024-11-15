import java.util.concurrent.*;
import java.util.*;
import java.util.stream.Collectors;

public class PrimeNumbersFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число N (0 <= N <= 1000): ");
        int N = scanner.nextInt();
        if (N < 0 || N > 1000) {
            System.out.println("Число має бути в межах [0; 1000].");
            return;
        }

        System.out.print("Введіть кількість потоків: ");
        int threadCount = scanner.nextInt();
        if (threadCount <= 0) {
            System.out.println("Кількість потоків має бути більшою за 0.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<List<Integer>>> futures = new ArrayList<>();
        CopyOnWriteArrayList<Integer> allPrimes = new CopyOnWriteArrayList<>();

        int rangeSize = (int) Math.ceil((double) N / threadCount);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            int start = i * rangeSize;
            int end = Math.min(start + rangeSize, N);
            if (start >= end) break;

            Callable<List<Integer>> task = new PrimeTask(start, end);
            futures.add(executor.submit(task));
        }

        for (Future<List<Integer>> future : futures) {
            try {
                allPrimes.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        long endTime = System.currentTimeMillis();

        System.out.println("Прості числа: " + allPrimes.stream().sorted().collect(Collectors.toList()));
        System.out.println("Час виконання: " + (endTime - startTime) + " мс");
    }
}

class PrimeTask implements Callable<List<Integer>> {
    private final int start;
    private final int end;

    public PrimeTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Integer> call() {
        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(start, 2); i < end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}