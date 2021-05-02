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
public class AppuiDouble extends Appui{
    
     public AppuiDouble (int identificateur, TriangleTerrain terrain, int point1, int point2, double position) {
        super(identificateur,terrain,point1,point2,position);
    }
     
     public AppuiDouble(int identificateur, TriangleTerrain terrain,Point p){
         super(identificateur, terrain, p);
     }
     
     public String toString(){
       return "AppuiDouble;"+ this.getIdentifiant()+";"+this.getTerrain().getIdentificateur()+";"+ this.getPoint1()+";" 
               + this.getPoint2()+";"+this.getPosition();
    }
}
