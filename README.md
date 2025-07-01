# PrimeThreads

A Java program for finding prime numbers up to a specified number N using multithreading.

## Description

This program:
- Finds all prime numbers up to the user-specified number N.
- Uses multithreading to process ranges of numbers.
- Is implemented using Callable, Future, and CopyOnWriteArrayList for asynchronous data processing.
- Includes calculation of execution time.

## Requirements

- Java 8 or newer.

## Running

### Compiling and Running the Program

1. Clone the repository:
   ```bash
   git clone https://github.com/username/repo-name.git
   cd repo-name
   ```

2. Compile the program:
   ```bash
   javac PrimeNumbersFinder.java
   ```

3. Run the program:
   ```bash
   java PrimeNumbersFinder
   ```

4. Enter data:
   - Number N in the range [0; 1000].
   - Number of threads.

5. The program will output:
   - All found prime numbers.
   - Execution time in milliseconds.

### Compiling and Running Tests

1. Compile the tests:
   ```bash
   javac PrimeNumbersFinderTest.java
   ```

2. Run the tests:
   ```bash
   java PrimeNumbersFinderTest
   ```

3. The test program will automatically check several input scenarios and show whether they were successful.

**Example Output:**
```bash
Test FAILED: 10
2
Expected: [2, 3, 5, 7]
Received: []
Test PASSED: 1
1
Test PASSED: 0
1
Test FAILED: 20
4
Expected: [2, 3, 5, 7, 11, 13, 17, 19]
Received: []
Test PASSED: -1
2
Test PASSED: 10
0
```
