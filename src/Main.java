import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        System.out.print("Hello and welcome!\n");

        System.out.print("Please enter a number: ");
        int value;
        try {
            value = System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean is_prime = Lab1.primeCheck(value);
        System.out.print(is_prime);

        System.out.print("\n");

        // Shape ; Question 2
        Lab1.Shape rec = new Lab1.Rectangle(5, 10);
        System.out.print("\nThe area of the rectangle is: ");
        System.out.print(rec.area());

        Lab1.Shape cir = new Lab1.Circle(10);
        System.out.print("\nThe circumference of the circle is: ");
        System.out.println(cir.area());

        // Take an array Question 3
        Lab1.array(); //Save Array

        Lab1.printArray(); // Print array from memory

        // Word counter Question 4
        Lab1.textCounter();


    }
}