/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;
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
    
public Treillis(){
    this.identifiant=1;
    this.listeNoeuds = new ArrayList<Noeud>();
    this.listeBarres = new ArrayList<Barre>();
    this.terrain = new Terrain(); 
    this.catalogue=new CatalogueBarres();
}    

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
    
    /*public void getNbrNoeuds (List listeNoeuds)
    {
        this.getListeNoeuds();
        int nbrNoeuds = new int nbrNoeuds;
        
    } */
    
    
    // ça sert bien à rajouter un noeud à un treillis?
    public void ajouterNoeudSimple (Point p){
        System.out.println("Saisissez l'identifiant du Noeud");
        NoeudSimple n = new NoeudSimple(Lire.i(),p);
        this.getListeNoeuds().add(n);
    }
    public void ajouterAppuiSimple(Point p, TriangleTerrain t){
        System.out.println("Saisissez l'identifiant du Noeud");
        AppuiSimple a = new AppuiSimple(Lire.i(),t,p);
        this.getListeNoeuds().add(a);
    }
    public void ajouterAppuiDouble(Point p, TriangleTerrain t){
        System.out.println("Saisissez l'identifiant du Noeud");
        AppuiDouble a = new AppuiDouble(Lire.i(),t,p);
        this.getListeNoeuds().add(a);
    }
    
    // marche à condition de rentrer comme identifiant 1,2,3 rien d'autre (pour que la fonction supprimer tout marche)
    // sélectionner le type de barre dans le catalogue 
    public void ajouterBarre (Noeud n1,Noeud n2){
        System.out.println("Saisissez l'indentifiant de la barre");
         int id = Lire.i();
         System.out.println("Choisissez le type de barre");
         TypeBarre type= choisiType();
         Barre b = new Barre(id,n1,n2,type);
        this.getListeBarres().add(b);
    }
    
    public TypeBarre choisiType() {
        List<TypeBarre> lp = new ArrayList<>();
        System.out.println("liste des points disponibles : ");
        int nbr = this.getCatalogue().getListe().size();
        for (int i = 0; i < this.getCatalogue().getListe().size(); i++) {
            TypeBarre t = this.getCatalogue().getListe().get(i);
                System.out.println(i + ") " + t);
            }
        
        if (nbr == 0) {
            System.out.println("Aucun type dans le catalogue");
            return null;
        } else {
            int rep = -1;
            while (rep < 0 || rep > nbr) {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0) {
                return null;
            } else {
                return lp.get(rep);
            }
        }
    }
     
     public void supprimerNoeud (Noeud n){
         this.getListeNoeuds().remove(n);
     }
     
     public void supprimerBarre (Barre b){
         this.getListeBarres().remove(b);
     }
     
     // pas besoin de mettre i+1, les listes fonctionnent comme les tableux !
     public void supprimerTout (){
         for (int i=0; i<this.listeNoeuds.size() ;i++){
             this.getListeNoeuds().remove(i);
         }
         for (int i =0 ; i<this.listeBarres.size() ; i++){
             this.getListeBarres().remove(i);
         }
             
     }
/*
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
    */
    public static Treillis treillisTest() {
        Treillis test=new Treillis();
        Point p0= new Point(0,-3);
        Point p1=new Point(0,1);
        Point p2=new Point(-1,-1);
        TriangleTerrain t1= new TriangleTerrain(1,p0,p1,p2);
        Point p6= new Point(1,-1);
        AppuiDouble ap2=new AppuiDouble(1,t1,0,1,0.75);
        AppuiSimple ap=new AppuiSimple(2,t1,0,1,0.25);
        NoeudSimple n=new NoeudSimple(3,p6);
        test.getTerrain().getTriangles().add(t1);
        test.getListeNoeuds().add(ap2);
        test.getListeNoeuds().add(ap);
        test.getListeNoeuds().add(n);
        Barre b1= new Barre(ap2,n);
        Barre b2=new Barre(ap,n);
        Barre b3=new Barre(ap2,ap);
        test.getCatalogue().add(b1.getType());
        test.getListeBarres().add(b1);
        test.getListeBarres().add(b2);
        test.getListeBarres().add(b3);
        return test;
    }
    /*
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

*/
    public void calculForces(){
        int ns=this.getListeNoeuds().size();
        int nb=this.getListeBarres().size();
        int nas=0;
        int nap=0;
        double epsilon=Math.pow(10, -8);
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
        if((2*ns)!=(nb+nas+2*nap)){
            throw new Error("le treillis n'est pas isostatique");
        }           
        Matrice m= new Matrice (2*ns, (2*ns)+1);
        int n=nb;
            while(n<m.getNbrCol()-2){
            for(int i=0; i<this.getListeNoeuds().size(); i++){
                System.out.println("Quelle force Px s'exerce sur le noeud "+ i+" ?");
                m.setCoeffs(i*2,2*ns,-1*Lire.d());
                System.out.println("Quelle force Py s'exerce sur le noeud "+ i+" ?");
                m.setCoeffs(i*2+1,2*ns,-1*Lire.d());
               // System.out.println(m);
               //ajouter un condition if val<epsilon val=0
                for(int j=0; j<nb; j++){
                    if((this.getListeNoeuds().get(i)==this.getListeBarres().get(j).getNoeudDebut())){
                        m.setCoeffs(i*2, j, Math.cos(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(j).getNoeudFin().getPos())));
                        m.setCoeffs(i*2+1, j, Math.sin(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(j).getNoeudFin().getPos())));
                    }
                    if( this.getListeNoeuds().get(i)==this.getListeBarres().get(j).getNoeudFin()){
                  //      System.out.println("debug 4");
                        m.setCoeffs(i*2, j, Math.cos(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(j).getNoeudDebut().getPos())));
                        m.setCoeffs(i*2+1, j, Math.sin(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),this.getListeBarres().get(j).getNoeudDebut().getPos())));
                    }
                }
               // System.out.println("debug 1");
                    if(this.getListeNoeuds().get(i) instanceof AppuiSimple){
                        AppuiSimple ap= (AppuiSimple)this.getListeNoeuds().get(i);
                        m.setCoeffs(i*2, n, Math.cos(ap.getAngleBeta()));
                        m.setCoeffs(i*2, n+1, Math.sin(ap.getAngleBeta()));
                        }
                    
                    if(this.getListeNoeuds().get(i) instanceof AppuiDouble){
                        m.setCoeffs(i*2, n, 1);
                        n=n+1;
                        m.setCoeffs(i*2+1, n, 1);
                        n=n+1;
                    }
                }
            }
            for (int i=0; i<m.getNbrLig(); i++){
                for (int j=0; j<m.getNbrCol(); j++)
                    if(Math.abs(m.getCoeffs(i,j))<epsilon){
                        m.setCoeffs(i,j,0);
                    }
            }
            
            System.out.println(m);
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
    
/**
 * 
 * @param a Point de référence (milieu de l'angle)
 * @param c Deuxième Point
 * @return l'angle Ox,ac
 */
 
public static double getAngleAlpha(Point a, Point c) {  
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
    if(c.getX()>a.getY()){
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
    if(a.getX()==c.getX()){
        if(a.getY()>c.getY()){
            return -Math.PI/2;
        }
        else{
            return Math.PI/2;
        }
    }
    return angle;
    }

}
   

 

