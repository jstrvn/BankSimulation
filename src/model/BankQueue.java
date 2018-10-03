/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *Simulates a bank queue
 * @author Trevino
 */
public class BankQueue<T> {
    /**
     * current size of internal list
     * @return current size
     */
    public int size() {
        return queue.size();
    }
    
    private List<T> queue;
    private int capacity;
    /**
     * list of elements
     * @return list of elements
     */
    public List<T> getElements() {
        return queue;
    }
    /**
     * is internal list empty
     * @return is internal list empty
     */
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    /**
     * Max size for current Queue
     * @param cap max size for current Queue
     */
    public BankQueue(int cap) {
        this.capacity = cap;
        queue = new ArrayList<>();
    }
    /**
     * next element in queue
     * @return neext element in queue
     */
    
    public T getNext() {
        if (queue.isEmpty())  {
            return null;
        }
        T tmp = queue.get(0);
        queue.remove(0);
        return tmp;
    }
    /**
     * updates capacity of queue
     * @param cap new value for capacity instance field
     */
    
    public void setCapacity(int cap) {
        capacity = cap;
    }
    /**
     * adds a new element
     * @param e new element to add
     * @return true if e was added with success
     */
    
    public boolean add(T e) {
        if (isFull()) {
            return false;
        }
        queue.add(e);
        return true;
    }
    
    /**
     * checks if internal list is already full
     * @return true if its capacity have been reached.
     */
    public boolean isFull() {
        if (queue.size() < capacity) {
            return false;
        }
        return true;
    }

}
