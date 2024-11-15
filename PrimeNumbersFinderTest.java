import java.io.*;
import java.util.*;

public class PrimeNumbersFinderTest {
    public static void main(String[] args) throws IOException {
        // Тест 1: Число N = 10, кількість потоків = 2
        runTest("10\n2\n", Arrays.asList(2, 3, 5, 7));

        // Тест 2: Число N = 1, кількість потоків = 1
        runTest("1\n1\n", Collections.emptyList());

        // Тест 3: Число N = 0, кількість потоків = 1
        runTest("0\n1\n", Collections.emptyList());

        // Тест 4: Число N = 20, кількість потоків = 4
        runTest("20\n4\n", Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));

        // Тест 5: Невірне значення N
        runTest("-1\n2\n", null);

        // Тест 6: Невірна кількість потоків
        runTest("10\n0\n", null);
    }

    private static void runTest(String input, List<Integer> expectedPrimes) throws IOException {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        System.setIn(testInput);
        System.setOut(new PrintStream(testOutput));

        PrimeNumbersFinder.main(new String[0]);

        System.setIn(originalIn);
        System.setOut(originalOut);

        if (expectedPrimes != null) {
            String output = testOutput.toString();
            List<Integer> foundPrimes = extractPrimes(output);

            if (foundPrimes.equals(expectedPrimes)) {
                System.out.println("Тест пройдено: " + input.trim());
            } else {
                System.out.println("Тест НЕ пройдено: " + input.trim());
                System.out.println("Очікувано: " + expectedPrimes);
                System.out.println("Отримано: " + foundPrimes);
            }
        } else {
            String output = testOutput.toString();
            if (output.contains("Число має бути в межах") || output.contains("Кількість потоків має бути більшою за 0")) {
                System.out.println("Тест пройдено: " + input.trim());
            } else {
                System.out.println("Тест НЕ пройдено: " + input.trim());
                System.out.println("Отримано: " + output);
            }
        }
    }

    private static List<Integer> extractPrimes(String output) {
        String primesLine = Arrays.stream(output.split("\n"))
                .filter(line -> line.startsWith("Прості числа:"))
                .findFirst()
                .orElse("");

        String primesString = primesLine.replace("Прості числа:", "").trim();
        if (primesString.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(primesString.substring(1, primesString.length() - 1).split(", "))
                .map(Integer::parseInt)
                .toList();
    }
}