/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Matrice;
import fr.insa.winkler.projettreillis.Treillis;
import fr.insa.winkler.projettreillis.TypeBarre;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import recup.Lire;

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
      
        MenuItem menuItemCB = new MenuItem("Créer");
        MenuItem menuItemMB = new MenuItem("Modifier");
        MenuItem menuItemSB = new MenuItem("Supprimer");
        bBarre.getItems().addAll(menuItemCB, menuItemMB, menuItemSB);  
        Menu menuItemCN = new Menu ("Créer");
        MenuItem menuItemMN = new MenuItem("Modifier");
        MenuItem menuItemSN = new MenuItem("Supprimer");
        mbNoeud.getItems().addAll(menuItemCN, menuItemMN, menuItemSN);
        MenuItem menuItemCT = new MenuItem("Créer");
        MenuItem menuItemMT = new MenuItem("Modifier");
        MenuItem menuItemST = new MenuItem("Supprimer");
        bTerrain.getItems().addAll(menuItemCT, menuItemMT, menuItemST);
        Button valider=new Button("Valider");
        MenuButton typeBarre = new MenuButton("Type de Barre");
        if (model.getCatalogue().getListe().size()!=0){
            for(int i=0; i<model.getCatalogue().getListe().size();i++){
            MenuItem it=new MenuItem(model.getCatalogue().getListe().get(i).toString());
            typeBarre.getItems().addAll(it);  
            }
        }else{
            MenuItem it=new MenuItem("Aucun type disponible"); 
            typeBarre.getItems().addAll(it);  
            }  
        MenuButton noeuds = new MenuButton("Noeuds existants");
        if(model.getListeNoeuds().size()!=0){
        for(int i=0; i<model.getListeNoeuds().size();i++){
            MenuItem it=new MenuItem(model.getListeNoeuds().get(i).toString());
            noeuds.getItems().addAll(it);    
        }
        }else{
            MenuItem it=new MenuItem("Aucun noeud disponible"); 
            noeuds.getItems().addAll(it);  
        }
          
          
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
                Label identifiant=new Label ("identifiant du Noeud");
                Label forcesx = new Label ("force Px");
                Label forcesy = new Label ("force Py");
                TextField Px = new TextField();
                TextField Py = new TextField();
                VBox test = new VBox (forcesx, Px, forcesy, Py,valider);
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
       
         menuItemCT.setOnAction (new EventHandler<ActionEvent>() {
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
                 menuItemMT.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Modifier cliqué");
            }
        });  
                        menuItemST.setOnAction (new EventHandler<ActionEvent>() {
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
        menuItemCN.getItems().addAll(noeudSimple, noeudAppui);
 
        noeudSimple.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("NoeudSimple cliqué");
                vbGauche.getChildren().clear();
                Label identifiant= new Label ("Identifiant");
                Label abscisse = new Label ("Abscisse ");
                Label ordonnee = new Label ("Ordonnée");
                TextField id=new TextField();
                TextField x = new TextField(); 
                TextField y = new TextField();
                HBox ident= new HBox(identifiant, id);
                HBox abs = new HBox (abscisse, x);
                HBox ord = new HBox (ordonnee, y);
                vbGauche.getChildren().add(ident);
                vbGauche.getChildren().add(abs);
                vbGauche.getChildren().add(ord);
                vbGauche.getChildren().add(valider);
                valider.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                controleur.changeEtat(0);
                controleur.boutonValider();
            }
                });
            }
        });
                
    
       noeudAppui.setOnAction (new EventHandler<ActionEvent>() {
           public void handle (ActionEvent t){
                System.out.println("NoeudAppui cliqué");
                vbGauche.getChildren().clear(); 
                RadioButton appuiDouble = new RadioButton("AppuiDouble");
                RadioButton appuiSimple = new RadioButton("AppuiSimple");
                ToggleGroup appui = new ToggleGroup();
                appuiDouble.setToggleGroup(appui);
                appuiSimple.setToggleGroup(appui);
                Label identifiant= new Label ("Identifiant");
                Label abscisse = new Label ("Abscisse ");
                Label ordonnee = new Label ("Ordonnée");
                TextField id=new TextField();
                TextField x = new TextField(); 
                TextField y = new TextField();
                HBox ident= new HBox(identifiant, id);
                HBox abs = new HBox (abscisse, x);
                HBox ord = new HBox (ordonnee, y);
                VBox choixAppui = new VBox(appuiDouble, appuiSimple);
                vbGauche.getChildren().add(choixAppui);
                vbGauche.getChildren().add(ident);
                vbGauche.getChildren().add(abs);
                vbGauche.getChildren().add(ord);
                vbGauche.getChildren().add(valider);
                valider.setOnAction (new EventHandler<ActionEvent>() {
                public void handle (ActionEvent t){
                controleur.changeEtat(0);
                controleur.boutonValider();
            }
                });
          }
       });
        
    menuItemCB.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Créer cliqué");
                vbGauche.getChildren().clear();
                Label identifiant= new Label ("identifiant");
                TextField id = new TextField();
                Label identifiantType=new Label ("Identifiant du type :");
                TextField idType= new TextField();
                Label identifiantNoeudDebut = new Label ("Identifiant du noeud début :");
                TextField idND=new TextField();
                Label identifiantNoeudFin = new Label ("Identifiant du noeud de fin:  ");
                TextField idNF=new TextField();
                HBox ident=new HBox(identifiant, id);
                VBox barre = new VBox(ident,typeBarre,identifiantType,idType,noeuds,identifiantNoeudDebut,idND,
                        identifiantNoeudFin,idNF);
                vbGauche.getChildren().add(barre);
                vbGauche.getChildren().add(valider);
                valider.setOnAction (new EventHandler<ActionEvent>() {
                public void handle (ActionEvent t){
                controleur.changeEtat(0);
                controleur.boutonValider();
            }
                });
            }
        });
       menuItemMB.setOnAction (new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t){
                System.out.println("Modifier cliqué");
            }
        });
          menuItemSB.setOnAction (new EventHandler<ActionEvent>() {
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