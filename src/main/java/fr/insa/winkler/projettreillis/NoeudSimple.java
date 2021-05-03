/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author mariewinkler
 */
public class NoeudSimple extends Noeud{
    private Point position;
    
    public NoeudSimple(int identifiant, Point pos){
        super(identifiant);
        this.position=pos;
    }

    public Point getPos() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    public String toString(){
        return "NoeudSimple;"+this.getIdentifiant()+";"+this.getPos();
    }
    
    public void dessine(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        super.dessine(gc);    
    }
}
