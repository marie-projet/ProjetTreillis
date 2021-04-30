/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.CatalogueBarres;
import fr.insa.winkler.projettreillis.Treillis;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
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

    
 
private Button abscisse=new Button("Abscisse");
private Button ordonéeeeee=new Button("Ordonnéeeeeeee");
private Button bCalcul;
private Button bEnregistrer;   
private Button bTerrain;
private Button bBarre;
private Button bCatalogueBarre;

private MenuButton mbNoeud;

      
private DessinCanvas cDessin; 

public MainPane(){
    
}

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
    this.bEnregistrer = new Button ("Enregistrer");
    this.bEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Enregistrer cliqué");
        }
    }); 
    this.bTerrain= new Button("Terrain");
    this.bTerrain.setOnAction(new EventHandler<ActionEvent>() {
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
    this.bBarre= new Button ("Barre");
    this.bBarre.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Barre cliqué");
           vbGauche.getChildren().addButton("type de Barres");
        }
    }); 
    this.bCatalogueBarre= new Button ("Catalogue Barre");
    this.bCatalogueBarre.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Catalogue Barre cliqué");
           vbGauche.getChildren().addRadioButton("ajouter la liste du batalogue de barre ");
        }
    }); 
    
  
     // Creation de MenuItems
       MenuItem menuItemTB = new MenuItem("Noeud simple");
       MenuItem menuItemEN = new MenuItem("Noeuds appuis");

       mbNoeud.getItems().addAll(menuItemTB, menuItemEN);
       
      VBox vbDroite= new VBox(this.bCalcul, this.bEnregistrer);
        this.setRight(vbDroite);
        HBox boutons = new HBox(this.mbTerrain,this.mbNoeud, this.mbBarre, this.mbCatalogueBarre);
      this.cDessin=new DessinCanvas (this);
       this.setCenter(this.cDessin);
}

public void redrawAll(){
    this.cDessin.redrawAll();
}

  
public Treillis getModel() {
        return model;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public Button getbTerrain() {
        return bTerrain;
    }

    public Button getbBarre() {
        return bBarre;
    }

    public MenuButton getMbNoeud() {
        return mbNoeud;
    }

   
    
    
}
