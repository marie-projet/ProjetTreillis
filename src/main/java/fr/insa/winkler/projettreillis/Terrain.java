/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        this.xmin=0.5;
        this.xmax=4;
        this.ymin=-5;
        this.ymax=1;
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
    
    public void dessine (GraphicsContext gc){
        gc.setStroke(Color.GREEN);
        gc.strokeLine(this.getXmin(),this.getYmin(),this.getXmin(),this.getYmax());
        gc.strokeLine(this.getXmin(),this.getYmax(),this.getXmax(),this.getYmax());
        gc.strokeLine(this.getXmax(),this.getYmax(),this.getXmax(),this.getYmin());
        gc.strokeLine(this.getXmax(),this.getYmin(),this.getXmin(),this.getYmin());
        for (int i=0; i<this.getTriangles().size(); i++){
            this.getTriangles().get(i).dessine(gc);
        }
    }
}
