package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 *Pools clients and employees also it mantains
 * a relation of clients with the employee that is serving them
 * @author Trevino
 */
public class Pool {
    
    private List<Employee> employees;
    private Map<Client, Employee> relations;
    private List<Client> clients;
    
    private int capacity;
    
    /**
     * List of employees in this pool
     * @return list of employees in this pool
     */
    public List<Employee> getEmployees() {
        return employees;
    }
    /**
     * Simulates the passing of time
     * @param time time which will be simulated on employees in minutes
     */
    public void elapsedTime(double time) {
        for (Client c : clients) {
            c.elapsedTime(time);
        }
        for (Employee e: employees) {
            if (!e.getIsAvailable()) {
                e.addToTimeWorked(time);
            }
        }
    }
    
    private Employee getFreeEmployee() {
        for (Employee e: employees) {
            if ( e.getIsAvailable()) {
                return e;
            }
        }
        return null;
    }
    /**
     * Frees the employee which served client c
     * @param c client which is attended by a employee
     */
    
    public void finished(Client c) {
        relations.get(c).setAvailable(true);
    }
    
    /**
     * current size of internal list
     * @return current size
     */
    public int size() {
        return clients.size();
    }
    
    /**
     * list of elements
     * @return list of elements
     get*/
    public List<Client> getElements() {
        return clients;
    }
    /**
     * is internal list empty
     * @return is internal list empty
     */
    
    public boolean isEmpty() {
        return clients.isEmpty();
    }
    /**
     * Max size for current Queue
     * @param cap max size for current Queue
     */
    public Pool(int cap) {
        this.capacity = cap;
        clients = new ArrayList<>();
        employees = new ArrayList<>();
        for (int i = 0; i < cap; i++) {
            employees.add(new Employee());
        }
        relations = new HashMap<>();
    }
    /**
     * next element in queue
     * @return neext element in queue
     */
    
    public Client getNext() {
        if (clients.isEmpty())  {
            return null;
        }
        Client tmp = clients.get(0);
        clients.remove(0);
        return tmp;
    }
    /**
     * updates capacity of queue
     * @param cap new value for capacity instance field
     */
    
    public void setCapacity(int cap) {
        if ( cap > capacity) {
            for (int i = 0; i < cap-capacity; i++) {
                employees.add(new Employee());
            }
        }
        capacity = cap;
        
    }
    /**
     * adds a new element
     * @param c new element to add
     * @return true if e was added with success
     */
    
    public boolean add(Client c) {
        if (isFull()) {
            return false;
        }
        Employee e = getFreeEmployee();
        if (e != null) {
            e.setAvailable(false);
            
            relations.put(c, e);
            
            clients.add(c);
     //       System.out.println("relsize: " + relations.size() + " " + c + " has been adedd");
        }
        else {
            return false;
        }
        return true;
    }
    
    /**
     * checks if internal list is already full
     * @return true if its capacity have been reached.
     */
    public boolean isFull() {
        if (clients.size() < capacity) {
            return false;
        }
        return true;
    }

}


