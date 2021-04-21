/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import java.awt.Point;

/**
 *
 * @author mariewinkler
 */
public class Terrain {
    private Point[][] listeSommets;
    
public Terrain (Point[] [] listeSommets){
    this.listeSommets = listeSommets;
}

    public Point[][] getListeSommets() {
        return listeSommets;
    }

    public void setListeSommets(Point[][] listeSommets) {
        this.listeSommets = listeSommets;
    }

}
