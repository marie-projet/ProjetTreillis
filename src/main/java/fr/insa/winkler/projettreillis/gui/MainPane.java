/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Matrice;
import fr.insa.winkler.projettreillis.Treillis;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private MenuButton bTerrain;
    private MenuButton bBarre;
    private Button bForce; 
    private Button bCatalogueBarre;
    private MenuButton mbNoeud;   
    private DessinCanvas cDessin; 

    public MainPane(Treillis model){
        this.model=model;
        this.controleur= new Controleur(this);
        this.bCalcul= new Button("Calculer");
        this.bBarre=new MenuButton ("Barre");
        this.bCatalogueBarre=new Button("Catalogue de barres");
        this.bForce = new Button ("Forces");
        this.bTerrain=new MenuButton ("Terrain");
        this.bEnregistrer = new Button ("Enregistrer");
        this.mbNoeud=new MenuButton("Noeud");
      
        MenuItem menuItemC = new MenuItem("Créer");
        MenuItem menuItemM = new MenuItem("Modifier");
        MenuItem menuItemS = new MenuItem("Supprimer");
        bBarre.getItems().addAll(menuItemC, menuItemM, menuItemS);  
        Menu menuItemC3 = new Menu ("Créer");
        MenuItem menuItemM3 = new MenuItem("Modifier");
        MenuItem menuItemS3 = new MenuItem("Supprimer");
        mbNoeud.getItems().addAll(menuItemC3, menuItemM3, menuItemS3);
        MenuItem menuItemC2 = new MenuItem("Créer");
        MenuItem menuItemM2 = new MenuItem("Modifier");
        MenuItem menuItemS2 = new MenuItem("Supprimer");
        bTerrain.getItems().addAll(menuItemC2, menuItemM2, menuItemS2);
       
          
          
        VBox vbGauche = new VBox();
        vbGauche.setSpacing(10);
        this.setLeft(vbGauche);
        VBox vbDroite= new VBox(this.bCalcul, this.bEnregistrer);
        vbDroite.setSpacing(10);
        this.setRight(vbDroite);
        HBox boutons=new HBox(this.bTerrain, this.mbNoeud, this.bBarre, this.bCatalogueBarre, this.bForce);
        boutons.setSpacing(10);
        this.setTop(boutons);
        this.cDessin=new DessinCanvas (this);
        this.setCenter(this.cDessin);
        
        GraphicsContext context = this.cDessin.getVraiCanvas().getGraphicsContext2D();
        context.translate(300, 250);
        context.scale(50, 50);
    
        this.bCalcul.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Calculer cliqué");
                Matrice res=model.calculForces();
                for(int i=0; i<model.getListeBarres().size(); i++){
                    if ((res.getCoeffs(i,0)>model.getListeBarres().get(i).getType().getResistanceMaxTraction())
                        ||(-1*res.getCoeffs(i,0)>model.getListeBarres().get(i).getType().getResistanceMaxCompression())){
                            model.getListeBarres().get(i).dessine(context, Color.RED);
                            System.out.println(model.getListeBarres().get(i));
                    }
                }
            }
        }); 
    
        this.bEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Enregistrer cliqué");
            }
        }); 
        
        this.bForce.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent t){
                System.out.println("bouton Force cliqué ");
                vbGauche.getChildren().clear();
                Label forcesx = new Label ("forces qui s'exercent sur Px");
                Label forcesy = new Label ("forces qui s'exercent sur Py");
                TextField Px = new TextField();
                TextField Py = new TextField();
                Button Enregistrer = new Button ("Enregistrer");
                Button Enregistrer2 = new Button ("Enregistrer");
                VBox test = new VBox (forcesx, Px, Enregistrer, forcesy, Py, Enregistrer2);
                vbGauche.getChildren().add(test);
            }
        });
        
        this.bTerrain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton terrain cliqué"); 
            }
        }); 
       
         menuItemC2.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Créer cliqué");
                     vbGauche.getChildren().clear();
                Label abscisseMin = new Label("xmin");
                Label abscisseMax = new Label("xmax");
                Label ordonneeMin = new Label("ymin");
                Label ordonneeMax = new Label("ymax");
                Label triangleTerrain = new Label ("Triangle Terrrain");
                TextField xmin = new TextField();
                TextField xmax = new TextField();
                TextField ymax = new TextField();
                TextField ymin = new TextField();
                VBox test = new VBox (abscisseMin, xmin, abscisseMax, xmax, ordonneeMax, ymax, ordonneeMin, ymin);
                vbGauche.getChildren().add(test);
            }
        });
                 menuItemM2.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Modifier cliqué");
            }
        });  
                        menuItemS2.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Suprimer cliqué");
            }
        });
                        
        this.mbNoeud.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //indiquer dans la console que le bouton a été cliqué 
                System.out.println("bouton Noeud cliqué");
            }
        }); 
    MenuItem noeudSimple = new MenuItem("Noeud Simple");
          MenuItem noeudAppui = new MenuItem ("Noeud Appui");
                menuItemC3.getItems().addAll(noeudSimple, noeudAppui);
        menuItemC3.setOnAction(new EventHandler<ActionEvent>() {
              
            @Override
            public void handle (ActionEvent t){
                System.out.println("noeud simple cliqué");
                System.out.println("noeud appui cliqué");
                vbGauche.getChildren().clear();
            }
        });
        noeudSimple.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
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
                
    
       noeudAppui.setOnAction (new EventHandler<ActionEvent>() {
           public void handle (ActionEvent t){
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
            }
        }); 
        
    menuItemC.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Créer cliqué");
                 vbGauche.getChildren().clear();
                Label typeBarre = new Label("Type de Barre");
                //pas sure peut être mieux des Radio Boutons
                TextField zoneTexteTB = new TextField();
                Label identificateur= new Label ("rentrer un identificateur (nombre entier)");
                TextField zoneTexteId = new TextField();
                Label noeudDebut = new Label ("Noeud début");
                Label noeudFin = new Label ("Noeud fin");
                VBox barre = new VBox(identificateur, zoneTexteId, noeudDebut, noeudFin);
                vbGauche.getChildren().add(barre);
            }
        });
       menuItemM.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Modifier cliqué");
            }
        });
          menuItemS.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Suprimer cliqué");
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

    public MenuButton getbTerrain() {
        return bTerrain;
    }

    public MenuButton getbBarre() {
        return bBarre;
    }

    public MenuButton getMbNoeud() {
        return mbNoeud;
    }   
     
}
