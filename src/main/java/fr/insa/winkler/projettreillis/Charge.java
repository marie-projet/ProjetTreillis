/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

/**
 *
 * @author mariewinkler
 */
public class Charge {
    
    private double px;
    private double py;
    private Noeud n;
    
    public Charge(double px, double py, Noeud n){
        this.px=px;
        this.py=py;
        this.n=n;
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }

    public Noeud getN() {
        return n;
    }
    
}
