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
    private String section;
    
    //definition du constructeur 
public Barre (int identifiant, Noeud noeudDeb, Noeud noeudFin, String section){
    this.identifiant = identifiant;
    this.noeudDebut = noeudDeb;
    this.noeudFin = noeudFin;
    this.section = section; 
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
    
 public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
} 