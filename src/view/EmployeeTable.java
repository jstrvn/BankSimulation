/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Tick;
import model.Simulator;
import model.Employee;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *A Window with a table of employees
 * @author Trevino
 */
public class EmployeeTable extends JFrame implements Observer{
    private final JTable table;
    
    public EmployeeTable() {
        super("Tabla empleados");
        
        String[] columnNames = {"Id",
                                "Tipo",
                                "Tiempo ocupado"};

        Object[][] data = {{"as", "s", "s"}};
        setAlwaysOnTop(true);
        
        table = new JTable(new DefaultTableModel(data, columnNames));

        table.setPreferredScrollableViewportSize(new Dimension(800, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,0));

        tablePanel.add(scrollPane);
        add(tablePanel);
        pack();
       
    }
    
    private void updateTable(Simulator model) {
        Tick tick = model.getCurrentTick();
        List<Employee> executives = tick.getExecutiveList();
        List<Employee> windows = tick.getWindowList();


        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Employee e : executives) {
            tableModel.addRow(new Object[]{e.getId(), "Ejecutivo", e.getTimeWorked()});
        }
        for (Employee e : windows) {
            tableModel.addRow(new Object[]{e.getId(), "Ventanilla", e.getTimeWorked()});
        }
    }
    /**
     * This method is called when the model is change and it updates the table
     * @param o
     * @param o1 
     */
    @Override
    public void update(Observable o, Object o1) {
        Simulator model = (Simulator)o;
        updateTable(model);
    }
}
