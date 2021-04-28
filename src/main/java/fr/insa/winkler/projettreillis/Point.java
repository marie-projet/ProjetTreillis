/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import recup.Lire;

/**
 *
 * @author francois
 */
public class Point {
    
    public static double RAYON_IN_DRAW = 5;

    private double px;
    private double py;

    public Point(double px, double py) {
        this.px = px;
        this.py = py;
    }



    public Point() {
        this(0, 0);
    }

    /**
     * @return the px
     */
    public double getX() {
        return px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public double getY() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(double py) {
        this.py = py;
    }

    @Override
    public String toString() {
        return "(" + px + "," + py + ')';
    }

    public static Point demandePoint() {
        System.out.println("abscisse : ");
        double px = Lire.d();
        System.out.println("ordonnée : ");
        double py = Lire.d();
        return new Point(px, py);
    }


    public double maxX() {
        return this.px;
    }


    public double minX() {
        return this.px;
    }


    public double maxY() {
        return this.py;
    }


    public double minY() {
        return this.py;
    }


    public double distancePoint(Point p) {
        double dx = this.px - p.px;
        double dy = this.py - p.py;
        return Math.sqrt(dx*dx+dy*dy);

    }


    public void draw(GraphicsContext gc) {
        gc.fillOval(this.px-RAYON_IN_DRAW, this.py-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }

}
