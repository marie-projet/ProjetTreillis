/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;
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
    
    public Terrain(){
        this.xmin=-100;
        this.xmax=100;
        this.ymin=-100;
        this.ymax=100;
        this.triangles= new ArrayList<TriangleTerrain>();
    }
    
    public Terrain (double xmin, double xmax, double ymin, double ymax){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.triangles= new ArrayList<TriangleTerrain>();
    }
    
    public String toString(){
    String res="ZoneConstructible;"+this.getXmin()+";"+this.getXmax()+";"+this.getYmin()+";"+this.getYmax()+"\n";
    for(int i=0; i<this.getTriangles().size(); i++){
        res=res+this.getTriangles().get(i).toString()+"\n";
    }
    return res;  
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

    public List<TriangleTerrain> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<TriangleTerrain> triangles) {
        this.triangles = triangles;
    } 
}
