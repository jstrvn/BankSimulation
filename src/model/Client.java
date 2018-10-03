/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Trevino
 */
public class Client implements Comparable<Client>{
    private double remainingTime;
    private double waitingTime;
    private String operation;
    private double timeOperation;
    private int id;
    private static int counter;
    
    /**
     * Time of the operation that this client perform
     * @return time in minutes
     */
    
    public double getOperationTime() {
        return timeOperation;
    }
    
    
    /**
     * Operation performed by client
     * @return string with operation performed
     */
    public String getOperation() {
        return operation;
    }
   
    public String toString() {
        return String.valueOf(id);
    }
    /**
     * 
     * @param operation operation to be performed
     * @param time time assigned to task
     */
    
   
    public Client(String operation, double time) {
        this.timeOperation = time;
        this.remainingTime = time;
        this.operation = operation;
        counter++;
        id = counter;
    }
    /**
     * time that client has waited on queue
     * @return time that client has waited on queue
     */
    public double getWaitingTime() {
        return waitingTime;
    }
    /**
     * increases waitingTime instnace variable by time
     * @param time magnitude to increase waitingTime
     */
    public void increaseWaitingTime(double time) {
        waitingTime += time;
    }
    /**
     * Simulates operation time, it decreases the accumulator
     * @param e magnitude to decrease time remaining to expire
     */
    public void elapsedTime(double e) {
        this.remainingTime -= e;
    }
    /**
     * Has this client finished his operation
     * @return Has this client finished his operation
     */
    public boolean isDone() {
        return remainingTime <= 0;
    }
    /**
     * Confirms equality by comparing id
     * @param o another client
     * @return 
     */
    public boolean equals(Client o) {
        return this.id == o.id;
    }
    /**
     * Compares object to other client, compares their ids
     * @param t another client
     * @return 
     */
    @Override
    public int compareTo(Client t) {
        return new Integer(this.id).compareTo(t.id);
    }
}
