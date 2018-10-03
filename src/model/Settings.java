/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *Contains settings used by the program to generate a simulation
 * @author Trevino
 */
public class Settings {
    private Range withdrawalTime;
    private Range depositTime;
    private Range openingTime;
    private Range cancelatingTime;
    private Range inquiringTime;
    private int executiveQueueMaxSize;
    private int windowQueueMaxSize;
    private int windows;
    private int executives;
    private int maxRateClients;
    
    /**
     * Default values based on pdf specifications
     */
    public Settings() {
        withdrawalTime =  new Range(8, 15,2);
        depositTime = new Range(3, 6, 2);
        openingTime = new Range(15, 25, 2);
        cancelatingTime = new Range(5, 8, 2);
        inquiringTime = new Range(20, 30, 2);
        executiveQueueMaxSize = 35;
        windowQueueMaxSize = 100;
        windows = 8;
        executives = 3;
        maxRateClients = 6;
    }
   /**
    * Maximum number of clients per minute
    * @return Maximum number of clients per minute
    */
    public int getMaxRateClients() {
        return maxRateClients;
    }
    /**
     * Max size of window queue
     * @return Max size of window queue
     */
    public int getWindowQueueMaxSize() {
        return windowQueueMaxSize;
    }
    /**
     * Number of windows
     * @return Number of windows
     */
    public int getWindows() {
        return windows;
    }
    
    /**
     * Maximum size of executive queue
     * @return Maximum size of executive queue
     */
    
    public int getExecutiveQueueMaxSize() {
        return executiveQueueMaxSize;
    }
    /**
     * Number of executives
     * @return number of executives
     */
    public int getExecutives() {
        return executives;
    }
    
    /**
     * Initializes instance variables with value gathered from filepath
     * @param filepath path to config textfile
     */
    
    public Settings(String filepath)   {
        this();
        try{
            Scanner sc = new Scanner(new File(filepath));

            while (sc.hasNext()) {
                String currentLine = sc.nextLine();
                String[] tokens = currentLine.split(":");
                for(String s: tokens) {
                    System.out.println(s);
                }
                String[] range = tokens[1].trim().split("-");
                int a = Integer.parseInt(range[0].trim());
                int b = Integer.parseInt(range[1].trim());

                switch(tokens[0]) {
                    case "tasa de clientes":
                        maxRateClients = b;
                        break;
                    case "ejecutivos":
                        executives = a;
                        executiveQueueMaxSize = b;
                        break;
                    case "ventanillas":
                        windows = a;
                        windowQueueMaxSize = b;
                        break;
                    case "retiro":
                        withdrawalTime =  new Range(a, b,2);
                        break;
                    case "deposito":
                        depositTime = new Range(a, b, 2);
                        break;
                    case "cancelacion:":
                        cancelatingTime = new Range(a, b, 2);
                        break;
                    case "aclaraciones":
                        inquiringTime = new Range(a, b, 2);
                        break;
                     case "apertura":    
                         openingTime = new Range(a, b, 2);
                         break;
                } 
            }
        }
        catch(Exception ex) {
            System.out.println("File not found: default values will be used");
        }
    }
    /**
     * It will generate a random time for a withdrawal op
     * @return random time for a withdrawal operation
     */
    public double getWithdrawalTime() {
        return  withdrawalTime.getNext();
    }
    
    /**
     * It will generate random time for a deposit operation
     * @return random time for a deposit operation
     */
    public double getDepositTime() {
        return depositTime.getNext();
    }
    /**
     *  It will generate random time for a opening operation
     * @return random time for a opening operation
     */
    
    public double getOpeningTime() {
        return openingTime.getNext();
    }   
    
    /**
     * It will generate random time for a canceling operation
     * @return random time for a canceling operation
     */
    
    public double getCancelingTime() {
        return cancelatingTime.getNext();
    }
    /**
     * It will generate random time for a asking operation
     * @return random time for an asking operation
     */
    public double getInquiringTime() {
        return inquiringTime.getNext();
    }
    
}
