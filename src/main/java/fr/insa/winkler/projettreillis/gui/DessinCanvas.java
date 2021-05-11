/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Treillis;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author elodieherrmann
 */
public class DessinCanvas extends Pane {
    
    
    private MainPane PanneauPrincipal;
 
    private Canvas vraiCanvas;
    
    public DessinCanvas (MainPane PanneauPrincipal){
        this.PanneauPrincipal= PanneauPrincipal;
        this.vraiCanvas= new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.vraiCanvas);
        this.vraiCanvas.heightProperty().bind(this.heightProperty());
        this.vraiCanvas.heightProperty().addListener((cl)-> {
          this.redrawAll();
        });
        this.vraiCanvas.widthProperty().bind(this.widthProperty());
       this.vraiCanvas.widthProperty().addListener((cl)-> {
           this.redrawAll();
        });
       this.vraiCanvas.setOnMouseClicked((t)-> {
           Controleur control = this.PanneauPrincipal.getControleur();
           control.clicDansZoneDessin(t);
       });
       this.redrawAll();
    }
    
    //méthode qui permet de reessiner si on change la taille de la fenêtre 
    public void redrawAll() {
        GraphicsContext context = this.vraiCanvas.getGraphicsContext2D();
        Treillis model = this.PanneauPrincipal.getModel();
        context.clearRect(-100, -100, this.getWidth(), this.getHeight());
        model.dessine(context);
         
    }

    public MainPane getPanneauPrincipal() {
        return PanneauPrincipal;
    }

    public Canvas getVraiCanvas() {
        return vraiCanvas;
    }
    
}
