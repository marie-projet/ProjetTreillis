/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import java.text.DecimalFormat;

/**
 *
 * @author mariewinkler
 */
public class Charge {
    private double px;
    private double py;
    private Noeud n;
    
    public Charge(double px, double py, Noeud n){
        this.px=(double)Math.round(px * 1000) / 1000;
        this.py=(double)Math.round(py * 1000) /1000;
        this.n=n;
    }
    
    public String toString(){
        return "Noeud;"+n.getIdentifiant()+";"+"Fx;"+px+";"+"Fy;"+py+"\n";
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

    public void setPx(double px) {
        this.px = px;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public void setN(Noeud n) {
        this.n = n;
    }
    
    
}
