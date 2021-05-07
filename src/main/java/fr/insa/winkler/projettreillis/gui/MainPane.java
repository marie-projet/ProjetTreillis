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
    private Treillis model;
    private Controleur controleur;  
    private Button bCalcul;
    private Button bEnregistrer;   
    private Button bZoneConstructible;
    private MenuButton mbTriangle;
    private MenuButton mbBarre;
    private Button bForce; 
    private MenuButton mbCatalogue;
    private MenuButton mbNoeud;   
    private DessinCanvas cDessin; 

    public MainPane(Treillis model){
        this.model=model;
        this.controleur= new Controleur(this);
        this.bCalcul= new Button("Calculer");
        this.mbBarre=new MenuButton ("Barre");
        this.mbCatalogue=new MenuButton("Catalogue de barres");
        this.bForce = new Button ("Forces");
        this.bZoneConstructible=new Button ("Zone Constructible");
        this.mbTriangle=new MenuButton("Triangles");
        this.bEnregistrer = new Button ("Enregistrer");
        this.mbNoeud=new MenuButton("Noeud");
      
        MenuItem menuItemCT = new MenuItem("Créer");
        MenuItem menuItemMT = new MenuItem("Modifier");
        MenuItem menuItemST = new MenuItem("Supprimer");
        mbTriangle.getItems().addAll(menuItemCT,menuItemMT,menuItemST);
        MenuItem menuItemCB = new MenuItem("Créer");
        MenuItem menuItemMB = new MenuItem("Modifier");
        MenuItem menuItemSB = new MenuItem("Supprimer");
        mbBarre.getItems().addAll(menuItemCB, menuItemMB, menuItemSB);  
        Menu menuCN = new Menu ("Créer");
        MenuItem menuItemMN = new MenuItem("Modifier");
        MenuItem menuItemSN = new MenuItem("Supprimer");
        mbNoeud.getItems().addAll(menuCN, menuItemMN, menuItemSN);
        MenuItem noeudSimple = new MenuItem("Noeud Simple");
        MenuItem noeudAppui = new MenuItem ("Noeud Appui");
        menuCN.getItems().addAll(noeudSimple, noeudAppui);
        MenuItem menuItemCC = new MenuItem("Créer");
        MenuItem menuItemSC = new MenuItem("Supprimer");
        mbCatalogue.getItems().addAll(menuItemCC, menuItemSC); 
        
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
        HBox entete=new HBox(this.bZoneConstructible,this.mbTriangle, this.mbNoeud, this.mbBarre, this.mbCatalogue, this.bForce);
        entete.setSpacing(10);
        this.setTop(entete);
        this.cDessin=new DessinCanvas (this);
        this.setCenter(this.cDessin);
        
        GraphicsContext context = this.cDessin.getVraiCanvas().getGraphicsContext2D();
        context.translate(300, 250);
        context.scale(50, 50);
        
        bZoneConstructible.setOnAction ((t) -> {
            System.out.println("Créer cliqué");
            vbGauche.getChildren().clear();
            Label abscisseMin = new Label("xmin");
            Label abscisseMax = new Label("xmax");
            Label ordonneeMin = new Label("ymin");
            Label ordonneeMax = new Label("ymax");
            TextField xmin = new TextField();
            TextField xmax = new TextField();
            TextField ymax = new TextField();
            TextField ymin = new TextField();
            VBox test = new VBox (abscisseMin, xmin, abscisseMax, xmax, ordonneeMax, ymax, ordonneeMin, ymin,valider);
                vbGauche.getChildren().add(test);
        });
        
        Label identifiant= new Label ("Identifiant");
        TextField id=new TextField();
        HBox ident= new HBox(identifiant, id);
        
        menuItemCT.setOnAction ((t) -> {
            //id, trois points, valider
        });
        menuItemMT.setOnAction ((t) -> {
            //trianglesexistants, id, 3 points
        });
        menuItemST.setOnAction ((t) -> {
            //triangles existants, id
        });

        Label abscisse = new Label ("Abscisse ");
        Label ordonnee = new Label ("Ordonnée");
        TextField x = new TextField(); 
        TextField y = new TextField();
        HBox abs = new HBox (abscisse, x);
        HBox ord = new HBox (ordonnee, y);
        
        noeudSimple.setOnAction ((t) -> {
            vbGauche.getChildren().clear();
            vbGauche.getChildren().add(ident);
            vbGauche.getChildren().add(abs);
            vbGauche.getChildren().add(ord);
            vbGauche.getChildren().add(valider);
            valider.setOnAction ((i) -> {
                controleur.changeEtat(0);
                controleur.boutonValider();
            });
        });
                

        noeudAppui.setOnAction ((t) -> {
            vbGauche.getChildren().clear();
            RadioButton appuiDouble = new RadioButton("AppuiDouble");
            RadioButton appuiSimple = new RadioButton("AppuiSimple");
            ToggleGroup appui = new ToggleGroup();
            appuiDouble.setToggleGroup(appui);
            appuiSimple.setToggleGroup(appui);
            VBox choixAppui = new VBox(appuiDouble, appuiSimple);
            vbGauche.getChildren().add(choixAppui);
            vbGauche.getChildren().add(ident);
            vbGauche.getChildren().add(abs);
            vbGauche.getChildren().add(ord);
            vbGauche.getChildren().add(valider);
            valider.setOnAction ((i) -> {
                controleur.changeEtat(0);
                controleur.boutonValider();
            });
        });
        menuItemMN.setOnAction((t) -> {
            //noeudsexistants,id, coordonées, valider
        });
        menuItemSN.setOnAction((t) -> {
            //noeudsexistants,id,valider
        });
  
        menuItemCB.setOnAction ((t) -> {
            vbGauche.getChildren().clear();
            Label identifiantType=new Label ("Identifiant du type :");
            TextField idType= new TextField();
            Label identifiantNoeudDebut = new Label ("Identifiant du noeud début :");
            TextField idND=new TextField();
            Label identifiantNoeudFin = new Label ("Identifiant du noeud de fin:  ");
            TextField idNF=new TextField();
            VBox barre = new VBox(ident,typeBarre,identifiantType,idType,noeuds,identifiantNoeudDebut,idND,
                        identifiantNoeudFin,idNF);
            vbGauche.getChildren().add(barre);
            vbGauche.getChildren().add(valider);
            valider.setOnAction ((i) -> {
                controleur.changeEtat(0);
                controleur.boutonValider();
            });
        });
        menuItemMB.setOnAction ((t) -> {
            //barresexistantes,id, noeuddeb,noeudfin,valider
        });
        menuItemSB.setOnAction ((t) -> {
            //barresexistantes,id,valider
        });

            
        menuItemCC.setOnAction((t) -> {
            //id, cout au metre,...,valider
        });
        menuItemSC.setOnAction((t) -> {
            //typesexistants,id,valider
        });
                   
        this.bForce.setOnAction((t) -> {
            System.out.println("bouton Force cliqué ");
            vbGauche.getChildren().clear();
            Label identifiantNoeud=new Label ("identifiant du Noeud");
            Label forcesx = new Label ("force Px");
            Label forcesy = new Label ("force Py");
            TextField idN =new TextField();
            TextField Px = new TextField();
            TextField Py = new TextField();
            HBox forcesPx=new HBox(forcesx,Px);
            HBox forcesPy=new HBox(forcesy,Py);
            VBox forces = new VBox (noeuds,identifiantNoeud,idN,forcesPx,forcesPy,valider);
            vbGauche.getChildren().add(forces);
            valider.setOnAction ((i) -> {
                controleur.changeEtat(0);
                controleur.boutonValider();
            });
        });
        
        this.bCalcul.setOnAction((t) -> {
            Matrice res=model.calculForces();
            for(int i=0; i<model.getListeBarres().size(); i++){
                if ((res.getCoeffs(i,0)>model.getListeBarres().get(i).getType().getResistanceMaxTraction())
                    ||(-1*res.getCoeffs(i,0)>model.getListeBarres().get(i).getType().getResistanceMaxCompression())){
                        model.getListeBarres().get(i).dessine(context, Color.RED);
                        System.out.println(model.getListeBarres().get(i));
                }
            }
        }); 
    
        this.bEnregistrer.setOnAction((t) -> {
        }); 
    }

    public void redrawAll(){
        this.cDessin.redrawAll();
    }

    public Button getbCalcul() {
        return bCalcul;
    }

    public Button getbEnregistrer() {
        return bEnregistrer;
    }

    public MenuButton getMbTriangle() {
        return mbTriangle;
    }

    public MenuButton getMbBarre() {
        return mbBarre;
    }

    public Button getbForce() {
        return bForce;
    }

    public MenuButton getMbCatalogue() {
        return mbCatalogue;
    }

    public DessinCanvas getcDessin() {
        return cDessin;
    }
    
    public Treillis getModel() {
        return model;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public Button getbZoneConstructible() {
        return bZoneConstructible;
    }

    public MenuButton getbBarre() {
        return mbBarre;
    }

    public MenuButton getMbNoeud() {
        return mbNoeud;
    }   
     
}