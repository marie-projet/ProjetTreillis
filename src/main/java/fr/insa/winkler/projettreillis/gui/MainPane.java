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

    public MainPane(Treillis model){
        this.model=model;
        this.controleur= new Controleur(this);
        this.bCalcul= new Button("Calculer");
        this.bBarre=new Button ("Barre");
        this.bCatalogueBarre=new Button("Catalogue de barres");
        this.bTerrain=new Button ("Terrain");
        this.bEnregistrer = new Button ("Enregistrer");
        this.mbNoeud=new MenuButton("Noeud");
        MenuItem menuItemTB = new MenuItem("Noeud simple");
        MenuItem menuItemEN = new MenuItem("Noeud appui");
        mbNoeud.getItems().addAll(menuItemTB, menuItemEN);
    
        VBox vbGauche = new VBox();
        vbGauche.setSpacing(10);
        this.setLeft(vbGauche);
        VBox vbDroite= new VBox(this.bCalcul, this.bEnregistrer);
        vbDroite.setSpacing(10);
        this.setRight(vbDroite);
        HBox boutons=new HBox(this.bTerrain, this.mbNoeud, this.bBarre, this.bCatalogueBarre);
        boutons.setSpacing(10);
        this.setTop(boutons);
        this.cDessin=new DessinCanvas (this);
        this.setCenter(this.cDessin);
    
        this.bCalcul.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Calculer cliqué");
            }
        }); 
    
        this.bEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Enregistrer cliqué");
            }
        }); 

        this.bTerrain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton terrain cliqué");
                vbGauche.getChildren().clear();
            }
        }); 

        this.mbNoeud.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Noeud cliqué");
            }
        }); 
    
        menuItemTB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent t){
                System.out.println("noeud simple cliqué");
                vbGauche.getChildren().clear();
                Label abscisse = new Label ("Abscisse ");
                Label ordonnee = new Label ("Ordonnée");
                TextField x = new TextField(); 
                TextField y = new TextField();
                HBox abs = new HBox (abscisse, x);
                HBox ord = new HBox (ordonnee, y);
                vbGauche.getChildren().add(abs);
                vbGauche.getChildren().add(ord);
            }
        });
    
        menuItemEN.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("noeud appui cliqué");
                vbGauche.getChildren().clear();
                RadioButton appuiDouble = new RadioButton("AppuiDouble");
                RadioButton appuiSimple = new RadioButton("AppuiSimple");
                VBox test = new VBox(appuiDouble, appuiSimple);
                vbGauche.getChildren().add(test);
            }
        });
    
        this.bBarre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Barre cliqué");
                vbGauche.getChildren().clear();
                Label typeBarre = new Label("Type de Barre");
                //pas sure peut être mieux des Radio Boutons
                TextField zoneTexteTB = new TextField();
                Label identificateur= new Label ("rentrer un identificateur (nombre entier)");
                TextField zoneTexteId = new TextField();
                Label noeudDebut = new Label ("Noeud début");
                Label noeudFin = new Label ("Noeud fin");
                VBox barre = new VBox(identificateur, noeudDebut, noeudFin);
                vbGauche.getChildren().add(barre);
            }
        }); 

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
