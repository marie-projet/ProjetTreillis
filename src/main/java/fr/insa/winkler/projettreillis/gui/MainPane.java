/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.CatalogueBarres;
import fr.insa.winkler.projettreillis.Treillis;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    
     VBox vbGauche = new VBox(this.bBarre, this.bCalcul, this.bCatalogueBarre, this.bEnregistrer, this.bTerrain);
    this.setLeft(vbGauche);
      VBox vbDroite= new VBox(this.bCalcul, this.bEnregistrer);
        this.setRight(vbDroite);
    this.cDessin=new DessinCanvas (this);
       this.setCenter(this.cDessin);
       
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
           vbGauche.getChildren().clear();
        }
    }); 
   
     // Creation de MenuItems
       MenuItem menuItemTB = new MenuItem("Noeud simple");
       MenuItem menuItemEN = new MenuItem("Noeud appui");

       mbNoeud.getItems().addAll(menuItemTB, menuItemEN);
    HBox boutons = new HBox(this.bTerrain,this.mbNoeud, this.bBarre, this.bCatalogueBarre);
    this.mbNoeud = new MenuButton ("Noeud");
    this.mbNoeud.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Noeud cliqué");
        }
    }); 
    menuItemTB.setOnAction(new EventHandler<ActionEvent>() {
    public void handle (ActionEvent t){
        System.out.println("noeud simple cliqué");
     vbGauche.getChildren().clear();
         Label abscisse = new Label ("abscisse");
         Label ordonnee = new Label ("ordonnée");
         TextField x = new TextField(); 
         TextField y = new TextField();
        HBox test = new HBox (abscisse, x);
        HBox test2 = new HBox (ordonnee, y);
        VBox global = new VBox ( abscisse, ordonnee);
        VBox global2= new VBox (x , y );
        vbGauche.getChildren().add(test);
    }
    });
    menuItemEN.setOnAction (new EventHandler<ActionEvent>() {
        public void handle (ActionEvent t){
            System.out.println("noeud appui cliqué");
            vbGauche.getChildren().clear();
            RadioButton appuiDouble = new RadioButton();
            RadioButton appuiSimple = new RadioButton();
            VBox test = new VBox(appuiDouble, appuiSimple);
            vbGauche.getChildren().add(test);
        }
    });
    
    this.bBarre= new Button ("Barre");
    this.bBarre.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Barre cliqué");
           vbGauche.getChildren().clear();
           Label TypeBarre = new Label("Type de Barre");
           //pas sure peut être mieux des Radio Boutons
           TextField ZoneTexteTB = new TextField();
           Label identificateur= new Label ("rentrer un identificateur (nombre entier)");
           TextField ZoneTexteId = new TextField();
           Label NoeudDebut = new Label ("Noeud début");
           Label NoeudFin = new Label ("Noeud fin");
           VBox test3 = new VBox(identificateur, NoeudDebut, NoeudFin);
           vbGauche.getChildren().add(test3);
        }
    }); 
    this.bCatalogueBarre= new Button ("Catalogue Barre");
    this.bCatalogueBarre.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            //indiquer dans la console que le bouton a été cliqué 
           System.out.println("bouton Catalogue Barre cliqué");
           //vbGauche.getChildren().addRadioButton("ajouter la liste du batalogue de barre ");
        }
    }); 
   
    
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
