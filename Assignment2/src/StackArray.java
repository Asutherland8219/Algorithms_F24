import java.util.EmptyStackException;

class StackArray<T> {
    // Generic type T so it can store any type of data
    private T[] stack;
    private int top;

    public StackArray(int size) {
        // Inits the stack based on the size given
        stack = (T[]) new Object[size];
        top = -1;
    }

    public void push(T value) {
        // Adds new Element to the top of the stack
        if (top == stack.length - 1) {
            resize();
        }
        stack[++top] = value;
    }

    public T pop() {
        // removes and returns the top value of the stack
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = stack[top--];
        return value;
    }

    public T peek() {
        // prints the top element of the stack, but does not remove it
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top];
    }

    public boolean isEmpty() {
        // checks whether the stack is empty
        return top == -1;
    }

    private void resize() {
        // Increase the size of the stack when it is full to prevent overflow
        T[] newStack = (T[]) new Object[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    public int size() {
        // Return the size of the stack
        return top + 1;
    }


}