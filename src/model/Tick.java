/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *Contains stats generated per unit of time
 * @author Trevino
 */
public class Tick {
    private int clientsServed;
    private double maxWaitingTime;
    private double totalWaitingTime;
    private List<Employee> windowList;
    private List<Employee> executiveList;
    
    private int clientsQueued;
    
    private int withdrawals;
    private int deposits;
    private int openings;
    private int cancelations;
    private int questionings;
    
    /**
     * Employees serving in windowd
     * @return list of employees serving in windowd
     */
    public List<Employee> getWindowList() {
        return windowList;
    }
    /**
     * employees serving as executives
     * @return employees that are executives
     */
    public List<Employee> getExecutiveList() {
        return executiveList;
    }
    /**
     * Updates window employees list
     * @param employees new list for window employees
     */
    
    public void setWindowList(List<Employee> employees) {
        this.windowList = employees;
    }
    /**
     * Updates executives list
     * @param employees new list for executives 
     */
    public void setExecutiveList(List<Employee> employees) {
        this.executiveList = employees;
    }
    
    
    /**
     * Calculates the most commong operation during the current unit of time
     * @return the most commong operation during the current unit of time
     */
    
    public String getMostCommonOperation() {
        List<Integer> operations = Arrays.asList(withdrawals, deposits, openings, cancelations,
                            questionings);
        
        int max = Collections.max(operations);
        for (int i=0; i<5; i++) {
            if (operations.get(i) == max) {
                switch(i){
                    case 0:
                        return "retiros";
                    case 1:
                        return "depositos";
                    case 2:
                        return "apertura de cuenta";
                    case 3: 
                        return "cancelacion de cuenta";
                    case 4:
                        return "aclaraciones";
                }
            }
        }
        return "";
    }
    
    /**
     * Receives a client and fetch the operation that this client performed and update
     * the right counter.
     * @param c client to be processed
     */
    public void incrementOperationCounter(Client c) {
        switch(c.getOperation()) {
            case "deposito":
                deposits++;
                break;
            case "retiro":
                withdrawals++;
                break;
            case "cancelacion":
                cancelations++;
                break;
            case "aclaracion":
                questionings++;
                break;
            case "apertura":
                openings++;
                break;
        }
    }
    
    
    /**
     * @return clients which operations have ended in the current unit of time
     */
    public int getClientsServed() {
        return clientsServed;
    }

    /**
     * increments clients counter
     */
    public void incrementClientServedCounter() {
        this.clientsServed++;
    }

    /**
     * @return maxWaitingTime for current unit of time
     */
    public double getMaxWaitingTime() {
        return maxWaitingTime;
    }

    /**
     * Updates maxWaitingTime variable if the new value is greater
     * @param maxWaitingTime a new value to compare to current maxWaitingTime
     */
    public void updateMaxWaitingTime(double maxWaitingTime) {
        
        if (this.maxWaitingTime < maxWaitingTime) {
            this.maxWaitingTime = maxWaitingTime;
        }
    
    }

    /**
     * @return totalWaitingTime in the current unit of time
     */
    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * @param totalWaitingTime amout to increase total waiting in this unit
     * of time
     */
    public void incrementTotalWaitingTime(double totalWaitingTime) {
        this.totalWaitingTime += totalWaitingTime;
    }

    /**
     * @return withdrawals in this unit of time
     */
    public int getWithdrawals() {
        return withdrawals;
    }

    /**
     * increments withdrawal counter 
     */
    public void incrementWithdrawalCounter() {
        this.withdrawals++;
    }

    /**
     * @return the deposits in this unit of time
     */
    public int getDeposits() {
        return deposits;
    }

    /**
     * increments deposit counter
     */
    public void incrementDepositCounter() {
        this.deposits++;
    }

    /**
     * @return the openinings in this unit of time
     */
    public int getOpeninings() {
        return openings;
    }

    /**
     * increments opening counter
     */
    public void incrementOpeniningCounter() {
        this.openings++;
    }

    /**
     * @return cancelations in this unit of time
     */
    public int getCancelations() {
        return cancelations;
    }

    /**
     * increments cancelations counter
     */
    public void incrementCancelationCounter() {
        this.cancelations ++;
    }

    /**
     * return the number of questions during this unit of time
     * @return the questionings during this unit of time
     */
    public int getQuestionings() {
        return questionings;
    }

    /**
     * increments counter of questions in this unit of time
     */
    public void incrementQuestioningCounter() {
        this.questionings++;
    }
    
    /**
     * 
     * @param number  clients queued in this unit of time
     */
    public void setQueuedClientsCounter(int number){
        clientsQueued = number;
    }
    
    /**
     * Calculates the number of clients in both queues.
     * @return Sums the sizes of both queues and returns the result
     */
    
    public int getQueuedClientsCounter() {
        return clientsQueued;
    }
    
}