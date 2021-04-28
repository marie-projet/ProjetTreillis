/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Treillis;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * @author mariewinkler
 */
public class MainPane extends BorderPane{
    
    // définir le squelette de l'interface
    private Treillis model;
    private Controleur controleur; 

    
    
private Button bCalcul;
private Button bEnregistrer;    

private MenuButton mbTerrain;
private MenuButton mbNoeud;
private MenuButton mbBarre;
private MenuButton mbCatalogueBarre; 
      
private DessinCanvas cDessin; 

public MainPane(Treillis model){
    this.model=model;
    this.controleur= new Controleur(this);
    this.bCalcul= new Button("Calculer");
    this.bCalcul.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Calculer cliqué");
        }
    }); 
    this.bEnregistrer = new Button ("Enrigistrer");
    this.bEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Enregistrer cliqué");
        }
    }); 
    this.mbTerrain= new MenuButton("Terrain");
    this.mbTerrain.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton terrain cliqué");
        }
    }); 
    
     
    this.mbNoeud = new MenuButton ("Noeud");
    this.mbNoeud.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Noeud cliqué");
        }
    }); 
    this.mbBarre= new MenuButton ("Barre");
    this.mbBarre.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Barre cliqué");
        }
    }); 
    this.mbCatalogueBarre= new MenuButton ("Catalogue Barre");
    this.mbCatalogueBarre.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Catalogue Barre cliqué");
        }
    }); 
     // Creation de MenuItems
       MenuItem menuItemTB = new MenuItem("Noeud simple");
       MenuItem menuItemEN = new MenuItem("Noeuds appuis");
       
       mbNoeud.getItems().addAll(menuItemTB, menuItemEN);
       
      VBox vbDroite= new VBox(this.bCalcul, this.bEnregistrer);
        this.setRight(vbDroite);
      VBox vbGauche = new VBox();
        this.setLeft(vbGauche);
  HBox boutons = new HBox(this.mbTerrain,this.mbNoeud, this.mbBarre, this.mbCatalogueBarre);
      this.cDessin=new DessinCanvas (this);
       this.setCenter(this.cDessin);
}

  
public Treillis getModel() {
        return model;
    }

    public Controleur getControleur() {
        return controleur;
    }
    
}
