/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Simulator;
import model.Tick;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *Generates frame for the stats that appear at the end of the simulation
 * @author Trevino
 */
public class StatsFrame extends JFrame{
    
    public StatsFrame(Tick tick, Simulator model) {
        super("Stats");
        
        
        setLayout(new GridLayout(1,0));

        String[] columnNames = {"# clientes atendidos",
                                "# clientes aun por atender ",
                                "Tiempo espera mas grande",
                                "Operacion mas frecuente"};

        Object[][] data = {
	    {model.getClientCounter(), tick.getQueuedClientsCounter(),
	    tick.getMaxWaitingTime() , tick.getMostCommonOperation()}
        };

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(800, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        
        pack();
        setVisible(true);
    }
    
}

