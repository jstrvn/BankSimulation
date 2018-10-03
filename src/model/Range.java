/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *This class is used to generate random values
 * in a interval [a,b] with n bits of decimal precision
 * @author Trevino
 */
public class Range {
    
    private double inferior;
    private double superior;
    private Random random;
    private int precission;
    
    /**
     * 
     * @param inferior lower boundary
     * @param superior upper boundary
     * @param precission n bits
     */
    
    public Range(double inferior, double superior, int precission) {
        this.inferior = inferior;
        this.superior = superior;
        this.precission = precission;
    }
    
    /**
     * upper boundary
     * @return upper boundary
     */
    public double getUpperLimit() {
        return superior;
    }
    /**
     * lower boundary
     * @return lower boundary
     */
    public double getLowerLimit() {
        return inferior;
    }
    /**
     * Random double number
     * @return a new value in the range [a,b]
     */
    public double getNext() {
        return (int)((inferior + Math.random()*(superior-inferior))*precission)/(float)precission;
    }
    
}
