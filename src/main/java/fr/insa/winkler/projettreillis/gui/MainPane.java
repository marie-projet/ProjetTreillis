/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.Barre;
import fr.insa.winkler.projettreillis.Matrice;
import fr.insa.winkler.projettreillis.Noeud;
import fr.insa.winkler.projettreillis.Point;
import fr.insa.winkler.projettreillis.Treillis;
import fr.insa.winkler.projettreillis.TriangleTerrain;
import fr.insa.winkler.projettreillis.TypeBarre;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private TextArea message;

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
        this.message=new TextArea();
        this.message.setMinHeight(60);
        this.message.setMaxHeight(60);
      
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
        MenuItem menuItemCC = new MenuItem("Créer un type");
        MenuItem menuItemSC = new MenuItem("Supprimer un type");
        mbCatalogue.getItems().addAll(menuItemCC, menuItemSC); 
        
        Button valider=new Button("Valider");
          
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
        this.setBottom(this.message);
                
        GraphicsContext context = this.cDessin.getVraiCanvas().getGraphicsContext2D();
        context.translate(300, 250);
        context.scale(50, 50);
        
        bZoneConstructible.setOnAction ((t) -> {
            controleur.changeEtat(10);
            vbGauche.getChildren().clear();
            Label abscisseMin = new Label("Abscisse minimale");
            Label abscisseMax = new Label("Abscisse maximale");
            Label ordonneeMin = new Label("Ordonnée minimale");
            Label ordonneeMax = new Label("Ordonnée maximale");
            TextField xmin = new TextField();
            TextField xmax = new TextField();
            TextField ymax = new TextField();
            TextField ymin = new TextField();
            vbGauche.getChildren().addAll(abscisseMin, xmin, abscisseMax, xmax,ordonneeMin, ymin, ordonneeMax, ymax,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(xmin.getText(),xmax.getText(),ymin.getText(), ymax.getText());
            });
        });
        
        Label identifiant= new Label ("Identifiant");
        TextField id=new TextField();
        HBox ident= new HBox(identifiant, id);
        
        menuItemCT.setOnAction ((t) -> {
            controleur.changeEtat(20);
            vbGauche.getChildren().clear();
            Label identifiantTriangle=new Label ("Identifiant du triangle de terrain :");
            TextField idTriangle= new TextField();
            Label abscisse1 = new Label ("Abscisse point 1");
            Label ordonnee1 = new Label ("Ordonnée point 1");
            Label abscisse2 = new Label ("Abscisse point 2");
            Label ordonnee2 = new Label ("Ordonnée point 2");
            Label abscisse3 = new Label ("Abscisse point 3");
            Label ordonnee3 = new Label ("Ordonnée point 3");
            TextField x1 = new TextField();
            TextField y1 = new TextField();
            TextField x2 = new TextField();
            TextField y2 = new TextField();
            TextField x3 = new TextField();
            TextField y3 = new TextField();
            vbGauche.getChildren().addAll(identifiantTriangle, idTriangle, abscisse1, x1, 
                    ordonnee1, y1, abscisse2, x2, ordonnee2, y2, abscisse3, x3, ordonnee3, y3,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(idTriangle.getText(),x1.getText(),y1.getText(),x2.getText(), y2.getText(),x3.getText(),y3.getText());
            });
        });
        
        menuItemMT.setOnAction ((t) -> {
            controleur.changeEtat(24);
            message.clear();
            vbGauche.getChildren().clear();
            Label triangle=new Label("Triangle à modifier");
            ComboBox<String> triangles=new ComboBox<String>();
            for (TriangleTerrain tr: model.getTerrain().getTriangles()){
                triangles.getItems().addAll(tr.toString());
            }
            Label abscisse1 = new Label ("Abscisse point 1");
            Label ordonnee1 = new Label ("Ordonnée point 1");
            Label abscisse2 = new Label ("Abscisse point 2");
            Label ordonnee2 = new Label ("Ordonnée point 2");
            Label abscisse3 = new Label ("Abscisse point 3");
            Label ordonnee3 = new Label ("Ordonnée point 3");
            TextField x1 = new TextField();
            TextField y1 = new TextField();
            TextField x2 = new TextField();
            TextField y2 = new TextField();
            TextField x3 = new TextField();
            TextField y3 = new TextField();
            vbGauche.getChildren().addAll(triangle,triangles, abscisse1, x1, 
                    ordonnee1, y1, abscisse2, x2, ordonnee2, y2, abscisse3, x3, ordonnee3, y3,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(triangles.getSelectionModel().getSelectedItem(),x1.getText(),y1.getText(),x2.getText(), y2.getText(),x3.getText(),y3.getText());
            });
        });
        
        menuItemST.setOnAction ((t) -> {
            controleur.changeEtat(28);
            vbGauche.getChildren().clear();
            Label triangle=new Label("Triangle à supprimer");
            ComboBox<String> triangles=new ComboBox<String>();
            for (TriangleTerrain tr: model.getTerrain().getTriangles()){
                triangles.getItems().addAll(tr.toString());
            }
            vbGauche.getChildren().addAll(triangle,triangles,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(triangles.getSelectionModel().getSelectedItem());
                triangles.getItems().clear();
                for (TriangleTerrain tr: model.getTerrain().getTriangles()){
                    triangles.getItems().addAll(tr.toString());
                }
            });
        });
        
            Label abscisse = new Label ("Abscisse");
            Label ordonnee = new Label ("Ordonnée");
            TextField x = new TextField(); 
            TextField y = new TextField();
            HBox abs = new HBox (abscisse, x);
            HBox ord = new HBox (ordonnee, y);
        noeudSimple.setOnAction ((t) -> {
            controleur.changeEtat(30);
            message.clear();
            vbGauche.getChildren().clear();
            vbGauche.getChildren().addAll(ident,abs,ord,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(id.getText(),x.getText(),y.getText());
            });
        });
                
        noeudAppui.setOnAction ((t) -> {
            message.clear();
            vbGauche.getChildren().clear();
            RadioButton appuiDouble = new RadioButton("AppuiDouble");
            RadioButton appuiSimple = new RadioButton("AppuiSimple");
            ToggleGroup appui = new ToggleGroup();
            appuiDouble.setToggleGroup(appui);
            appuiSimple.setToggleGroup(appui);
            VBox choixAppui = new VBox(appuiDouble, appuiSimple);
            Label triangle=new Label("Triangle");
            ComboBox<String> triangles=new ComboBox<String>();
            for (TriangleTerrain tr: model.getTerrain().getTriangles()){
                triangles.getItems().addAll(tr.toString());
            }
            Label noeud1=new Label("Extermité du segment surlequel placé l'appui");
            TextField n1= new TextField();
            Label position =new Label("Position sur le segment");
            TextField pos=new TextField();
            vbGauche.getChildren().addAll(choixAppui,ident,triangle,triangles,noeud1,n1,position,pos,valider);
            valider.setOnAction ((i) -> {
                if(appuiDouble.isSelected()){
                controleur.changeEtat(31);
                message.clear();
                controleur.valider(id.getText(),triangles.getSelectionModel().getSelectedItem(),n1.getText(),pos.getText());
            }
            if (appuiSimple.isSelected()){
                controleur.changeEtat(32);
                message.clear();
                controleur.valider(id.getText(),triangles.getSelectionModel().getSelectedItem(),n1.getText(),pos.getText());
            }
            });
        });
        
        menuItemMN.setOnAction((t) -> {
            controleur.changeEtat(34);
            message.clear();
            vbGauche.getChildren().clear();
            Label noeud = new Label ("Noeud à modifier :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeuds.getItems().addAll(n.toString());
            }
            vbGauche.getChildren().addAll(noeud,noeuds,abs,ord,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(noeuds.getSelectionModel().getSelectedItem(),x.getText(),y.getText());
            });
        });
        
        menuItemSN.setOnAction((t) -> {
            controleur.changeEtat(38);
            message.clear();
            vbGauche.getChildren().clear();
            Label noeud = new Label ("Noeud à supprimer :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeuds.getItems().addAll(n.toString());
            }
            vbGauche.getChildren().addAll(noeud,noeuds,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(noeuds.getSelectionModel().getSelectedItem());
                noeuds.getItems().clear();
                for(Noeud n:model.getListeNoeuds()){
                noeuds.getItems().addAll(n.toString());
            }
            });
        });
  
        menuItemCB.setOnAction ((t) -> {
            controleur.changeEtat(40);
            message.clear();
            vbGauche.getChildren().clear();
            Label type=new Label ("Type de barre:");
            ComboBox<String> types=new ComboBox<String>();
            for(TypeBarre b:model.getCatalogue().getListe()){
                types.getItems().addAll(b.toString());
            }
            Label noeudDebut = new Label ("Noeud de début :");
            ComboBox<String> noeudDeb=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeudDeb.getItems().addAll(n.toString());
            }
            Label noeudFin = new Label ("Noeud de fin:  ");
            ComboBox<String> noeudF=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeudF.getItems().addAll(n.toString());
            }
            vbGauche.getChildren().addAll(ident,type,types,noeudDebut,noeudDeb,noeudFin,noeudF,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(id.getText(),types.getSelectionModel().getSelectedItem(),noeudDeb.getSelectionModel().getSelectedItem(),
                        noeudF.getSelectionModel().getSelectedItem());
                
            });        
        });
        
        menuItemMB.setOnAction ((t) -> {
            controleur.changeEtat(44);
            message.clear();
            vbGauche.getChildren().clear();
            Label barre=new Label("Barre à modifier");
            ComboBox<String> barres=new ComboBox<String>();
            for(Barre b:model.getListeBarres()){
                barres.getItems().addAll(b.toString());
            }
            Label type=new Label ("Type de barre:");
            ComboBox<String> types=new ComboBox<String>();
            for(TypeBarre b:model.getCatalogue().getListe()){
                types.getItems().addAll(b.toString());
            }
            vbGauche.getChildren().addAll(barre,barres,type,types,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(barres.getSelectionModel().getSelectedItem(),types.getSelectionModel().getSelectedItem());
            });            
        });
        
        menuItemSB.setOnAction ((t) -> {
            message.clear();
            controleur.changeEtat(48);
            vbGauche.getChildren().clear();
            Label barre=new Label("Barre à supprimer");
            ComboBox<String> barres=new ComboBox<String>();
            for(Barre b:model.getListeBarres()){
                barres.getItems().addAll(b.toString());
            }
            vbGauche.getChildren().addAll(barre,barres,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(barres.getSelectionModel().getSelectedItem());
                barres.getItems().clear();
                for(Barre b:model.getListeBarres()){
                    barres.getItems().addAll(b.toString());
                }
            });
        });

            
        menuItemCC.setOnAction((t) -> {
            controleur.changeEtat(50);
            vbGauche.getChildren().clear();
            Label identifiantType = new Label("Identifiant du type :");
            TextField idType = new TextField();
            Label coutAuMetre = new Label("Coût au metre : ");
            TextField cAm = new TextField();
            Label longueurMin = new Label ("Longueur minimale de la barre : ");
            TextField lMin = new TextField();
            Label longueurMax = new Label ("Longueur maximale de la barre : ");
            TextField lMax = new TextField();
            Label resistanceTension = new Label ("Résistance maximale à la tension :");
            TextField resTen = new TextField();
            Label resistanceCompression = new Label ("Résistance maximale à la compression :");
            TextField resComp = new TextField();
            vbGauche.getChildren().addAll(identifiantType, idType, coutAuMetre, cAm,
                    longueurMin, lMin, longueurMax, lMax, resistanceTension, resTen, resistanceCompression, resComp,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(idType.getText(),cAm.getText(),lMin.getText(),lMax.getText(),resTen.getText(),
                        resComp.getText());
            });
        });
        
        menuItemSC.setOnAction((t) -> {
            controleur.changeEtat(58);
            message.clear();
            vbGauche.getChildren().clear();
            Label type=new Label ("Type de barre à supprimer:");
            ComboBox<String> types=new ComboBox<String>();
            for(TypeBarre b:model.getCatalogue().getListe()){
                types.getItems().addAll(b.toString());
            }
            vbGauche.getChildren().addAll(type,types,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(types.getSelectionModel().getSelectedItem());
                types.getItems().clear();
                for(TypeBarre b:model.getCatalogue().getListe()){
                    types.getItems().addAll(b.toString());
                }
            });
        });
                   
        this.bForce.setOnAction((t) -> {
            vbGauche.getChildren().clear();
            Label noeudsEx = new Label ("Noeuds :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeuds.getItems().addAll(n.toString());
            }
            Label forcex = new Label ("force Px");
            Label forcey = new Label ("force Py");
            TextField Px = new TextField();
            TextField Py = new TextField();
            HBox forcePx=new HBox(forcex,Px);
            HBox forcePy=new HBox(forcey,Py);
            vbGauche.getChildren().addAll(noeudsEx,noeuds,forcePx,forcePy,valider);
            valider.setOnAction ((i) -> {
                controleur.changeEtat(60);
              //  controleur.boutonValider();
            });
        });
        
        this.bCalcul.setOnAction((t) -> {
            controleur.changeEtat(100);
            controleur.calculer();
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

    public TextArea getMessage() {
        return message;
    }
     
}