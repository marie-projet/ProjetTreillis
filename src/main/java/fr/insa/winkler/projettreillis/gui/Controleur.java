/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Noeud;
import fr.insa.winkler.projettreillis.Point;
import fr.insa.winkler.projettreillis.Treillis;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

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

   public void clicDansZoneDessin(MouseEvent t) {
       double px = t.getX();
       double py = t.getY();
      Treillis model = this.vue.getModel();
      // il faudra ajouter des noeuds (et pas des points) en fonctions de l'état du controleur
      model.add(new Point (px, py));
      this.vue.redrawAll();
    }
}



