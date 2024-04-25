/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author e-default
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 20;
    private Object[] elements;

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean add(T newEntry) {
        if (isArrayFull()) {
            doubleArray();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public T get(int givenPosition) {
        T result = null;

        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
            result = array[givenPosition];
        }
        return result;
    }

    @Override
    public boolean removeBoolean(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }
            numberOfEntries--;
            return true;
        }
        return false;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= numberOfEntries) {
            throw new IndexOutOfBoundsException();
        }
        T returnElement = array[index];
        for (int i = index + 1; i < numberOfEntries; i++) {
            array[i - 1] = array[i];
        }
        array[numberOfEntries - 1] = null;
        numberOfEntries--;
        return returnElement;
    }

    public boolean remove(T element) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(element)) {
                // Shift elements to the left to cover the removed element
                for (int j = i; j < numberOfEntries - 1; j++) {
                    array[j] = array[j + 1];
                }

                // Set the last element to null and decrement the number of entries
                array[numberOfEntries - 1] = null;
                numberOfEntries--;

                return true;
            }
        }

        // Element not found
        return false;
    }

    public void remove(String ID) {
        int index = -1;
        // Find the index of the element to remove
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(ID)) {
                index = i;
                break;
            }
        }
        // If the element is found
        if (index != -1) {
            // Shift elements to the left to fill the gap
            for (int i = index; i < numberOfEntries - 1; i++) {
                array[i] = array[i + 1];
            }
            // Set the last element to null and decrement the size
            array[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    private void doubleArray() {
        T[] oldArray = array;
        array = (T[]) new Object[2 * oldArray.length];

        for (int index = 0; index < oldArray.length; index++) {
            array[index] = oldArray[index];
        }
    }

    public T[] toArray(T[] array) {
        if (array.length < numberOfEntries) {
            // If the provided array is too small to hold all elements, create a new array of the same type and size
            array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), numberOfEntries);
        }

        // Copy the elements of the ArrayList to the provided array
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = this.array[i];
        }

        if (array.length > numberOfEntries) {
            // If the provided array is larger than the size of the ArrayList, set the first element beyond the size to null
            array[numberOfEntries] = null;
        }

        return array;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public boolean set(int givenPosition, T newEntry) {
        // Check if givenPosition is within bounds
        if (givenPosition < 0 || givenPosition >= numberOfEntries) {
            // Invalid position, return false
            return false;
        } else {
            // Update the element at the specified position
            array[givenPosition] = newEntry;
            // Operation successful, return true
            return true;
        }
    }

    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    @Override
    public void addAll(ArrayList<T> otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            T element = otherList.get(i);
            if (element != null) {
                this.add(element);
            }
        }
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        // Check if the givenPosition is valid
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            // Replace the element at the given position with the newEntry
            array[givenPosition - 1] = newEntry;
            return true; // Return true indicating successful replacement
        }
        return false; // Return false if the givenPosition is invalid
    }

    @Override
    public boolean addNoDuoplicated(T newEntry) {
        // Check if the newEntry already exists in the list
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(newEntry)) {
                // If the newEntry already exists, return false (entry not added)
                return false;
            }
        }

        // If the newEntry does not exist, add it to the list
        if (isArrayFull()) {
            doubleArray();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    public boolean contains(T memberToRemove) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (memberToRemove.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public void clear() {
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = null;  // Remove references to the objects
        }
        numberOfEntries = 0;  // Reset the size to 0
    }

}
