/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

/**
 *
 * @author elodieherrmann
 */
public class TriangleTerrain {
    private int identificateur;
    private Point PT1;
    private Point PT2;
    private Point PT3;
    private Point P;
    
public TriangleTerrain (int identificateur, Point PT1, Point PT2, Point PT3, Point P){
    this.identificateur = identificateur; 
    this.PT1 = PT1;
    this.PT2 = PT2;
    this.PT3 = PT3;
    this.P = P;
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

    public Point getPT3() {
        return PT3;
    }

    public Point getP() {
        return P;
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

    public void setPT3(Point PT3) {
        this.PT3 = PT3;
    }

    public void setP(Point P) {
        this.P = P;
    }
}
