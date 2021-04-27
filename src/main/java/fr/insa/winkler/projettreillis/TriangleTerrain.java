/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import java.awt.Point;

/**
 *
 * @author elodieherrmann
 */
public class TriangleTerrain {
    private int identificateur;
    private Point PT0;
    private Point PT1;
    private Point PT2;
    
public TriangleTerrain (int identificateur, Point PT0, Point PT1, Point PT2){
    this.identificateur = identificateur; 
    this.PT0 = PT0;
    this.PT1 = PT1;
    this.PT2 = PT2;

}

    public int getIdentificateur() {
        return identificateur;
    }

    public Point getPT1() {
        return PT1;
    }

    public Point getPT2() {
        return PT2;
    }

    public Point getPT0() {
        return PT0;
    }

    public void setIdentificateur(int identificateur) {
        this.identificateur = identificateur;
    }

    public void setPT1(Point PT1) {
        this.PT1 = PT1;
    }

    public void setPT2(Point PT2) {
        this.PT2 = PT2;
    }

    public void setPT0(Point PT3) {
        this.PT0 = PT3;
    }
}
