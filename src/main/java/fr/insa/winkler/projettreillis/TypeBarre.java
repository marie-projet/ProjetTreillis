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

public  class TypeBarre {
    private int identificateur;
    private double coutAuMetre;
    private double longueurMin;
    private double longueurMax;
    private double resistanceMaxTraction;
    private double resistanceMaxCompression;
  
    public TypeBarre (int id,double cout, double longueurMin, double longueurMax, double traction, double compression){
        this.identificateur=id;
        this.coutAuMetre = cout;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTraction = traction;
        this.resistanceMaxCompression = compression;
    }
    
    public TypeBarre(){
        this.identificateur=1;
        this.coutAuMetre = 10;
        this.longueurMin = 1;
        this.longueurMax = 100;
        this.resistanceMaxTraction = 100;
        this.resistanceMaxCompression = 100;
    }
  
    public String toString(){
        return "TypeBarre;"+this.getIdentificateur()+";"+this.getCoutAuMetre()+";"+this.getLongueurMin()+";"+this.getLongueurMax()+";"
              +this.resistanceMaxTraction+";"+this.getResistanceMaxCompression();
    }

    public double getCoutAuMetre() {
        return coutAuMetre;
    }

    public double getLongueurMin() {
        return longueurMin;
    }

    public double getLongueurMax() {
        return longueurMax;
    }

    public double getResistanceMaxTraction() {
        return resistanceMaxTraction;
    }

    public double getResistanceMaxCompression() {
        return resistanceMaxCompression;
    }

    public int getIdentificateur() {
        return identificateur;
    }
    

    public void setCoutAuMetre(double coutAuMetre) {
        this.coutAuMetre = coutAuMetre;
    }

    public void setLongueurMin(double longueurMin) {
        this.longueurMin = longueurMin;
    }

    public void setLongueurMax(double longueurMax) {
        this.longueurMax = longueurMax;
    }

    public void setResistanceMaxTraction(double resistanceMaxTraction) {
        this.resistanceMaxTraction = resistanceMaxTraction;
    }

    public void setResistanceMaxCompression(double resistanceMaxCompression) {
        this.resistanceMaxCompression = resistanceMaxCompression;
    } 

    public void setIdentificateur(int identificateur) {
        this.identificateur = identificateur;
    }
    
}
