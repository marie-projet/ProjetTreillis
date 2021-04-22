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

    public int getXmin() {
        return xmin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getYmin() {
        return ymin;
    }

    public int getYmax() {
        return ymax;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

   
}
