/**
 * Container class for storing key-value pairs in the priority queue.
 * @param <K> key
 * @param <V> value
 */
public class Container<K extends Comparable<K>, V> {
    // Comparable ensures that K must be comparable to itself, all keys must be of same type when comparing
    // ex. You cant have one container with key of string, compared to another container of key of int
    private K key;
    private V value;

    /**
     * Constructor using key and value
     * @param key the priority key
     * @param value the associated value
     */
    public Container(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * @return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets a new key
     * @param key the new key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Sets a new value
     * @param value the new value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * @return string in format "key: value"
     */
    @Override
    // Override because we are using our own custom to string function
    public String toString() {
        return key + ": " + value;
    }
}