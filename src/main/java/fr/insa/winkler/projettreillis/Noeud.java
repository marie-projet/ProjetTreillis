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
public abstract class Noeud {
    private int identifiant;


  public abstract Point getPos();
    
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
