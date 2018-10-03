/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *Represents an employee it generalizes a executive and window agent
 * @author Trevino
 */
public class Employee implements Comparable<Employee>{
    private int id;
    private double timeWorked;
    private boolean isAvailable;
    private static int counter;
/**
 * identifier of current employee
 * @return integer identifying current employee
 */
    
    public int getId() {
        return id;
    }
    /**
     * compares using id
     * @param o other instance of employee
     * @return 
     */
    public boolean equals(Employee o) {
        return this.id == o.id;
    }
    /**
     * Representation in String of employee
     * @return a string with id and timeworked
     */
    public String toString() {
        return id + " : " + timeWorked; 
    }
    /**
     * Creates one new free employee
     */
    
    public Employee() {
        counter++;
        this.id = counter;
        isAvailable = true;
    }
    
    public boolean getIsAvailable() {
        return isAvailable;
    }
    
    /**
     * change state of availabilty
     * @param b new value of availabilty
     */
    public void setAvailable(boolean b) {
        isAvailable = b;
    }
    /**
     * time worked by this employee
     * @return time worked by this employee
     */
    public double getTimeWorked() {
        return timeWorked;
        
    }
    /**
     * Increments timeworked in employee
     * @param time magnitude of increment of timeworked
     */
    public void addToTimeWorked(double time) {
        timeWorked += time;
    }
    /**
     * compare two instances of employee
     * @param t employee to compare with
     * @return 0 if they are equal, 1 if this is greater, -1 otherwise
     */
    @Override
    public int compareTo(Employee t) {
        return new Integer(t.id).compareTo(t.id);
    }
}
