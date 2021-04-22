/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

/**
 *
 * @author mariewinkler
 */
public abstract class Appui extends Noeud{
    
    
    private TriangleTerrain terrain;
    private int point1;
    private int point2;
    private double position;
    
    public Appui (int identificateur, TriangleTerrain terrain, int point1, int point2, double position) {
        super(identificateur);
        this.terrain= terrain;
        this.point1=point1;
        this.point2=point2;
        this.position=position;
    }

    public TriangleTerrain getTerrain() {
        return terrain;
    }

    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }

    public double getPosition() {
        return position;
    }

    public void setTerrain(TriangleTerrain terrain) {
        this.terrain = terrain;
    }

    public void setPoint1(int point1) {
        this.point1 = point1;
    }

    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    public void setPosition(double position) {
        this.position = position;
    }
    
}
