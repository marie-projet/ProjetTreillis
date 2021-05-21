/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis.gui;

import fr.insa.winkler.projettreillis.AppuiDouble;
import fr.insa.winkler.projettreillis.AppuiSimple;
import fr.insa.winkler.projettreillis.Charge;
import fr.insa.winkler.projettreillis.Matrice;
import fr.insa.winkler.projettreillis.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author mariewinkler
 */
public class Controleur {
    
    private MainPane vue;
    private int etat;
    
    public Controleur (MainPane vue){
        this.vue=vue;
    }
    public void changeEtat(int nouvelEtat) {
        this.etat = nouvelEtat;
    }
        public void valider(String s1){
        if(this.etat==28){
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            vue.getMessage().appendText(vue.getModel().supprimerTriangle(Integer.parseInt(a.get(1))));
            vue.getcDessin().redrawAll();
        }
        if (this.etat==38){
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            vue.getMessage().appendText(vue.getModel().supprimerNoeud(Integer.parseInt(a.get(1))));
            vue.getcDessin().redrawAll();
        }
        if (this.etat==48){
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            vue.getMessage().appendText(vue.getModel().supprimerBarre(Integer.parseInt(a.get(1))));
            vue.getcDessin().redrawAll();
        }
        if (this.etat==58){
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            vue.getMessage().appendText(vue.getModel().supprimerTypeBarre(Integer.parseInt(a.get(1))));
            vue.getcDessin().redrawAll();
        }
        if(this.etat==70){
            vue.getModel().Enregistrer(s1);
        }
    }
    public void valider(String s1, String s2){
        if(this.etat==35){
            System.out.println("test");
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            vue.getMessage().appendText(vue.getModel().modifierAppui(Integer.parseInt(a.get(1)),Double.parseDouble(s2)));
            vue.getcDessin().redrawAll();
        }
        if(this.etat==44){
            //modifier le type de la barre
        }
    }
    public void valider (String s1, String s2, String s3){
        if(etat==30){
            vue.getMessage().appendText(vue.getModel().ajouterNoeudSimple(Integer.parseInt(s1),new Point(Double.parseDouble(s2),
                Double.parseDouble(s3))));
            vue.getcDessin().redrawAll();
        }

        if(this.etat==34){
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            vue.getMessage().appendText(vue.getModel().modifierNoeud(Integer.parseInt(a.get(1)),Double.parseDouble(s2),Double.parseDouble(s3)));
            vue.getcDessin().redrawAll();
        }
        if(this.etat==60){
            List<String> a = new ArrayList<>();
            for (String s: s1.split(";")) {
                a.add(s);
            }
            for (int i=0; i<vue.getModel().getListeNoeuds().size(); i++){
                if(vue.getModel().getListeNoeuds().get(i).getIdentifiant()==Integer.parseInt(a.get(1))){
                    vue.getModel().getCharge().add(new Charge(Math.cos(Double.parseDouble(s3)*Math.PI/180)*Double.parseDouble(s2),
                            Math.sin(Double.parseDouble(s3)*Math.PI/180)*Double.parseDouble(s2),vue.getModel().getListeNoeuds().get(i)));
                }
            } 
        vue.getMessage().appendText("Charge ajoutée !");
        }
    }

    public void valider(String s1, String s2, String s3, String s4){
        if (this.etat==10){
            vue.getMessage().appendText(vue.getModel().modifierZC(Double.parseDouble(s1),Double.parseDouble(s2),
                    Double.parseDouble(s3),Double.parseDouble(s4)));
            vue.getcDessin().redrawAll();
        }
        if(this.etat==31){
            List<String> triangle = new ArrayList<>();
            for (String s: s2.split(";")) {
                triangle.add(s);
            }
            vue.getMessage().appendText(vue.getModel().ajouterAppuiDouble(Integer.parseInt(s1),
                    Integer.parseInt(triangle.get(1)),Integer.parseInt(s3)-1,Double.parseDouble(s4)));
            vue.getcDessin().redrawAll();
        }
        if(this.etat==32){
            List<String> triangle = new ArrayList<>();
            for (String s: s2.split(";")) {
                triangle.add(s);
            }
            vue.getMessage().appendText(vue.getModel().ajouterAppuiSimple(Integer.parseInt(s1),
                    Integer.parseInt(triangle.get(1)),Integer.parseInt(s3)-1,Double.parseDouble(s4)));
            vue.getcDessin().redrawAll();
        }
        if(this.etat==40){
            List<String> type = new ArrayList<>();
            for (String s: s2.split(";")) {
                type.add(s);
            }
            List<String> debut = new ArrayList<>();
            for (String s: s3.split(";")) {
                debut.add(s);
            }
            List<String> fin = new ArrayList<>();
            for (String s: s4.split(";")) {
                fin.add(s);
            }
            vue.getMessage().appendText(vue.getModel().ajouterBarre(Integer.parseInt(s1),
                    Integer.parseInt(type.get(1)),Integer.parseInt(debut.get(1)),Integer.parseInt(fin.get(1))));
            vue.getcDessin().redrawAll();
        }
    }
    
    public void valider (String s1, String s2, String s3, String s4, String s5, String s6){
        if(this.etat==50){
              vue.getMessage().appendText(vue.getModel().ajouterTypeBarre(Integer.parseInt(s1), Double.parseDouble(s2),Double.parseDouble(s3),
                      Double.parseDouble(s4),Double.parseDouble(s5),Double.parseDouble(s6)));
              vue.getcDessin().redrawAll();
        }
    }
    public void valider(String s1, String s2, String s3, String s4, String s5, String s6, String s7){
        if(this.etat==20){
            vue.getMessage().appendText(vue.getModel().ajouterTriangle(Integer.parseInt(s1),Double.parseDouble(s1),Double.parseDouble(s2),
                    Double.parseDouble(s3),Double.parseDouble(s4),Double.parseDouble(s5),Double.parseDouble(s6)));
            vue.getcDessin().redrawAll();
        }
       
    }
    
    public void calculer (){
        String mes="";
        int ns=vue.getModel().getListeNoeuds().size();
        int nb=vue.getModel().getListeBarres().size();
        int nas=0;
        int nap=0;
        for(int i=0; i<ns; i++){
            if(vue.getModel().getListeNoeuds().get(i) instanceof AppuiSimple){
                nas=nas+1;
            }
        }
        for(int i=0; i<ns; i++){
            if(vue.getModel().getListeNoeuds().get(i) instanceof AppuiDouble){
                  nap=nap+1;
            }
        }
        if((2*ns)!=(nb+nas+2*nap)){
            mes=mes+"Le treillis n'est pas isostatique"+"\n";
        }
        for(int i=0; i<ns; i++){
            if(vue.getModel().getListeNoeuds().get(i) instanceof AppuiSimple){
                int compt=0;
                AppuiSimple ap=(AppuiSimple) vue.getModel().getListeNoeuds().get(i);
                for (int j=0; j<nb; j++){
                    if(vue.getModel().getListeBarres().get(j).getNoeudDebut()==ap){
                        compt=compt+1;
                    } 
                    if(vue.getModel().getListeBarres().get(j).getNoeudFin()==ap){
                        compt=compt+1;
                    } 
                }
                if(compt<2){
                    mes=mes+"L'appui simple peut roulerr"+"÷\n";
                }
            }
        }
        if (mes==""){
            Matrice res=vue.getModel().calculForces();
            for(int i=0; i<vue.getModel().getListeBarres().size(); i++){
                if (res.getCoeffs(i,0)>vue.getModel().getListeBarres().get(i).getType().getResistanceMaxTraction()){
                    vue.getModel().getListeBarres().get(i).dessine(vue.getcDessin().getVraiCanvas().getGraphicsContext2D(), Color.RED);
                    vue.getMessage().appendText("La barre "+vue.getModel().getListeBarres().get(i).getIdentifiant()+
                        " est soumise à une traction trop importante ("+res.getCoeffs(i,0)+"N)"+"\n");
                }
                if(-1*res.getCoeffs(i,0)>vue.getModel().getListeBarres().get(i).getType().getResistanceMaxCompression()){
                    vue.getModel().getListeBarres().get(i).dessine(vue.getcDessin().getVraiCanvas().getGraphicsContext2D(), Color.RED);
                    vue.getMessage().appendText("La barre "+vue.getModel().getListeBarres().get(i).getIdentifiant()+
                        " est soumise à une compression trop importante ("+-res.getCoeffs(i,0)+"N)"+"\n");
                }
            }
        }
        vue.getMessage().appendText(mes);
    }
    
    public void enregistrer(String nom){
        vue.getModel().Enregistrer(nom);
    }
    public void zoomDouble() {
        this.vue.setZoneModelVue(this.vue.getZoneModelVue().scale(0.5));
        this.vue.redrawAll();
    }

    public void zoomDemi() {
        this.vue.setZoneModelVue(this.vue.getZoneModelVue().scale(2));
        this.vue.redrawAll();
    }

    public void zoomFitAll() {
        this.vue.fitAll();
        this.vue.redrawAll();
    }

    public void translateGauche() {
         this.vue.setZoneModelVue(this.vue.getZoneModelVue().translateGauche(0.8));
        this.vue.redrawAll();
   }

    public void translateDroite() {
         this.vue.setZoneModelVue(this.vue.getZoneModelVue().translateDroite(0.8));
        this.vue.redrawAll();
   }

    public void translateHaut() {
         this.vue.setZoneModelVue(this.vue.getZoneModelVue().translateHaut(0.8));
        this.vue.redrawAll();
   }

    public void translateBas() {
         this.vue.setZoneModelVue(this.vue.getZoneModelVue().translateBas(0.8));
        this.vue.redrawAll();
   }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    
}



