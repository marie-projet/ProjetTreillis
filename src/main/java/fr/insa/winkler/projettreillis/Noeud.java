/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import java.awt.Point;

/**
 *
 * @author mariewinkler
 */
public abstract class Noeud {
    private int identifiant;
    private Point pos;

    public Point getPos() {
        return pos;
    }
  
    
    //definition du constructeur 
    public Noeud (int identifiant){
        this.identifiant= identifiant;
        
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    
}
