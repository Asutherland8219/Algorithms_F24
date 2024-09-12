import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab1 {

    // Prime check function
    public static boolean primeCheck(int val) {
        // 0 and 1 are not prime and are fast edge cases
        if (val <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(val); i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }

    // The base or parent class - making it static so it can be accessed without creating an instance of Lab1
    public static abstract class Shape {
        public abstract int area();
        // Calculates the area of the shape, but is implemented by the subclasses
    }

    // Circle class extends Shape
    static class Circle extends Shape {
        private final int radius;

        public Circle(int radius) {
            this.radius = radius;
        }

        // Override the base area function with the Circle's function
        @Override
        public int area() {
            return (int) (Math.PI * radius * radius);
        }
    }

    // Rectangle class extends Shape
    static class Rectangle extends Shape {
        private final int length;
        private final int width;

        public Rectangle(int length, int width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public int area() {
            return length * width;
        }
    }

    // Static variable to store the array
    private static int[] arr;

    // Method to collect array input from the user
    public static void array() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the desired number of elements in the array: ");
        int size = scanner.nextInt();
        arr = new int[size];

        System.out.print("Please enter " + size + " elements, separated by space: ");

        // Fill the array with user input
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Array saved in memory.");
    }

    // Method to print the array values
    public static void printArray() {
        if (arr == null) {
            System.out.println("No array is stored in memory.");
            return;
        }

        System.out.println("The values in the array are:");
        // Print out the values in the array
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();  // Move to the next line after printing the array
    }

    public static void textCounter() {
        System.out.print("Please enter the path of your txt file: ");
        Scanner pathScanner = new Scanner(System.in);
        String filename = pathScanner.nextLine(); // Get file path from user

        File file = new File(filename);
        int wordcount = 0;

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] words = line.trim().split("\\s+");
                wordcount += words.length;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file path.");
        }
        System.out.println("The number of words in the file: " + wordcount);
    }

}

