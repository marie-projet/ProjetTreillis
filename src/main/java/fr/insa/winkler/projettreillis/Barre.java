/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author mariewinkler
 */
public class Barre {
    private int identifiant;
    private Noeud noeudDebut;
    private Noeud noeudFin;
    private TypeBarre type;
    
    public Barre (int identifiant, Noeud noeudDeb, Noeud noeudFin, TypeBarre type){
        this.identifiant = identifiant;
        this.noeudDebut = noeudDeb;
        this.noeudFin = noeudFin;
        this.type=type;
    }
    public Barre(int id, Noeud noeudDeb, Noeud noeudFin){
        this.identifiant=id;
        this.type=new TypeBarre();
        this.noeudDebut = noeudDeb;
        this.noeudFin = noeudFin;
    }

    public String toString(){
        return "Barre;"+this.getIdentifiant()+";"+this.getType().getIdentificateur()+";"+this.getNoeudDebut().getIdentifiant()+";"+this.getNoeudFin().getIdentifiant();
    }

    public int getIdentifiant (){
        return identifiant;
    }
    
    public void setIdentifiant( int identifiant ){
        this.identifiant = identifiant; 
    }
    
    public Noeud getNoeudDebut(){
        return noeudDebut;
    }
    
    public void setNoeudDebut (Noeud noeudDebut){ 
        this.noeudDebut = noeudDebut; 
    }

    public Noeud getNoeudFin() {
        return noeudFin;
    }

    public void setNoeudFin(Noeud noeudFin) {
        this.noeudFin = noeudFin;
    }

    public TypeBarre getType() {
        return type;
    }

    public void setType(TypeBarre type) {
        this.type = type;
    }
    
    /**
     * tracer un segment entre noeud début et noeud fin 
     * @param context
     * @param couleur 
     */
    public void dessine(GraphicsContext context, Color couleur) {
        context.setStroke(couleur);
        context.setLineWidth(0.1);
        context.strokeLine(this.getNoeudDebut().getPos().getX(), -1*this.getNoeudDebut().getPos().getY(), 
                this.getNoeudFin().getPos().getX(), -1*this.getNoeudFin().getPos().getY());
    }
    
    public double longueur(){
        return Math.sqrt(Math.pow(2,this.noeudFin.getPos().getX()-this.noeudDebut.getPos().getX())+
                Math.pow(2,this.noeudFin.getPos().getY()-this.noeudDebut.getPos().getY()));
    }
} 