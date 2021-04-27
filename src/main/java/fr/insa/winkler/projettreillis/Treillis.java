/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import recup.Lire;

/**
 *
 * @author mariewinkler
 */
public class Treillis {
    private int identifiant;
    private List<Noeud> listeNoeuds;
    private List<Barre> listeBarres;
    private Terrain terrain;
    private CatalogueBarres catalogue;
    
public Treillis (int identifiant, Terrain terrain,CatalogueBarres catalogue){
    this.identifiant = identifiant;
    this.listeNoeuds = new ArrayList<Noeud>();
    this.listeBarres = new ArrayList<Barre>();
    this.terrain = terrain; 
    this.catalogue=catalogue;
}

    public int getIdentifiant() {
        return identifiant;
    }

    public List<Noeud> getListeNoeuds() {
        return listeNoeuds;
    }

    public List<Barre> getListeBarres() {
        return listeBarres;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public CatalogueBarres getCatalogue() {
        return catalogue;
    }
    

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setCatalogue(CatalogueBarres catalogue) {
        this.catalogue = catalogue;
    }
    

    public void ajouterNoeud(Figure f) {
        if (f.getGroupe() != this) {
            if (f.getGroupe() != null) {
                throw new Error("figure déja dans un autre groupe");
            }
            this.contient.add(f);
            f.setGroupe(this);
        }
    }
    
    public void ajouterBarre(Figure f) {
        if (f.getGroupe() != this) {
            if (f.getGroupe() != null) {
                throw new Error("figure déja dans un autre groupe");
            }
            this.contient.add(f);
            f.setGroupe(this);
        }
    }

    public void supprimerNoeud(Figure f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.contient.remove(f);
        f.setGroupe(null);
    }
    
    public void supprimerBarre(Barre b) {
    }

    public void removeAll() {
   
    }

    public void clear() {
        List<Noeud> toRemove = new ArrayList<>(this.listeNoeuds);
        this.removeAll(toRemove);
        List<Barre> toRemove2 = new ArrayList<>(this.listeBarres);
        this.removeAll(toRemove2);
    }
    
    public static Treillis treillisTest() {
    }
    
    public void menuTexte() {
        
    }
    public double maxX() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double max = this.contient.get(0).maxX();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).maxX();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }
    
    public double minX() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double min = this.contient.get(0).minX();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).minX();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }
     public double maxY() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double max = this.contient.get(0).maxY();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).maxY();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }

     public double minY() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double min = this.contient.get(0).minY();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).minY();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }
     public void dessine(GraphicsContext context) {
        for (Figure f : this.contient) {
            f.dessine(context);
        }
    }

     public void changeCouleur(Color value) {
        for (Figure f : this.contient) {
            f.changeCouleur(value);
        }
    }


    public void calculForces(){
        int ns=this.getListeNoeuds().size();
        int nb=this.getListeBarres().size();
        int nas=0;
        int nap=0;
        for(int i=0; i<this.listeNoeuds.size(); i++){
            if(this.listeNoeuds.get(i) instanceof AppuiSimple){
                nas=nas+1;
            }
        }
        for(int i=0; i<this.listeNoeuds.size(); i++){
            if(this.listeNoeuds.get(i) instanceof AppuiDouble){
                  nap=nap+1;
            }
        }
        if(2*ns!=nb+nas+nap){
            throw new Error("le treillis n'est pas isostatique");
        }           
        Matrice m= new Matrice (ns, ns+1);
            for(int i=0; i<this.getListeNoeuds().size(); i++){
                System.out.println("Quelle force Px s'exerce sur le noeud "+ i+" ?");
                m.setCoeffs(i*2-1,ns+1,Lire.d());
                System.out.println("Quelle force Py s'exerce sur le noeud "+ i+" ?");
                m.setCoeffs(i*2,ns+1,Lire.d());
                for(int n=0; n<nb; n++){
                    if((this.getListeNoeuds().get(i)==this.getListeBarres().get(n).getNoeudDebut())){
                        m.setCoeffs(i*2-1, n, Math.cos(getAngle(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(n).getNoeudFin().getPos())));
                        m.setCoeffs(i*2, n, Math.sin(getAngle(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(n).getNoeudFin().getPos())));
                    }
                    if( this.getListeNoeuds().get(i)==this.getListeBarres().get(n).getNoeudFin()){
                        m.setCoeffs(i*2-1, n, Math.cos(getAngle(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(n).getNoeudDebut().getPos())));
                        m.setCoeffs(i*2, n, Math.sin(getAngle(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(n).getNoeudDebut().getPos())));
                    }
                }
                int n=nb;
                while (n<m.getNbrCol()+1){
                    if(this.getListeNoeuds().get(i) instanceof AppuiSimple){
                        AppuiSimple ap= (AppuiSimple)this.getListeNoeuds().get(i);
                        int debut=ap.getPoint1();
                        int fin= ap.getPoint2();
                            if((debut==1) && (fin==2)){
                                if(ap.getTerrain().getPT0().getY()>=ap.getTerrain().getPT1().getY()){
                                    m.setCoeffs(i*2-1, n,Math.cos(getAngle(ap.getTerrain().getPT0(),ap.getTerrain().getPT1())+Math.PI/2));
                                    m.setCoeffs(i*2, n,Math.sin(getAngle(ap.getTerrain().getPT0(),ap.getTerrain().getPT1())+Math.PI/2));
                                }   
                                else{
                                    m.setCoeffs(i*2-1, n,Math.cos(getAngle(ap.getTerrain().getPT1(),ap.getTerrain().getPT0())+Math.PI/2));
                                    m.setCoeffs(i*2, n,Math.sin(getAngle(ap.getTerrain().getPT1(),ap.getTerrain().getPT0())+Math.PI/2));
                                }
                                    
                            }
                            if((debut==2) && (fin==3)){
                                if(ap.getTerrain().getPT1().getY()>=ap.getTerrain().getPT2().getY()){
                                    m.setCoeffs(i*2-1, n,Math.cos(getAngle(ap.getTerrain().getPT1(),ap.getTerrain().getPT2())+Math.PI/2));
                                    m.setCoeffs(i*2, n,Math.sin(getAngle(ap.getTerrain().getPT1(),ap.getTerrain().getPT2())+Math.PI/2));
                                }   
                                else{
                                    m.setCoeffs(i*2-1, n,Math.cos(getAngle(ap.getTerrain().getPT2(),ap.getTerrain().getPT1())+Math.PI/2));
                                    m.setCoeffs(i*2, n,Math.sin(getAngle(ap.getTerrain().getPT2(),ap.getTerrain().getPT1())+Math.PI/2));
                                }
                            }
                            if((debut==3) && (fin==1)){
                               if(ap.getTerrain().getPT2().getY()>=ap.getTerrain().getPT0().getY()){
                                    m.setCoeffs(i*2-1, n,Math.cos(getAngle(ap.getTerrain().getPT2(),ap.getTerrain().getPT0())+Math.PI/2));
                                    m.setCoeffs(i*2, n,Math.sin(getAngle(ap.getTerrain().getPT2(),ap.getTerrain().getPT0())+Math.PI/2));
                                }   
                                else{
                                    m.setCoeffs(i*2-1, n,Math.cos(getAngle(ap.getTerrain().getPT0(),ap.getTerrain().getPT2())+Math.PI/2));
                                    m.setCoeffs(i*2, n,Math.sin(getAngle(ap.getTerrain().getPT0(),ap.getTerrain().getPT2())+Math.PI/2));
                                }
                            }
                        }
                    n=n+1;
                    }
                    if(this.getListeNoeuds().get(i) instanceof AppuiDouble){
                        m.setCoeffs(i*2-1, n, 1);
                        n=n+1;
                        m.setCoeffs(i*2, n, 1);
                    }
                }
        if(m.subCols(0,m.getNbrCol()-2).determinant()==0){
            System.out.println("Le système a 0 ou une infinité de solutions");
        }
        else{
            m.descenteGauss();
            m.remontéeGauss().unitaire();
            System.out.println("Les solutions sont:");
            System.out.println(m.subCols(m.getNbrCol()-1,m.getNbrCol()-1).toString());
         }
    }

   
public static double getAngle(Point a, Point c) {  
double x;
double y;
double angle=0;
    if(a.getX()>c.getX()){
	 x=a.getX()-c.getX();
        if (a.getY()>c.getY()){
            y=a.getY()-c.getY();
            angle= -1*(Math.PI-Math.atan2(y, x));
        }
        if (c.getY()>=a.getY()){
            y=c.getY()-a.getY();
            angle= Math.PI-Math.atan2(y, x);
        }
    }
    if(c.getX()>=a.getY()){
	 x=c.getX()-a.getX();
        if (c.getY()>=a.getY()){
            y=c.getY()-a.getY();
            angle= Math.atan2(y, x);
        }
        if (a.getY()>c.getY()){
            y=a.getY()-c.getY();
            angle= -1*Math.atan2(y, x);
        }
    }
    return angle;
}
               

    }
   

 

