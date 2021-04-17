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
public class Noeud {
    private int identifiant;
    private int type;
    private double abscisse;
    private double ordonnée;
    
    //definition du constructeur 
    public Noeud (int identifiant, int type, double abs, double ord){
        this.identifiant= identifiant;
        this.type = type;
        this.abscisse= abs;
        this.ordonnée= ord;
    }
}
