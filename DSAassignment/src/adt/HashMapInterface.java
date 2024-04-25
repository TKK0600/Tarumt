/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import adt.HashMapInterface.Entry;
import java.util.Comparator;



/**
 *
 * @author e-default
 * @param <K>
 * @param <T>
 */
public interface HashMapInterface<K, V> extends Iterable<Entry<K,V>>{

    public void put(K key, V elements);   //TKK  //add elements and the key of the elements

    public V get(K key);         //YT

    public void replace(K key, V elements);      //Jia Chuan

    public V remove(K key);   //YT

    public void doubleSize();           //TKK

    public void sort(ArrayList<V> array,Comparator<V> comparator);         // Jia Chuan

    public boolean containsKey(K key);              //Jia Chuan

    public boolean containsValue(V elements);           //Jia Chuan

    public boolean isEmpty();          // Brad

    public boolean isFull();           //Brad

    public void clear();                //Brad
    
    //public void printAllValues();

    public String toString();
    //iterator
    
    public int size();
    
    public ArrayList<V> getValues();
    
    //Iterable<K> keySet();
     
    V getOrDefault(K key, V defaultValue);
    
    Iterable<Entry<K, V>> entries();
    
    V getValue(Entry<K, V> entry);
   
   static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
