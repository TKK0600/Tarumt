/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author e-default
 */
public interface ArrayListInterface<T> {
    public boolean add(T newEntry);
    
    public T get(int givenPosition);
    
    public boolean set(int givenPosition,T newEntry);
    
    public int size();

    public boolean isEmpty();
    
    public T remove(int givenPosition);
    
    public boolean removeBoolean(int givenPosition);
    
    public boolean replace(int givenPosition, T newEntry);
    
    public boolean addNoDuoplicated(T newEntry);
    
    public boolean contains(T memberToRemove);
    
    public void clear();
    
    public void addAll(ArrayList<T> otherList);
}
