/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import recup.Lire;

/**
 *
 * @author mariewinkler
 */
public class AppuiDouble extends Appui{
    
     public AppuiDouble (int identificateur, TriangleTerrain terrain, int point1, double position) {
        super(identificateur,terrain,point1,position);
    }
     
     public AppuiDouble(int identificateur, TriangleTerrain terrain,Point p){
         super(identificateur, terrain, p);
     }
     
     public String toString(){
       return "AppuiDouble;"+ this.getIdentifiant()+";"+this.getTriangle().getIdentificateur()+";"+ this.getPoint1()+";" 
               +this.getPosition();
    }
     
    public void dessine(GraphicsContext gc){
        gc.setFill(Color.RED);
        super.dessine(gc);    
    }
    
}
