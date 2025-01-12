package arrayList;

import java.util.Arrays;

public class CustomArrayList<E> {
    // Default initial capacity
    private static final int DEFAULT_CAPACITY = 10;

    // Array to store elements
    private Object[] elementData;
    // Number of elements in the ArrayList
    private int size;

    // Constructor to initialize with default capacity
    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    // Constructor to initialize with a specific capacity
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    // Add an element to the ArrayList
    public boolean add(E e){
        ensureCapacity(size+1); // Ensure capacity before adding
        elementData[size++] = e;
        return true;
    }
    // Get an element at specific index
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }

    // Get the size of the ArrayList
    public int size() {
        return size;
    }

    // Remove an element at a specific index
    public void remove(int index) {
        rangeCheck(index);
        E oldValue = get(index);

        // Shift elements to fill the gap
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            //            source_array, starting_idx to cpy, destination_array,starting_pos, no_ele to cpy
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // Clear the last element for GC
    }

    // Check if the index is within bounds
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Ensure sufficient capacity in the ArrayList
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = Math.max(minCapacity, elementData.length * 2);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

    }

}
