/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;
/**
 *
 * @author e-default
 */
public interface Iterator<K,V> {
    boolean hasNext();
    K nextKey();
    V nextValue();
    //Entry<K,V> next();
}
