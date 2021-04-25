/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author mariewinkler
 */
public class Controleur {
    
    private MainPane vue;

    private int etat;
    
    /*
    public void changeEtat(int nouvelEtat) {
    }
    
    void clicDansZoneDessin(MouseEvent t) {
    
    }
    void boutonSelect(ActionEvent t) {
        this.changeEtat(20);
    }

    void boutonPoints(ActionEvent t) {
        this.changeEtat(30);
    }

    void boutonSegments(ActionEvent t) {
        this.changeEtat(40);
    }

    private void activeBoutonsSuivantSelection() {
        if (this.selection.size() < 2) {
            this.vue.getbGrouper().setDisable(true);
        } else {
            this.vue.getbGrouper().setDisable(false);
        }
    }
}



