package model;

import java.util.Observable;
import java.util.Random;
/**
 *Simulates a bank, the simulation
 * is performed on discrete steps of time.
 * 250ms are equal to 0.5 minutes
 * @author Trevino
 */
public class Bank extends Observable{
    
    private Random random;
    private BankQueue<Client> executiveQueue;
    private BankQueue<Client> windowQueue;
    
    
    private Pool executiveList;
    private Pool windowList;
    
    private int attendedClients;
    private int withdrawalOperations;
    private int depositOperations;
    private int openingOperations;
    private int cancelingOperations;
    private int aclarationOperations;
    private double biggestWaitingTime;
    private Tick tick;
    
    private final double ratioExecutiveToWindow = 0.5;
    
    private Settings settings;
    
    private int currentWindows;
    private int currentExecutives;
    /**
     * Update number of windows
     * @param ex new value for windows
     */
    public void setCurrentWindows(int ex) {
        currentWindows = ex;
        windowList.setCapacity(ex);
    }
    /**
     * Updates number of executives
     * @param ex new value for executives
     */
    public void setCurrentExecutives(int ex) {
        currentExecutives = ex;
        executiveList.setCapacity(ex);
    }
    /**
     * transfers a client from queue to an executive
     */
    public void serveExecutiveClient() {
        executiveList.add(executiveQueue.getNext());
    }
    /**
     * transfers a client from queue to a window
     */
    public void serverWindowClient() {
        windowList.add(windowQueue.getNext());
    }
    /**
     * adds a client to its corresponding queue
     * @return true if the client was added with success to a queue, false
     *      if its corresponding queue is full so the client is discarded
     */
    public synchronized boolean addClient() {
        
        Client c;
        double option = random.nextDouble();
           
        if (random.nextDouble() <= ratioExecutiveToWindow) {
            
            if (option <= 0.7){
                c = new Client("aclaracion", settings.getInquiringTime());
            }
            else if (option <= 0.90){
                c = new Client("apertura", settings.getOpeningTime());
            }
            else {
                c = new Client("cancelacion", settings.getCancelingTime());
            }
           return executiveQueue.add(c);
        }
        else {
            
            if (option <= 0.5) {
                c = new Client("deposito", settings.getDepositTime());
            }
            else{
                c = new Client("retiro", settings.getWithdrawalTime());
            }
            return windowQueue.add(c);
        }
    }
    
    /**
     * Initializes instance variables
     * @param windows Number of windows in the bank
     * @param maxWindowQueue Maximum size of windows queue
     * @param executives Number of executives in the bank
     * @param maxExecutiveQueue Maximum size of executives queue
     */
    
    public Bank(int windows, int maxWindowQueue, int executives, int maxExecutiveQueue) {
       
        this.currentExecutives = executives;
        this.currentWindows = windows;
        
        settings = new Settings();

        executiveQueue = new BankQueue<>(maxExecutiveQueue);
        windowQueue = new BankQueue<>(maxWindowQueue);


        executiveList = new Pool(executives);
        windowList = new Pool(windows);
        random = new Random();
        
   }
    /**
     * Simulates a discrete unit of time, its the heart of the program
     * @return a class with stats from the current time
     */
    public Tick tick() {
        
        tick = new Tick();

        executiveList.elapsedTime(0.5);
        
        windowList.elapsedTime(0.5);
        
        for (int i = 0; i< executiveList.getElements().size();
                    i++) {
            Client current = executiveList.getElements().get(i);
             
            if (current.isDone()) {
                 executiveList.finished(current);
       
                executiveList.getElements().remove(i);
                i--;
                tick.incrementClientServedCounter();
                System.out.println("Bank: executive-client served");
                tick.incrementOperationCounter(current);
            }
        }
        System.out.println("Executives: ");
        for (Employee e: executiveList.getEmployees()) {
            System.out.println(e);
        } 
        
        for (int i = 0; i< windowList.getElements().size();
                    i++) {
            Client current = windowList.getElements().get(i);
            if (current.isDone()) {
                windowList.finished(current);
      
                windowList.getElements().remove(i);
                i--;
                tick.incrementClientServedCounter();
                System.out.println("Bank: window-client served");
            
                tick.incrementOperationCounter(current);
            }
        }
        System.out.println("Windows: ");

        for (Employee e: windowList.getEmployees()) {
            System.out.println(e);
        } 
        
        for(Client c : executiveQueue.getElements()) {
            c.increaseWaitingTime(0.5);
            tick.incrementTotalWaitingTime(0.5);
            tick.updateMaxWaitingTime(c.getWaitingTime());
        }
        for(Client c : windowQueue.getElements()) {
            c.increaseWaitingTime(0.5);
            tick.incrementTotalWaitingTime(0.5);
            tick.updateMaxWaitingTime(c.getWaitingTime());
        }
        while (!executiveList.isFull() && !executiveQueue.isEmpty()) {
            System.out.println("Bank: new client in executive");
            this.serveExecutiveClient();
        }
        while (!windowList.isFull() && !windowQueue.isEmpty()){
            System.out.println("Bank: new client in window");
            this.serverWindowClient();
        }
       
        tick.setQueuedClientsCounter(executiveQueue.size() + windowQueue.size());
        
        System.out.println("Bank: clients in executive : " + executiveList.size());
        System.out.println("Bank: clients in windows : " + windowList.size());

        System.out.println("Bank: clients in executive queue: " + executiveQueue.size());
        System.out.println("Bank: clients in windows queue: " + windowQueue.size());

        tick.setExecutiveList(executiveList.getEmployees());
        
        tick.setWindowList(windowList.getEmployees());

        return tick;
    }
    /**
     * Update number of executives in the bank
     * @param n new number of executives
     */
    public void updateExecutivesNumber(int n) {
        this.currentExecutives = n;
    }
    /**
     * Updates number of windows in the bank
     * @param n new value of windows 
     */
    public void updateNumberWindows(int n) {
        this.currentWindows = n;
    }
    /**
     *stats for the last unit of time
     * @return a reference to the stats of the last unit of time
     */
    
    public Tick getCurrentTick() {
        return tick;
    }
}
