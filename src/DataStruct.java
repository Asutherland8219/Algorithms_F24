
import java.util.Arrays;


public class DataStruct {
    public static int[] array;

    static class Stack {
        private int[] array;
        private int size_max;
        private int top;

        public Stack(int size_max) {
            this.size_max = size_max;
            this.array = new int[size_max];
            this.top = -1;
        }

        public int[] push(int value) {
            // add an element to the top of the stack
            if (top == size_max - 1) {
                System.err.print("Stack overflow");
                return null;
            } else {
                array[++top] = value;
                return this.array;
            }
        }

        ;

        public int pop() {
            if (top == -1) {
                System.err.println("Stack underflow");
                return -1;
            } else {
                System.out.printf(array[top--] + " Removed successfully.\n");
                System.out.println(array[top--] + "\n");
                return array[top--];
            }
        }

        ;

        public int peek() {
            if (isEmpty()) {
                System.out.print("Stack is empty\n");
                return -1;
            } else {
                System.out.print(array[top] + "\n");
                return array[top];
            }

        }

        ;

        public boolean isEmpty() {
            return top == -1;
        }

        ;

    }

    static class Queue {
        private int[] array;
        private int size_max;
        private int top;
        private int bottom;

        public Queue(int size_max) {
            this.size_max = size_max;
            this.array = new int[size_max];
            this.top = -1;
            this.bottom = 0;
        }

        public int[] enqueue(int value) {
            // Add an element to the queue
            if (top == size_max - 1) {
                System.err.println("Queue overflow");
                return null;
            } else {
                array[++top] = value;
                System.out.println("Enqueued: " + value);
                System.out.println("Queue: " + Arrays.toString(Arrays.copyOfRange(array, bottom, top + 1)));
                return array;
            }
        }

        public int dequeue() {
            // Remove an element from the queue
            if (isEmpty()) {
                System.err.println("Queue underflow");
                return -1;
            } else {
                int value = array[bottom++];
                System.out.println("Dequeued: " + value);
                System.out.println("Queue: " + Arrays.toString(Arrays.copyOfRange(array, bottom, top + 1)));
                return value;
            }
        }

        public boolean isEmpty() {
            return top < bottom;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            } else {
                System.out.println("Peek: " + array[bottom]);
                return array[bottom];
            }
        }
    }

    }

