/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.beuvron.cours.m2.pasapaslb.dessin;

import java.awt.Color;

/**
 *
 * @author francois
 */
public abstract class FigureSimple implements Figure{
    
    private Color couleurContour;
    
    public FigureSimple(Color couleur) {
        this.couleurContour = couleur;
    }

    /**
     * @return the couleurContour
     */
    public Color getCouleurContour() {
        return couleurContour;
    }

    /**
     * @param couleurContour the couleurContour to set
     */
    public void setCouleurContour(Color couleurContour) {
        this.couleurContour = couleurContour;
    }
    
    
    
}
