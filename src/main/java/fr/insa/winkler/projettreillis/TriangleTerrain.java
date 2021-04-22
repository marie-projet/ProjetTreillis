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
    private point PT1;
    private point PT2;
    private point PT3;
    private point P;
    
public TriangleTerrain (int identificateur, point PT1, point PT2, point PT3, point P){
    this.identificateur = identificateur; 
    this.PT1 = PT1;
    this.PT2 = PT2;
    this.PT3 = PT3;
    this.P = P;
}

    public int getIdentificateur() {
        return identificateur;
    }

    public point getPT1() {
        return PT1;
    }

    public point getPT2() {
        return PT2;
    }

    public point getPT3() {
        return PT3;
    }

    public point getP() {
        return P;
    }

    public void setIdentificateur(int identificateur) {
        this.identificateur = identificateur;
    }

    public void setPT1(point PT1) {
        this.PT1 = PT1;
    }

    public void setPT2(point PT2) {
        this.PT2 = PT2;
    }

    public void setPT3(point PT3) {
        this.PT3 = PT3;
    }

    public void setP(point P) {
        this.P = P;
    }
}
