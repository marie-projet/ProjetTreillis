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
public abstract class TypeBarre {
  private double coutAuMetre;
  private double longueurMin;
  private double longueurMax;
  private double resistanceMaxTraction;
  private double resistanceMaxCompression;
  
  public TypeBarre (double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTraction, double resistanceMaxCompression){
      this.coutAuMetre = coutAuMetre;
      this.longueurMin = longueurMin;
      this.longueurMax = longueurMax;
      this.resistanceMaxTraction = resistanceMaxTraction;
      this.resistanceMaxCompression = resistanceMaxCompression;
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
}
