package model;

import model.Bank;
import model.ClientCreator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trevino
 */
public class Simulator extends Observable{
    private int clientCounter;
    private double totalWaitingTime;
    private double maxWaitingTime;
    private int clientsQueued;
    
    /**
     * Number of clients served
     * @return number of clients served
     */
    public int getClientCounter() {
        return clientCounter;
    }
    /**
     * Current time measured in minutes since the start of the simulation
     * @return current time  
     */
    public double getCurrentTime() {
        return currentTime;
    }
    
    private double currentTime;
    private int cancelationsCounter;
    private int openingsCounter;
    private int withdrawalsCounter;
    private int depositsCounter;
    private int aclarationsCounter;
    
    private int executiveQueueMaxSize;
    private int windowQueueMaxSize;
    private boolean isRunning;
    private Bank bank;
    private Tick currentTick;
    private Thread creatorThread;
    /**
     * updates value of executives for bank
     * @param exe new value of executives for bank 
     */
    
    public void setCurrentExecutives(int exe) {
        bank.setCurrentExecutives(exe);
    }
    /**
     * Updates value of windows for bank
     * @param exe value of windows for bank
     */
    public void setCurrentWindows(int exe) {
        bank.setCurrentWindows(exe);
    }
    
    private ClientCreator creator;
    
    /**
     * updates maximum number of clients per minute 
     * @param max maximum number of clients per minute 
     */
    public void updateMaxClientRate(int max) {
        creator.updateClientsRate(max);
    }
    
    /**
     * Calculates average waiting time using instance fields
     * @return average waiting time
     */
    public double getAverageWaitingTime() {
        if (clientCounter == 0) return 0;
        return (double)totalWaitingTime/(double)clientCounter;
    }
    
    /**
     * Initializes instance variables and creates ClientCreator thread
     * @param windows Number of windows in the bank
     * @param maxWindowQueue Maximum size of windows queue
     * @param executives Number of executives in the bank
     * @param maxExecutiveQueue Maximum size of executives queue
     * @param maxRateClients Maximum numbers of clients per minute
     */
    
    public Simulator(int windows, int maxWindowQueue, int executives, int maxExecutiveQueue
                , int maxRateClients) {
        this.executiveQueueMaxSize = maxExecutiveQueue;
        this.windowQueueMaxSize= maxWindowQueue;
        bank = new Bank(windows, maxWindowQueue, executives, maxExecutiveQueue);
        creator = new ClientCreator(bank, maxRateClients);
        creatorThread  = new Thread(creator);
        creatorThread.setDaemon(true);
        creatorThread.start();
    }
    
    private void process(Tick tick) {
        clientsQueued = tick.getQueuedClientsCounter();
        cancelationsCounter += tick.getCancelations();
        openingsCounter += tick.getOpeninings();
        withdrawalsCounter += tick.getWithdrawals();
        depositsCounter += tick.getDeposits();
        aclarationsCounter += tick.getQuestionings();
        
        clientCounter += tick.getClientsServed();
        totalWaitingTime += tick.getTotalWaitingTime();
        
        if (maxWaitingTime < tick.getMaxWaitingTime()) {
            maxWaitingTime = tick.getMaxWaitingTime();
        }
    }
    private void printState() {
      System.out.println("current t: " + currentTime);
      System.out.println("client counter: " + clientCounter);
      System.out.println("totalWaitingTime: " + totalWaitingTime);
      System.out.println("maxWaitingTime: " + maxWaitingTime);
      System.out.println("cancelationsCounter: " + cancelationsCounter);
      
      System.out.println("openings: " + openingsCounter);
      System.out.println("withdrawals: " + withdrawalsCounter);
      System.out.println("deposits: " + depositsCounter);
      System.out.println("aclarations: " + aclarationsCounter);
      System.out.println("clients queued: "+ clientsQueued);
      System.out.println("executiveMaxSize : " + executiveQueueMaxSize);
      System.out.println("windowMaxSize : " + windowQueueMaxSize);  
    }
    
    public Tick getCurrentTick() {
        return currentTick;
    }

    public void stop() {
        creatorThread.stop();
    }
    
    /**
     * Makes the bank tick at a fixed frequency
     */
    
    public void tick() {
        
        currentTick = bank.tick();
        process(currentTick);
        setChanged();
        notifyObservers();
        currentTime += 0.5;
        printState();
        try {
            Thread.sleep(125);
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
