package tpfinal;

import controller.Controller;
import model.Settings;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
/**
 *Inicia hilo del controlador
 * @author Trevino
 */
public class Run {
    public static void main(String[] args) throws FileNotFoundException{
          String filepath = JOptionPane.showInputDialog("Enter config file path: ");
         // String filepath = "/home/perruche/config.txt";
          System.out.println(filepath);
          Settings settings = new Settings(filepath);
          int windows = settings.getWindows();
          int maxWindowsQueue = settings.getWindowQueueMaxSize();
          
          int executives = settings.getExecutives();
          int maxExecutivesQueue = settings.getExecutiveQueueMaxSize();
          int maxRateClients = settings.getMaxRateClients();
          
          Controller c = new Controller(windows, maxWindowsQueue, executives, 
                            maxExecutivesQueue, maxRateClients);
          Thread t = new Thread(c);
          t.start();
    }
}
