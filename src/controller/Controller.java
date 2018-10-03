/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Employee;
import view.EmployeeTable;
import model.Simulator;
import view.StatsFrame;
import model.Tick;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import view.View;

/**
 *
 * @author Trevino
 */
public class Controller implements Runnable{
    private View view;
    private Simulator model;
    private boolean isRunning;
    private JButton update;
    private JButton stop;
    private JButton show;
    private EmployeeTable tableEmployee;
    private JTextField executivesField;
    private JTextField windowsField;
    private JTextField maxRateClientsField;
    
    private void showTable() {
        boolean isAlreadyVisible = tableEmployee.isVisible();
        tableEmployee.setVisible(!isAlreadyVisible);
    }
    
    /**
     * Updates the number of executives, windows and rate of clients coming. It is called
     * from the GUI
     */
    
    
    public void update() {
        int executives = Integer.parseInt(executivesField.getText());
        int windows = Integer.parseInt(windowsField.getText());
        int maxClients = Integer.parseInt(maxRateClientsField.getText());
        model.updateMaxClientRate(maxClients);
        model.setCurrentExecutives(executives);
        model.setCurrentWindows(windows);
    }
    /**
     * This method is called to stop simulation, it also shows a message with some stats
     * @throws IOException 
     */
    
    public void stop() throws IOException {
        isRunning = false;
        
        Tick tick = model.getCurrentTick();
        
        
        model.stop();
        
        JFrame frame = new StatsFrame(tick, model);
        //JOptionPane.showMessageDialog(null, builder.toString(), "Fin",  JOptionPane.INFORMATION_MESSAGE);
       
       // System.exit(0);
    }
    
    /**
     * Initializes instance variables and set-up events
     * @param windows Number of windows in the bank
     * @param maxWindowQueue Maximum size of windows queue
     * @param executives Number of executives in the bank
     * @param maxExecutiveQueue Maximum size of executives queue
     * @param maxRateClients Max number of clients per minute in simulation
     */
    
    
    public Controller(int windows, int maxWindowQueue, int executives, int maxExecutiveQueue, int maxRateClients) {
        windowsField = new JTextField(String.valueOf(windows));
        executivesField = new JTextField(String.valueOf(executives));
        maxRateClientsField  = new JTextField(String.valueOf(maxRateClients));
        
        update = new JButton("Update");
        stop = new JButton("Stop");
        show = new JButton("Show employees");

        show.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                showTable();
            }
        });
        

        
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    stop();
                }
                catch(IOException ex) {
                    
                }
            }
            
        });
        
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               update();
            }
        }
        );
        
        view = new View();
        view.setPreferredSize(new Dimension(250, 250));
   
        isRunning = true;
       
        model = new Simulator(windows, maxWindowQueue, executives, maxExecutiveQueue, maxRateClients);
        tableEmployee = new EmployeeTable();
      
        model.addObserver(view);
        model.addObserver(tableEmployee);
        
        JFrame frame = new JFrame("Ventana principal");
        frame.setPreferredSize(new Dimension(1300, 250));
        GridLayout layout = new GridLayout(1, 2);
        
        frame.setLayout(layout);
        JPanel panel = new JPanel();
        GridLayout grid = new GridLayout(3,3);
        panel.setLayout(grid);
        panel.add(new JLabel("Num Executives: "));
        panel.add(new JLabel("Num Windows: "));
        panel.add(new JLabel("Max clients per min: "));
        panel.add(executivesField);
        
        panel.add(windowsField);
       
        panel.add(maxRateClientsField);
        
        panel.add(update);
        panel.add(stop);
        panel.add(show);
       
        frame.add(panel);
        frame.add(view);
     
        
        frame.pack();
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    /**
     * Moves simulation one unit of time ahead
     */
    public void run() {
        while (isRunning) {
            model.tick();
        }
    }
    
}