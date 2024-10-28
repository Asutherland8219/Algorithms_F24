import java.io.FileWriter;
import java.io.IOException;

public class Fibonacci {
    boolean timeout = false;

    public void analysisOutput(int type) {
        String function_name = "";
        if (type == 0) {
            function_name = "Slow";
        } else if (type == 1) {
            function_name = "Fast";
        } else if (type == 2) {
            function_name = "Tail Recursive";
        }

        try (FileWriter writer = new FileWriter("out.txt", true)) {
            writer.write("\n\n Fibonacci " + function_name + " Analysis\n\n");
            writer.write(String.format("%-15s%-20s%-30s\n", "Fibonacci(n)", "Recursive Time (ns)", "Recursive Time (sec)"));
            writer.write("------------------------------------------------------------\n");

            // set a 30 second timeout
            long timeoutThreshold = 30 * 1_000_000_000L;  // 30 seconds in nanoseconds
            // Fibonacci analysis from 5 up to 100 in 5 increments
            functionTimeoutWrapper(timeoutThreshold, type, writer);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());

        }
    }

    public void functionTimeoutWrapper(long timeoutThreshold, int type, FileWriter writer) throws IOException {
        long startRecursive = System.nanoTime();
        for (int n = 5; n <= 100; n += 5) {
            // Timeout Control
            try {
                long fib_value = fibonacciExecute(n, startRecursive, timeoutThreshold, type, writer);
            } catch (RuntimeException e) {
//                System.out.println(e.getMessage());
                timeout = true;
            }

            long endRecursive = System.nanoTime();
            long recursiveTime = endRecursive - startRecursive;
            double recursiveTimeSeconds = (double) recursiveTime / 1_000_000_000;  // Convert to seconds

            // Output as table with seconds for easier interpretability
            if (timeout) {
                writer.write(String.format("%-15s%-20s%-30s\n", "Fibonacci(" + n + ")", "Timeout", "Timeout"));
            } else {
                writer.write(String.format("%-15s%-20d%-30.9f\n", "Fibonacci(" + n + ")", recursiveTime, recursiveTimeSeconds));
            }
            writer.flush();
        }
        writer.write("\n");
    }

    public long fibonacciExecute(int n, long startTime, long timeoutThreshold, int type, FileWriter writer) {
        long currentTime = System.nanoTime();

        if (currentTime - startTime >= timeoutThreshold) {
            throw new RuntimeException("Timeout exceeded");
        }

        if (type == 0) {
            fibonacciBad(n); // Slow recursive version
        } else if (type == 1) {
            fibonacciGood(n); // Fast version (original good function)
        } else if (type == 2) {
            fibonacciTail(n, 0, 1); // Tail-recursive Fibonacci version
        }
        return currentTime;
    }

    public long fibonacciBad(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciBad(n - 2) + fibonacciBad(n - 1);
        }
    }

    public static long[] fibonacciGood(int n) {
        if (n <= 1) {
            return new long[]{n, 0};
        } else {
            long[] temp = fibonacciGood(n - 1);  // returns {F(n-1), F(n-2)}
            long[] answer = {temp[0] + temp[1], temp[0]};  // we want {F(n), F(n-1)}
            return answer;
        }
    }

    public long fibonacciTail(int n, long a, long b) {
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }
        return fibonacciTail(n - 1, b, a + b);  // Tail recursion
    }
}


