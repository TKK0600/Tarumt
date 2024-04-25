/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import Entity.Student;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author e-default
 * @param <K> The type of keys.
 * @param <V> The type of values.
 */
public class HashMap<K, V> implements HashMapInterface<K, V>, Serializable {

    public Entry<K, V>[] table; // array that holds the entries of the hash map
    private int size;
    private final int DEFAULT_CAPACITY = 30;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public HashMap(int intialSize) {
        this.table = new Entry[intialSize];
        this.size = 0;
    }

    public HashMap() {
        this.table = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return (value != null) ? value : defaultValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V elements) {
        if (key == null || elements == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        int index = hash(key);
        //System.out.println(index);

        Entry<K, V> existingEntry = table[index];

        // Check if the key already exists in the linked list
        while (existingEntry != null) {
            if (existingEntry.key.equals(key)) {
                existingEntry.value = elements;
                return; // Key already exists, exit the method
            }
            existingEntry = existingEntry.next; // Move to the next entry in the chain
        }

        // If the key does not exist, add a new entry to the end of the linked list
        Entry<K, V> newEntry = new Entry<>(key, elements);
        newEntry.next = table[index];
        table[index] = newEntry;

        size++;

        // Check if the load factor exceeds the threshold, and if so, resize the table
        if ((double) size / table.length > LOAD_FACTOR_THRESHOLD) {
            doubleSize();
        }
    }

    @Override
    public V get(K key) {                 //return the values of the specific key
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
                entry = entry.next;
            }
        }
        return null; // Key not found
    }

    @Override
    public V remove(K key) {                               //remove the key
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> previous = null; // no entry before first one element
            Entry<K, V> current = table[i];

            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        table[i] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    size--;
                    return current.value;
                }
                previous = current;
                current = current.next;
            }
        }
        return null; // Key not found
    }

    @Override
    public void doubleSize() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCapacity];

        // Rehash all entries into the new table
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int newIndex = hash(entry.key) % newCapacity;
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = next;
            }
        }

        // Update the table reference
        table = newTable;
    }

//    test create list
    @Override
    public ArrayList<V> getValues() {
        ArrayList<V> valuesList = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                valuesList.add(current.value);
                current = current.next;
            }
        }

        return valuesList;
    }

//    test sort
    public void sort(ArrayList<V> arr, Comparator<V> comparator) {
        mergeSort(arr, 0, arr.size() - 1, comparator);
    }

    private void mergeSort(ArrayList<V> arr, int left, int right, Comparator<V> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(arr, left, middle, comparator);
            mergeSort(arr, middle + 1, right, comparator);

            merge(arr, left, middle, right, comparator);
        }
    }

    private void merge(ArrayList<V> arr, int left, int middle, int right, Comparator<V> comparator) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        ArrayList<V> leftArray = new ArrayList<>();
        ArrayList<V> rightArray = new ArrayList<>();

        leftArray.clear();
        rightArray.clear();

        // Print the left array elements
//    System.out.println("Left Array:");
        for (int i = 0; i < n1; ++i) {
            V element = arr.get(left + i);
            //System.out.println(element);
            leftArray.add(element);
        }
//
//    // Print the right array elements
//    System.out.println("Right Array:");
        for (int j = 0; j < n2; ++j) {
            V element = arr.get(middle + 1 + j);
            //System.out.println(element);
            rightArray.add(element);
        }

        // Merge the arrays
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray.get(i), rightArray.get(j)) <= 0) {
//        System.out.println("upper part");
                //System.out.println("I : " + leftArray.get(i));
                //System.out.println("J : " + rightArray.get(j));
                arr.set(k++, leftArray.get(i++));
                //System.out.println("position : " + k);
                //System.out.println("entry : "+  leftArray.get(i - 1)); // Accessing the correct element
            } else {
                //System.out.println("lower part");
                //System.out.println("I : " + leftArray.get(i));
                //System.out.println("J : " + rightArray.get(j));
                arr.set(k++, rightArray.get(j++));
                //System.out.println("position : " + k);
                //System.out.println("entry : "+ rightArray.get(j - 1) ); // Accessing the correct element
            }
        }

        // Copy remaining elements of left array
        while (i < n1) {
            arr.set(k++, leftArray.get(i++));
        }

        // Copy remaining elements of right array
        while (j < n2) {
            arr.set(k++, rightArray.get(j++));
        }
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) { // if the key in the map is same with the key input
                return true; // return true
            }
            entry = entry.next;
        }
        return false; // key not found
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public void replace(K key, V elements) {
        V existingValue = get(key); // get the key of the elements
        if (existingValue != null) { // if does not exist will return null else will execute the if
            put(key, elements);
        }
    }

    @Override
    public boolean containsValue(V elements) {
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                if (entry.value.equals(elements)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public boolean isFull() {
        return size == table.length;
    }

    private int hash(K key) {
        int hashValue = key.hashCode();

        // Apply the multiplication method
        double A = (Math.sqrt(5) - 1) / 2; // A is typically (sqrt(5) - 1) / 2
        double fractionalPart = hashValue * A - Math.floor(hashValue * A);
        hashValue = (int) (table.length * fractionalPart);

        // Linear probing to handle collisions
        int initialIndex = hashValue;
        int i = 1;
        while (table[hashValue] != null && !table[hashValue].getKey().equals(key)) {
            // Increment hashValue using linear probing
            hashValue = (initialIndex + i) % table.length;
            i++;
        }

        return hashValue;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public V getValue(Entry<K, V> entry) {
        return entry.getValue();
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return new Iterable<Entry<K, V>>() {
            @Override
            public java.util.Iterator<Entry<K, V>> iterator() {
                return new java.util.Iterator<Entry<K, V>>() {
                    private int currentIndex = 0;
                    private Entry<K, V> currentEntry = null;

                    @Override
                    public boolean hasNext() {
                        while (currentIndex < table.length && table[currentIndex] == null) {
                            currentIndex++;
                        }
                        return currentIndex < table.length;
                    }

                    @Override
                    public Entry<K, V> next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        currentEntry = table[currentIndex++];
                        return currentEntry;
                    }

                    @Override
                    public void remove() {
                        if (currentEntry == null) {
                            throw new IllegalStateException();
                        }
                        HashMap.this.remove(currentEntry.key);
                        currentEntry = null;
                    }
                };
            }
        };
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
