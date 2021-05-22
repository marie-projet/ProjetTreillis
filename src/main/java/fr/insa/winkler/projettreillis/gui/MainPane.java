/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.AppuiDouble;
import fr.insa.winkler.projettreillis.AppuiSimple;
import fr.insa.winkler.projettreillis.Barre;
import fr.insa.winkler.projettreillis.Noeud;
import fr.insa.winkler.projettreillis.NoeudSimple;
import fr.insa.winkler.projettreillis.Treillis;
import fr.insa.winkler.projettreillis.TriangleTerrain;
import fr.insa.winkler.projettreillis.TypeBarre;
import java.io.File;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author mariewinkler
 */
public class MainPane extends BorderPane{
    private Treillis model;
    private File file;
    private Controleur controleur;  
    private Button bCalcul;  
    private Button bZoneConstructible;
    private MenuButton mbFichier;
    private MenuButton mbTriangle;
    private MenuButton mbBarre;
    private MenuButton mbCharge; 
    private MenuButton mbCatalogue;
    private MenuButton mbNoeud;   
    private DessinCanvas cDessin; 
    private TextArea message; 
    private Icone bZoomDouble;
    private Icone bZoomDemi;
    private Icone bZoomFitAll;
    private Icone bTranslateGauche;
    private Icone bTranslateDroite;
    private Icone bTranslateHaut;
    private Icone bTranslateBas;
    private Rectangle zoneModelVue;
    private static double MULT_POUR_FIT_ALL = 2.7;
    private Stage stage;
    
    public MainPane(Stage inStage) {
        this(inStage, new Treillis());
    }

    public MainPane(Stage st,Treillis model){
        this(st,null,model);
    }
    public MainPane(Stage st,File f,Treillis model){
        this.stage=st;
        this.file=f;
        this.model=model;
        this.controleur= new Controleur(this);
        this.bCalcul= new Button("Calculer");
        this.mbBarre=new MenuButton ("Barre");
        this.mbCatalogue=new MenuButton("Catalogue de barres");
        this.mbCharge = new MenuButton ("Charges");
        this.bZoneConstructible=new Button ("Zone Constructible");
        this.mbTriangle=new MenuButton("Triangles");
        this.mbNoeud=new MenuButton("Noeud");
        this.message=new TextArea();
        this.message.setMinHeight(60);
        this.message.setMaxHeight(60);
        this.mbFichier=new MenuButton("Fichier");
        this.fitAll();
      
        MenuItem menuItemCT = new MenuItem("Créer");
        MenuItem menuItemST = new MenuItem("Supprimer");
        mbTriangle.getItems().addAll(menuItemCT,menuItemST);
        MenuItem menuItemCB = new MenuItem("Créer");
        MenuItem menuItemMB = new MenuItem("Modifier");
        MenuItem menuItemSB = new MenuItem("Supprimer");
        mbBarre.getItems().addAll(menuItemCB, menuItemMB, menuItemSB);  
        Menu menuCN = new Menu ("Créer");
        Menu menuMN = new Menu("Modifier");
        MenuItem menuItemSN = new MenuItem("Supprimer");
        mbNoeud.getItems().addAll(menuCN,menuMN, menuItemSN);
        MenuItem noeudSimple = new MenuItem("Noeud Simple");
        MenuItem noeudAppui = new MenuItem ("Noeud Appui");
        menuCN.getItems().addAll(noeudSimple, noeudAppui);
        MenuItem noeudSimple2 = new MenuItem("Noeud Simple");
        MenuItem noeudAppui2 = new MenuItem ("Noeud Appui");
        menuMN.getItems().addAll(noeudSimple2, noeudAppui2);
        MenuItem menuItemCC = new MenuItem("Créer un type");
        MenuItem menuItemSC = new MenuItem("Supprimer un type");
        mbCatalogue.getItems().addAll(menuItemCC, menuItemSC); 
        MenuItem menuItemCCh=new MenuItem("Ajouter charge");
        MenuItem menuItemSCh=new MenuItem("Supprimer charge");
        mbCharge.getItems().addAll(menuItemCCh,menuItemSCh);
        Button valider=new Button("Valider");
        MenuItem menuItemN=new MenuItem("Nouveau...");
        MenuItem menuItemO=new MenuItem("Ouvrir...");
        MenuItem menuItemS=new MenuItem("Sauvergarder...");
        MenuItem menuItemSS=new MenuItem("Sauvergarder sous...");
        mbFichier.getItems().addAll(menuItemN, menuItemO,menuItemS,menuItemSS);
        this.bZoomDouble = new Icone("icones/zoomer.png",32,32);
        this.bZoomDouble.setOnAction((t) -> {
            this.controleur.zoomDouble();
        });
        this.bZoomDemi = new Icone("icones/dezoomer.png",32,32);
        this.bZoomDemi.setOnAction((t) -> {
            this.controleur.zoomDemi();
        });
        this.bZoomFitAll = new Icone("icones/agrandir.png",32,32);
        this.bZoomFitAll.setOnAction((t) -> {
            this.controleur.zoomFitAll();
        });
        
        this.bTranslateGauche = new Icone("icones/gauche.png",32,32);
        this.bTranslateGauche.setOnAction((t) -> {
            this.controleur.translateGauche();
        });
        this.bTranslateDroite = new Icone("icones/droite.png",32,32);
       this.bTranslateDroite.setOnAction((t) -> {
            this.controleur.translateDroite();
        });
         this.bTranslateHaut = new Icone("icones/haut.png",32,32);
        this.bTranslateHaut.setOnAction((t) -> {
            this.controleur.translateHaut();
        });
        this.bTranslateBas = new Icone("icones/bas.png",32,32);
       this.bTranslateBas.setOnAction((t) -> {
            this.controleur.translateBas();
        });
         
        HBox hbZoom = new HBox(this.bZoomDouble, this.bZoomDemi, this.bZoomFitAll);
        
        GridPane gpTrans = new GridPane();
        gpTrans.add(this.bTranslateGauche, 0, 1,1,1);
        gpTrans.add(this.bTranslateDroite, 2, 1,1,1);
        gpTrans.add(this.bTranslateHaut, 1, 0,1,1);
        gpTrans.add(this.bTranslateBas, 1, 2,1,1);
        
        VBox vbZoom = new VBox(hbZoom,gpTrans);
        vbZoom.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
          
        VBox vbGauche = new VBox();
        vbGauche.setSpacing(10);
        this.setLeft(vbGauche);
        VBox vbDroite= new VBox(this.bCalcul,vbZoom);
        vbDroite.setSpacing(100);
        this.setRight(vbDroite);
        HBox entete=new HBox(this.bZoneConstructible,this.mbTriangle, this.mbNoeud, this.mbBarre, this.mbCatalogue, this.mbCharge);
        VBox haut=new VBox(mbFichier,entete);
        entete.setSpacing(10);
        this.setTop(haut);
        this.cDessin=new DessinCanvas (this);
        this.setCenter(this.cDessin);
        this.setBottom(this.message);
                
        GraphicsContext context = this.cDessin.getVraiCanvas().getGraphicsContext2D();

        
        bZoneConstructible.setOnAction ((t) -> {
            controleur.changeEtat(10);
            message.clear();
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
            message.clear();
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
        
        menuItemST.setOnAction ((t) -> {
            controleur.changeEtat(28);
            message.clear();
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
        
        noeudSimple2.setOnAction ((t) -> {
            controleur.changeEtat(34);
            message.clear();
            vbGauche.getChildren().clear();
            Label noeud = new Label ("Noeud à modifier :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                if(n instanceof NoeudSimple){
                noeuds.getItems().addAll(n.toString());
                }
            }
            vbGauche.getChildren().addAll(noeud,noeuds,abs,ord,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(noeuds.getSelectionModel().getSelectedItem(),x.getText(),y.getText());
            });
        });
        
        noeudAppui2.setOnAction((t) -> {
            controleur.changeEtat(35);
            message.clear();
            vbGauche.getChildren().clear();
            Label noeud = new Label ("Noeud à modifier :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                if((n instanceof AppuiSimple)||(n instanceof AppuiDouble)){
                    noeuds.getItems().addAll(n.toString());
                }
            }
            Label position =new Label("Position sur le segment");
            TextField pos=new TextField();
            vbGauche.getChildren().addAll(noeud,noeuds,position,pos,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.valider(noeuds.getSelectionModel().getSelectedItem(),pos.getText());
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
                controleur.changeEtat(44);
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
            message.clear();
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
        
        menuItemCCh.setOnAction((t) -> {
            vbGauche.getChildren().clear();
            this.message.clear();
            Label noeudsEx = new Label ("Noeuds :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeuds.getItems().addAll(n.toString());
            }
            Label norme = new Label ("Norme de la charge:");
            Label angle = new Label ("Angle en degrés que fait la charge avce l'horizontale:");
            TextField n = new TextField();
            TextField a = new TextField();
            vbGauche.getChildren().addAll(noeudsEx,noeuds,norme, n,angle,a,valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.changeEtat(60);
                controleur.valider(noeuds.getSelectionModel().getSelectedItem(), n.getText(),a.getText());
            });
        });
        
        menuItemSCh.setOnAction((t)-> {
            vbGauche.getChildren().clear();
            this.message.clear();
            Label noeudsEx = new Label ("Noeuds :");
            ComboBox<String> noeuds=new ComboBox<String>();
            for(Noeud n:model.getListeNoeuds()){
                noeuds.getItems().addAll(n.toString());
            }
            vbGauche.getChildren().addAll(noeudsEx, noeuds, valider);
            valider.setOnAction ((i) -> {
                message.clear();
                controleur.changeEtat(62);
                controleur.valider(noeuds.getSelectionModel().getSelectedItem());
            });
        });
        
        this.bCalcul.setOnAction((t) -> {
            message.clear();
            controleur.changeEtat(100);
            controleur.calculer();
        }); 
    
        menuItemS.setOnAction((t) -> {
            message.clear();
            controleur.sauvegarder(t);
        });
        menuItemSS.setOnAction((t) -> {
            message.clear();
            controleur.sauvegarderSous(t);
        });
        menuItemO.setOnAction((t)->{
            message.clear();
            controleur.ouvrir(t);
        });
        menuItemN.setOnAction((t)->{
            message.clear();
            controleur.nouveau(t);
        });
    }
    
    public void fitAll() {
        this.zoneModelVue = new Rectangle(this.model.minX(),
                this.model.maxX(), this.model.minY()+3, this.model.maxY()+3);
        this.zoneModelVue = this.zoneModelVue.scale(MULT_POUR_FIT_ALL);
    }

    public void redrawAll(){
        this.cDessin.redrawAll();
    }

    public Button getbCalcul() {
        return bCalcul;
    }

    public MenuButton getMbTriangle() {
        return mbTriangle;
    }

    public MenuButton getMbBarre() {
        return mbBarre;
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


    public Icone getbZoomDouble() {
        return bZoomDouble;
    }

    public Icone getbZoomDemi() {
        return bZoomDemi;
    }

    public Icone getbZoomFitAll() {
        return bZoomFitAll;
    }

    public Icone getbTranslateGauche() {
        return bTranslateGauche;
    }

    public Icone getbTranslateDroite() {
        return bTranslateDroite;
    }

    public Icone getbTranslateHaut() {
        return bTranslateHaut;
    }

    public Icone getbTranslateBas() {
        return bTranslateBas;
    }

    public Rectangle getZoneModelVue() {
        return zoneModelVue;
    }

    public void setModel(Treillis model) {
        this.model = model;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void setbCalcul(Button bCalcul) {
        this.bCalcul = bCalcul;
    }


    public void setbZoneConstructible(Button bZoneConstructible) {
        this.bZoneConstructible = bZoneConstructible;
    }

    public void setMbTriangle(MenuButton mbTriangle) {
        this.mbTriangle = mbTriangle;
    }

    public void setMbBarre(MenuButton mbBarre) {
        this.mbBarre = mbBarre;
    }


    public void setMbCatalogue(MenuButton mbCatalogue) {
        this.mbCatalogue = mbCatalogue;
    }

    public void setMbNoeud(MenuButton mbNoeud) {
        this.mbNoeud = mbNoeud;
    }

    public void setcDessin(DessinCanvas cDessin) {
        this.cDessin = cDessin;
    }

    public void setMessage(TextArea message) {
        this.message = message;
    }


    public void setbZoomDouble(Icone bZoomDouble) {
        this.bZoomDouble = bZoomDouble;
    }

    public void setbZoomDemi(Icone bZoomDemi) {
        this.bZoomDemi = bZoomDemi;
    }

    public void setbZoomFitAll(Icone bZoomFitAll) {
        this.bZoomFitAll = bZoomFitAll;
    }

    public void setbTranslateGauche(Icone bTranslateGauche) {
        this.bTranslateGauche = bTranslateGauche;
    }

    public void setbTranslateDroite(Icone bTranslateDroite) {
        this.bTranslateDroite = bTranslateDroite;
    }

    public void setbTranslateHaut(Icone bTranslateHaut) {
        this.bTranslateHaut = bTranslateHaut;
    }

    public void setbTranslateBas(Icone bTranslateBas) {
        this.bTranslateBas = bTranslateBas;
    }

    public void setZoneModelVue(Rectangle zoneModelVue) {
        this.zoneModelVue = zoneModelVue;
    }

    public MenuButton getMbCharge() {
        return mbCharge;
    }

    public static double getMULT_POUR_FIT_ALL() {
        return MULT_POUR_FIT_ALL;
    }

    public Stage getStage() {
        return stage;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
  
}