/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Treillis;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author mariewinkler
 */
public class Controleur {
    
    private MainPane vue;
    private int etat;
    
    public Controleur (MainPane vue){
        this.vue=vue;
    }
    public void changeEtat(int nouvelEtat) {
        if (nouvelEtat == 20) {
        } else if (nouvelEtat == 30) {
            // creation de points

            this.vue.redrawAll();
        } else if (nouvelEtat == 40) {
            // creation de segments étape 1
            this.vue.redrawAll();
        } else if (nouvelEtat == 41) {
            // creation de segments étape 2
        }
        this.etat = nouvelEtat;
    }
    
    public void clicDansZoneDessin(MouseEvent t) {
       double px = t.getX();
       double py = t.getY();
        Treillis model = this.vue.getModel();
        // il faudra ajouter des noeuds (et pas des points) en fonctions de l'état du controleur
        //model.add(new Point (px, py));
        this.vue.redrawAll();
    }
}



