/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariewinkler
 */
public class Terrain {
    private double xmin;
    private double xmax;
    private double ymin; 
    private double ymax;
    private List<TriangleTerrain> triangles;
    
public Terrain (double xmin, double xmax, double ymin, double ymax){
    this.xmin = xmin;
    this.xmax = xmax;
    this.ymin = ymin;
    this.ymax = ymax;
    this.triangles= new ArrayList();
}

    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    public void setYmin(double ymin) {
        this.ymin = ymin;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }

   
}
