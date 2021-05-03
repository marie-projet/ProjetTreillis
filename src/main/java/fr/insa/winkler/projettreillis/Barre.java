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
public class Barre {
    private int identifiant;
    private Noeud noeudDebut;
    private Noeud noeudFin;
    private TypeBarre type;
    
    //definition du constructeur 
    public Barre (int identifiant, Noeud noeudDeb, Noeud noeudFin, TypeBarre type){
        this.identifiant = identifiant;
        this.noeudDebut = noeudDeb;
        this.noeudFin = noeudFin;
        this.type=type;
    }
    public Barre(int id,Noeud noeudDeb, Noeud noeudFin){
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
} 