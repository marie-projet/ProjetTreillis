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
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

/**
 *
 * @author elodieherrmann
 */
public class DessinCanvas extends Pane {
    
    private MainPane PanneauPrincipal;
    private Canvas vraiCanvas;
    private Rectangle asRect;
    
    public DessinCanvas (MainPane PanneauPrincipal){
        this.PanneauPrincipal= PanneauPrincipal;
        this.asRect = new Rectangle(0, 0, this.getWidth(), this.getHeight());
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
       });
       this.redrawAll();
    }
    public void concatenateTransform(Transform trans) {
        Transform oldTrans = this.vraiCanvas.getGraphicsContext2D().getTransform();
        Transform newTrans = oldTrans.createConcatenation(trans);
        this.setTransform(newTrans);
    }

    public void setTransform(Transform trans) {
        this.vraiCanvas.getGraphicsContext2D().setTransform(new Affine(trans));
    }

    public Transform getTransform() {
        return this.vraiCanvas.getGraphicsContext2D().getTransform();
    }
    
    //méthode qui permet de reessiner si on change la taille de la fenêtre 
    public void redrawAll() {
        GraphicsContext context = this.vraiCanvas.getGraphicsContext2D();
        context.setTransform(new Affine());
        Treillis model = this.PanneauPrincipal.getModel();
        context.clearRect(-100, -100, this.getWidth()*2, this.getHeight()*2);
        this.asRect.setxMax(this.vraiCanvas.getWidth());
        this.asRect.setyMax(this.vraiCanvas.getHeight());
        Transform curTrans = this.PanneauPrincipal.getZoneModelVue().fitTransform(this.asRect);
        this.setTransform(curTrans);
        model.dessine(context);     
    }

    public MainPane getPanneauPrincipal() {
        return PanneauPrincipal;
    }

    public Canvas getVraiCanvas() {
        return vraiCanvas;
    }    
}
