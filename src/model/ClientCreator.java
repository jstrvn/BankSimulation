
package model;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *A thread which creates and enqueues clients
 * @author Trevino
 */
public class ClientCreator implements Runnable{
    private Random random;
    private Bank bank;
    private int clientRate;
    
    
    /**
     * 
     * @param bank which this instance will populate
     * @param clientRate max number of clients per minute
     */
    public ClientCreator(Bank bank, int clientRate) {
        this.bank = bank;
        random = new Random();
        this.clientRate = clientRate;
    }
    /**
     * new rate of clients
     * @param cpm new rate of clients
     */
    public void updateClientsRate(int cpm) {
        this.clientRate = cpm;
    }
    /**
     * a cycle used to feed bank with a random number of clients per minute
     * where clientRate is a maximum 
     */
    @Override
    public void run() {
        int j = 1;
        int clientsToBeGenerated = 0;
        int clientsGenerated = 0;
        int dif;
        while (true) {
            if ( j % 2 == 1) {
                clientsToBeGenerated =  random.nextInt()%(clientRate+1);
                clientsGenerated = random.nextInt()%(clientRate+1);
                
                dif = Math.abs(clientsToBeGenerated- clientsGenerated);
                
            }
            else {
                if ( clientsGenerated > clientsToBeGenerated) {
                    
                    dif = clientsToBeGenerated;
                }
                else {
                    dif = clientsGenerated;
                }
            }
            for (int i = 0; i < dif; i++) {
                    boolean accepted = bank.addClient();
                    if (!accepted) {
                        System.out.println("ClientCreator: client discarded");
                    }
                    else {
                        System.out.println("ClientCreator: client queued");
                    }
                }
            try {
                Thread.sleep(125);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}