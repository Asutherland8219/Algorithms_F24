import java.util.NoSuchElementException;

/**
 * Adaptable and Flexible Priority Queue (AFPQ) implementation using a heap.
 * Designed to function as either a min-heap or max-heap.
 * @param <K> Key type, must be Comparable
 * @param <V> Value type
 */
public class AFPQ<K extends Comparable<K>, V> {
    /** Storage Array */
    private Container<K, V>[] heap;
    /** Current number of elements in the heap */
    private int size;
    /**
     * Heap Type Flag (true for min-heap, false for max-heap) */
    private boolean isMinHeap;
    /** Initial capacity of the heap array */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Constructs an empty priority queue
     * @param isMinHeap true for min-heap, false for max-heap
     */
    @SuppressWarnings("unchecked") // This is used to create a container of generic type. Without it, Java freaks out.
    public AFPQ(boolean isMinHeap) {
        this.heap = (Container<K, V>[]) new Container[DEFAULT_CAPACITY];
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }

    /**
     * Alternate constructor with specified initial capacity
     * @param isMinHeap true for min-heap, false for max-heap
     * @param initialCapacity initial size of the internal array
     */
    @SuppressWarnings("unchecked")
    public AFPQ(boolean isMinHeap, int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be >= 1");
        }
        this.heap = (Container<K, V>[]) new Container[initialCapacity];
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }

    /**
     * Compares two keys based on heap type
     * @return true if k1 should be higher in the heap than k2
     */
    private boolean compare(K k1, K k2) {
        int comp = k1.compareTo(k2);
        return isMinHeap ? comp < 0 : comp > 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        /**
         * Swap two values in the heap
         */
        Container<K, V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Container<K, V>[] newHeap = (Container<K, V>[]) new Container[heap.length * 2]; // double the length of the container to avoid overflow
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    /**
     * Moves the node up the heap to maintain heap
     * @param index
     */
    private void upHeap(int index) {

        while (index > 0) {
            int p = parent(index);
            if (compare(heap[index].getKey(), heap[p].getKey())) {
                swap(index, p);
                index = p;
            } else {
                break;
            }
        }
    }

    private void downHeap(int index) {
        while (true) {
            int left = leftChild(index);
            int right = rightChild(index);
            int smallest = index;

            if (left < size && compare(heap[left].getKey(), heap[smallest].getKey())) {
                smallest = left;
            }
            if (right < size && compare(heap[right].getKey(), heap[smallest].getKey())) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private int findContainer(Container<K, V> c) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == c) return i;
        }
        throw new NoSuchElementException("Container not found in heap");
    }

    /**
     * Inserts a new key-value pair into the priority queue
     * @return the created container
     */
    public Container<K, V> insert(K key, V value) {
        if (size == heap.length) resize();

        Container<K, V> container = new Container<>(key, value);
        heap[size] = container;
        upHeap(size);
        size++;
        return container;
    }

    /**
     * Removes and returns the top container from the priority queue
     * @throws NoSuchElementException if queue is empty
     */
    public Container<K, V> removeTop() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");

        Container<K, V> top = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (!isEmpty()) downHeap(0);
        return top;
    }

    /**
     * Returns but does not remove the top container
     * @throws NoSuchElementException if queue is empty
     */
    public Container<K, V> top() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");
        return heap[0];
    }

    /**
     * Removes and returns the specified container
     * @throws NoSuchElementException if container not found
     */
    public Container<K, V> remove(Container<K, V> c) {
        int index = findContainer(c);
        Container<K, V> result = heap[index];

        heap[index] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (index < size) {
            upHeap(index);
            downHeap(index);
        }

        return result;
    }

    /**
     * Replaces the key of the specified container
     * @return the old key
     */
    public K replaceKey(Container<K, V> c, K key) {
        K oldKey = c.getKey();
        int index = findContainer(c);
        c.setKey(key);
        upHeap(index);
        downHeap(index);
        return oldKey;
    }

    /**
     * Replaces the value of the specified container
     * @return the old value
     */
    public V replaceValue(Container<K, V> c, V value) {
        V oldValue = c.getValue();
        c.setValue(value);
        return oldValue;
    }

    /**
     * Toggles between min-heap and max-heap
     */
    public void toggle() {
        isMinHeap = !isMinHeap;
        // Rebuild heap to maintain heap property
        for (int i = (size / 2) - 1; i >= 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Returns current heap state
     * @return true if min-heap, false if max-heap
     */
    public boolean state() {
        return isMinHeap;
    }

    /**
     * Checks if the priority queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of containers in the priority queue
     */
    public int size() {
        return size;
    }
}