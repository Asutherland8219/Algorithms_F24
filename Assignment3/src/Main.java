public class Main {
    public static void main(String[] args) {
        System.out.println("======= AFPQ (Adaptable and Flexible Priority Queue) =======\n");

        // Example 1: Basic min-heap operations
        System.out.println("Example 1: min-heap operation");
        AFPQ<Integer, String> minHeap = new AFPQ<>(true);
        minHeap.insert(5, "Five");
        minHeap.insert(3, "Three");
        minHeap.insert(7, "Seven");
        System.out.println("Top of min-heap: " + minHeap.top().getValue());  // Should print "Three"
        System.out.println();

        // Example 2: Basic max-heap operations
        System.out.println("Example 2: max-heap operation");
        AFPQ<Integer, String> maxHeap = new AFPQ<>(false);
        maxHeap.insert(5, "Five");
        maxHeap.insert(3, "Three");
        maxHeap.insert(7, "Seven");
        System.out.println("Top of max-heap: " + maxHeap.top().getValue());  // Should print "Seven"
        System.out.println();

        // Example 3: Removing top elements from min-heap
        System.out.println("Example 3: remove top from min-heap");
        AFPQ<Integer, String> heap3 = new AFPQ<>(true);

        heap3.insert(5, "Five");
        heap3.insert(3, "Three");
        heap3.insert(7, "Seven");

        System.out.println("\nInitial state:");
        System.out.println("Current top: " + heap3.top().getKey() + " -> " + heap3.top().getValue());
        System.out.println("\nRemoving elements in order:");

        while (!heap3.isEmpty()) {
            System.out.println("\nBefore removal - Top: " + heap3.top().getKey() +
                    " -> " + heap3.top().getValue());

            Container<Integer, String> removed = heap3.removeTop();
            System.out.println("Removed: " + removed.getKey() + " -> " + removed.getValue());

            if (!heap3.isEmpty()) {
                System.out.println("After removal - New top: " + heap3.top().getKey() +
                        " -> " + heap3.top().getValue());
                System.out.println("Heap size: " + heap3.size());
            } else {
                System.out.println("Heap is now empty\n");
            }
        }

        // Example 4: Array resizing
        System.out.println("Example 4: array resizing test ");
        AFPQ<Integer, String> heap4 = new AFPQ<>(true);
        System.out.println("Inserting 15 elements to trigger resize:");
        for (int i = 1; i <= 15; i++) {
            heap4.insert(i, "Element" + i);
            System.out.println("Inserted element " + i + ", size is now: " + heap4.size());
        }
        System.out.println();

        // Example 5: Replacing keys
        System.out.println("Example 5: replace key");
        AFPQ<Integer, String> heap5 = new AFPQ<>(true);
        Container<Integer, String> container5 = heap5.insert(5, "Five");
        heap5.insert(3, "Three");
        heap5.insert(7, "Seven");
        System.out.println("Before replacing key 5: Top is " + heap5.top().getValue());
        heap5.replaceKey(container5, 1);
        System.out.println("After replacing key 5 with 1: Top is " + heap5.top().getValue());
        System.out.println();

        // Example 6: Replacing values
        System.out.println("Example 6: replace value");
        AFPQ<Integer, String> heap6 = new AFPQ<>(true);
        Container<Integer, String> container6 = heap6.insert(5, "Five");
        System.out.println("Original value: " + container6.getValue());
        heap6.replaceValue(container6, "New Five");
        System.out.println("New value: " + container6.getValue());
        System.out.println();

        // Example 7: Toggle between min and max heap
        System.out.println("Example 7: min and max heap toggle");
        AFPQ<Integer, String> heap7 = new AFPQ<>(true);
        heap7.insert(5, "Five");
        heap7.insert(3, "Three");
        heap7.insert(7, "Seven");
        System.out.println("Min heap top: " + heap7.top().getValue());
        heap7.toggle();
        System.out.println("After toggle to max heap, top: " + heap7.top().getValue());
        System.out.println();

        // Example 8: Remove from middle of heap
        System.out.println("Example 8: remove from middle");
        AFPQ<Integer, String> heap8 = new AFPQ<>(true);
        heap8.insert(1, "One");
        Container<Integer, String> middle = heap8.insert(2, "Two");
        heap8.insert(3, "Three");
        System.out.println("Before removing middle element (2): Size = " + heap8.size());
        heap8.remove(middle);
        System.out.println("After removing middle element: Size = " + heap8.size());
        System.out.println();

        // Example 9: Working with strings as keys
        System.out.println("Example 9: using strings as keys");
        AFPQ<String, Integer> heap9 = new AFPQ<>(true);
        heap9.insert("Apple", 1);
        heap9.insert("Banana", 2);
        heap9.insert("Cherry", 3);
        System.out.println("Top string key: " + heap9.top().getKey());  // Should print "Apple"
        System.out.println();

        // Example 10: Working with doubles as keys
        System.out.println("Example 10: using doubles as keys");
        AFPQ<Double, String> heap10 = new AFPQ<>(true);
        heap10.insert(3.14, "Pi");
        heap10.insert(2.718, "e");
        heap10.insert(1.414, "Square root of 2");
        System.out.println("Top double key: " + heap10.top().getKey());  // Should print smallest number
        System.out.println();

        // Example 11: Stress testing with many operations
        System.out.println("Example 11: stress test");
        AFPQ<Integer, String> heap11 = new AFPQ<>(true);
        // Insert 100 elements in random order
        for (int i = 100; i > 0; i--) {
            heap11.insert(i, "Element" + i);
        }
        System.out.println("Inserted 100 elements");
        System.out.println("Top element: " + heap11.top().getKey());  // Should be 1
        System.out.println("Size: " + heap11.size());  // Should be 100
        System.out.println();

        // Example 12: Complex key replacements
        System.out.println("Example 12: test key replacements");
        AFPQ<Integer, String> heap12 = new AFPQ<>(true);
        Container<Integer, String> c1 = heap12.insert(5, "Five");
        Container<Integer, String> c2 = heap12.insert(3, "Three");
        Container<Integer, String> c3 = heap12.insert(7, "Seven");

        System.out.println("Original top: " + heap12.top().getValue());
        heap12.replaceKey(c1, 1);
        System.out.println("After replacing 5 with 1: " + heap12.top().getValue());
        heap12.replaceKey(c2, 10);
        System.out.println("After replacing 3 with 10: " + heap12.top().getValue());
        System.out.println();

        // Example 13: Testing empty queue operations
        System.out.println("Example 13: test empty queue");
        AFPQ<Integer, String> heap13 = new AFPQ<>(true);
        try {
            heap13.top();
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
        System.out.println("Is empty: " + heap13.isEmpty());
        System.out.println();

        // Example 14: Testing min/max state
        System.out.println("Example 14: testing state (min/max)");
        AFPQ<Integer, String> heap14 = new AFPQ<>(true);
        System.out.println("Initial state (true=min, false=max): " + heap14.state());
        heap14.toggle();
        System.out.println("After toggle: " + heap14.state());
        System.out.println();

        // Example 15: Multiple toggles with data
        System.out.println("Example 15: test multiple toggles");
        AFPQ<Integer, String> heap15 = new AFPQ<>(true);
        for (int i = 1; i <= 5; i++) {
            heap15.insert(i, "Element" + i);
        }
        System.out.println("Min heap top: " + heap15.top().getKey());
        heap15.toggle();
        System.out.println("After first toggle (max heap) top: " + heap15.top().getKey());
        heap15.toggle();
        System.out.println("After second toggle (min heap) top: " + heap15.top().getKey());
        System.out.println();

        // Example 16: Custom initial capacity
        System.out.println("Example 16: test custom capacity");
        AFPQ<Integer, String> heap16 = new AFPQ<>(true, 5);
        for (int i = 1; i <= 10; i++) {
            heap16.insert(i, "Element" + i);
            System.out.println("Inserted element " + i + ", size is now: " + heap16.size());
        }
        System.out.println();

        // Example 17: Remove all elements in different order
        System.out.println("Example 17: test removal in different order");
        AFPQ<Integer, String> heap17 = new AFPQ<>(true);
        Container<Integer, String> c17a = heap17.insert(5, "Five");
        Container<Integer, String> c17b = heap17.insert(3, "Three");
        Container<Integer, String> c17c = heap17.insert(7, "Seven");

        System.out.println("Removing middle element: " + heap17.remove(c17b).getValue());
        System.out.println("Removing top element: " + heap17.removeTop().getValue());
        System.out.println("Removing last element: " + heap17.remove(c17c).getValue());
        System.out.println();

        // Example 18: Multiple operations sequence
        System.out.println("Example 18: test multiple operations sequentially");
        AFPQ<Integer, String> heap18 = new AFPQ<>(false);  // max heap
        Container<Integer, String> c18 = heap18.insert(5, "Five");
        heap18.insert(10, "Ten");
        System.out.println("Initial top: " + heap18.top().getValue());
        heap18.replaceKey(c18, 15);
        System.out.println("After replacing 5 with 15: " + heap18.top().getValue());
        heap18.toggle();  // switch to min heap
        System.out.println("After toggle to min heap: " + heap18.top().getValue());
        System.out.println();

        // Example 19: Edge case testing
        System.out.println("Example 19: test edge-case (replace with same key and value)");
        AFPQ<Integer, String> heap19 = new AFPQ<>(true);
        Container<Integer, String> c19 = heap19.insert(1, "One");
        heap19.replaceKey(c19, 1);  // replace with same key
        System.out.println("After replacing with same key: " + heap19.top().getValue());
        heap19.replaceValue(c19, "One");  // replace with same value
        System.out.println("Size after operations: " + heap19.size());
        System.out.println();

        // Example 20: Complex sequence with all operations
        System.out.println("Example 20: test all operations at once");
        AFPQ<Integer, String> heap20 = new AFPQ<>(true);
        System.out.println("1. Creating min heap");
        Container<Integer, String> c20a = heap20.insert(5, "Five");
        Container<Integer, String> c20b = heap20.insert(3, "Three");
        Container<Integer, String> c20c = heap20.insert(7, "Seven");

        System.out.println("2. Top element: " + heap20.top().getValue());
        System.out.println("3. Replacing key 5 with 1");
        heap20.replaceKey(c20a, 1);
        System.out.println("4. New top: " + heap20.top().getValue());

        System.out.println("5. Toggle to max heap");
        heap20.toggle();
        System.out.println("6. New top after toggle: " + heap20.top().getValue());

        System.out.println("7. Remove middle element");
        heap20.remove(c20b);
        System.out.println("8. Size after remove: " + heap20.size());

        System.out.println("9. Final top element: " + heap20.top().getValue());
    }
}