/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import javafx.scene.canvas.GraphicsContext;


/**
 *
 * @author mariewinkler
 */
public abstract class Noeud {
    private int identifiant;

    public static double RAYON_IN_DRAW = 5;
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
    
    public abstract String toString();
    
    public void dessine(GraphicsContext context) {
        context.fillOval(this.getPos().getX()-RAYON_IN_DRAW, this.getPos().getY()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }
    
}
