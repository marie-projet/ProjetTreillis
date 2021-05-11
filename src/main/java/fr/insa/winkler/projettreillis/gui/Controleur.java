/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Matrice;
import fr.insa.winkler.projettreillis.Treillis;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
        this.etat = nouvelEtat;
    }
    
    public void valider(){
        if (this.etat==0){
        }
    }
    public void clicDansZoneDessin(MouseEvent t) {
       double px = t.getX();
       double py = t.getY();
        Treillis model = this.vue.getModel();
        // il faudra ajouter des noeuds (et pas des points) en fonctions de l'état du controleur
        //model.add(new Point (px, py));
        this.vue.redrawAll();
    }
    
    public void calculer (){
            Matrice res=vue.getModel().calculForces();
            for(int i=0; i<vue.getModel().getListeBarres().size(); i++){
                if ((res.getCoeffs(i,0)>vue.getModel().getListeBarres().get(i).getType().getResistanceMaxTraction())
                    ||(-1*res.getCoeffs(i,0)>vue.getModel().getListeBarres().get(i).getType().getResistanceMaxCompression())){
                        vue.getModel().getListeBarres().get(i).dessine(vue.getcDessin().getVraiCanvas().getGraphicsContext2D(), Color.RED);
                        System.out.println(vue.getModel().getListeBarres().get(i));
                }
            }
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}



