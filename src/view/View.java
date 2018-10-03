/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Employee;
import model.Simulator;
import model.Tick;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *Panel with table with fields instante, media tiempo de espera, clientes servidos
 * @author perruche
 */
public class View extends JPanel implements Observer{   
    private final JTable table;
    
    public View() {
        
        String[] columnNames = {" ","Instante",
                                "Media tiempo de espera",
                                "Clientes servidos"};

        Object[][] data = {{new String(" "), new Double(0), new Double(0), new Integer(0)}};
        
        table = new JTable(new DefaultTableModel(data, columnNames));

        table.setPreferredScrollableViewportSize(new Dimension(800, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,0));

        tablePanel.add(scrollPane);
        add(tablePanel);
       
    }
    
    private void updateTable(Simulator model) {
        Tick tick = model.getCurrentTick();
        List<Employee> executives = tick.getExecutiveList();
        List<Employee> windows = tick.getWindowList();


        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        tableModel.addRow(new Object[]{"", model.getCurrentTime(), String.format("%.2f", model.getAverageWaitingTime()), model.getClientCounter()});
        
    }
/**
 * Called when model changes
 * @param o
 * @param o1 
 */
    @Override
    public void update(Observable o, Object o1) {
        Simulator model = (Simulator)o;
        updateTable(model);
    }
}
