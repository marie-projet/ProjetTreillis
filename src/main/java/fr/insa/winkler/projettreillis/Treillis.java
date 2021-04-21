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
public class Treillis {
    private int identifiant;
    private Noeud[][] listeNoeuds;
    private Barre[][] listeBarres;
    private Terrain terrain;
    
public Treillis (int identifiant, Noeud [][] listeNoeuds, Barre [][] listeBarres, Terrain terrain){
    this.identifiant = identifiant;
    this.listeNoeuds = listeNoeuds;
    this.listeBarres = listeBarres;
    this.terrain = terrain; 
}

    public int getIdentifiant() {
        return identifiant;
    }

    public Noeud[][] getListeNoeuds() {
        return listeNoeuds;
    }

    public Barre[][] getListeBarres() {
        return listeBarres;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public void setListeNoeuds(Noeud[][] listeNoeuds) {
        this.listeNoeuds = listeNoeuds;
    }

    public void setListeBarres(Barre[][] listeBarres) {
        this.listeBarres = listeBarres;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

}
