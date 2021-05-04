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
    
    public String toString(){
        String res="";
        res=res+this.getTerrain().toString()+"FINTRIANGLES"+"\n";
        for(int i=0; i<this.getCatalogue().getListe().size(); i++){
            res=res+this.getCatalogue().getListe().get(i).toString()+"\n";
        }
        res=res+"FINCATALOGUE"+"\n";
        for(int i=0; i<this.getListeNoeuds().size();i++){
            res=res+this.getListeNoeuds().get(i).toString()+"\n";
        }
        res=res+"FINNOEUDS"+"\n";
        for (int i=0; i<this.getListeBarres().size();i++){
            res=res+this.getListeBarres().get(i).toString()+"\n";
        }
        res=res+"FINBARRES";
        return res;
    }
    
    /**
     * crée un NoeudSimple à partir d'un point
     * @param p Point (position du Noeud)
     * reste a faire: vérifier qu'aucun noeud ne possède cet identifiant
     */
    public void ajouterNoeudSimple (Point p){
        for(int i=0; i<this.getTerrain().getTriangles().size();i++){
            if(this.getTerrain().getTriangles().get(i).estDansTriangle(p)==true){
                throw new Error("le point est dans un triangle terrain");
            }
            if((p.getX()<this.getTerrain().getXmin())||(p.getX()>this.getTerrain().getXmax())
                    ||(p.getY()<this.getTerrain().getYmin())|| (p.getY()>this.getTerrain().getYmax())){
                throw new Error("le point n'est pas dans la zone contructible");
            }
        }
        System.out.println("Saisissez l'identifiant du Noeud");
        NoeudSimple n = new NoeudSimple(Lire.i(),p);
        this.getListeNoeuds().add(n);
    }
     /**
     * créee un AppuiSimple à partir d'un point et d'un TriangleTerrain
     * @param p Point (position du noeud)
     * @param t TraingleTerrain
     * reste a faire: vérifier qu'aucun noeud ne possède cet identifiant
     */
    public void ajouterAppuiSimple(Point p, TriangleTerrain t){
        if(t.estDansTriangle(p)==false){
            throw new Error("le point n'est pas dans le triangle terrain");
        }
        System.out.println("Saisissez l'identifiant du Noeud");
        AppuiSimple a = new AppuiSimple(Lire.i(),t,p);
        this.getListeNoeuds().add(a);
    }
    /**
     * créee un AppuiDouble à partir d'un point et d'un TriangleTerrain
     * @param p Point (position du noeud)
     * @param t TraingleTerrain
     * reste a faire: vérifier qu'aucun noeud ne possède cet identifiant
     */
    public void ajouterAppuiDouble(Point p, TriangleTerrain t){
        if(t.estDansTriangle(p)==false){
            throw new Error("le point n'est pas dans le triangle terrain");
        }
        System.out.println("Saisissez l'identifiant du Noeud");
        AppuiDouble a = new AppuiDouble(Lire.i(),t,p);
        this.getListeNoeuds().add(a);
    }
    
    /**
    * crée une nouvelle barre à partir de deux noeuds
    * @param n1 Noeud 1
    * @param n2 Noeud 2
    * reste a faire: vérifier qu'aucune barre ne possède cet identifiant
    */
    public void ajouterBarre (Noeud n1,Noeud n2){
        System.out.println("Saisissez l'indentifiant de la barre");
        int id = Lire.i();
        System.out.println("Choisissez le type de barre");
        TypeBarre type= choisiType();
        Barre b = new Barre(id,n1,n2,type);
        this.getListeBarres().add(b);
    }
    /**
     * premet de choisir le type de la barre qu'on veut créer dans le catalogue
     * @param Treillis
     * @return TypeBarre
     */
    public TypeBarre choisiType() {
        System.out.println("liste des points disponibles : ");
        int nbr = this.getCatalogue().getListe().size();
        for (int i = 0; i < nbr; i++) {
            TypeBarre t = this.getCatalogue().getListe().get(i);
                System.out.println(i+1 + ") " + t);
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
                return this.getCatalogue().getListe().get(rep-1);
            }
        }
    }
    
    /**
     * permet de choisir un Noeud parmi les noeuds existants pour créer une barre
     * @param Treillis
     * @return Noeud
     */
    public Noeud choisiNoeud() {
        System.out.println("liste des points disponibles : ");
        int nbr = this.getListeNoeuds().size();
        for (int i = 0; i < nbr; i++) {
            Noeud n = this.getListeNoeuds().get(i);
                System.out.println(i+1 + ") " + n);
            }
        
        if (nbr == 0) {
            System.out.println("Aucun noeud disponible");
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
                return this.getListeNoeuds().get(rep-1);
            }
        }
    }
    
    public Barre choisiBarre(){
        System.out.println("liste des barres disponibles : ");
        int nbr = this.getListeBarres().size();
        for (int i = 0; i < nbr; i++) {
            Barre b = this.getListeBarres().get(i);
                System.out.println(i+1 + ") " + b);
            }
        
        if (nbr == 0) {
            System.out.println("Aucune barre disponible");
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
                return this.getListeBarres().get(rep-1);
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

    public static Treillis treillisTest() {
        Treillis test=new Treillis();
        Point p0= new Point(0,-3);
        Point p1=new Point(0,1);
        Point p2=new Point(-1,-1);
        TriangleTerrain t1= new TriangleTerrain(1,p0,p1,p2);
        Point p6= new Point(1,-1);
        AppuiDouble ap2=new AppuiDouble(1,t1,0,0.75);
        AppuiSimple ap=new AppuiSimple(2,t1,0,0.25);
        NoeudSimple n=new NoeudSimple(3,p6);
        test.getTerrain().getTriangles().add(t1);
        test.getListeNoeuds().add(ap2);
        test.getListeNoeuds().add(ap);
        test.getListeNoeuds().add(n);
        Barre b1= new Barre(1,ap2,n);
        Barre b2=new Barre(2,ap,n);
        Barre b3=new Barre(3,ap2,ap);
        test.getCatalogue().add(b1.getType());
        test.getListeBarres().add(b1);
        test.getListeBarres().add(b2);
        test.getListeBarres().add(b3);
        return test;
    }
    
    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher le treillis");
            System.out.println("2) ajouter un noeud");
            System.out.println("3) ajouter une barre sur deux noeuds existants");
            System.out.println("4) afficher le rectangle englobant");
            System.out.println("5) retirer des noeuds");
            System.out.println("6) retirer des barres");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 2) {
                System.out.println("1)NoeudSimple"+"\n"+"2)AppuiSimple"+"\n"+"3)AppuiDouble");
                int type=Lire.i();
                Point np = Point.demandePoint();
                if (type==1){
                    this.ajouterNoeudSimple(np);
                }
                else if (type==2){
                    System.out.println("Entrez le numéro du triangle surlequel vous voulez placez l'appui");
                    this.ajouterAppuiSimple(np,this.getTerrain().getTriangles().get(Lire.i()));
                }
                else if(type==3){
                    System.out.println("Entrez le numéro du triangle surlequel vous voulez placez l'appui");
                    this.ajouterAppuiSimple(np,this.getTerrain().getTriangles().get(Lire.i()));
                }
            } else if (rep == 3) {
                System.out.println("choisissez le début de la barre");
                Noeud deb = this.choisiNoeud();
                if (deb != null) {
                    System.out.println("choisissez la fin de la barre");
                    Noeud fin = this.choisiNoeud();
                    this.ajouterBarre(deb,fin);
                }
            } else if (rep == 4) {
                System.out.println("maxX = " + this.maxX() + " ; "
                       + "minX = " + this.minX() + "\n"
                       + "maxY = " + this.maxY() + " ; "
                       + "minY = " + this.minY() + "\n");
            } else if (rep == 5) {
                System.out.println("choisissez un Noeud :");
                Noeud n= this.choisiNoeud();
                this.supprimerNoeud(n);
            } else if (rep == 6) {
                System.out.println("choisissez une Barre :");
                Barre b= this.choisiBarre();
                this.supprimerBarre(b);
            }
        }
    }
   
    // ces méthodes peuvent être améliorer un prenant en compte les triangles terrain
    public double maxX() {
        if (this.getListeNoeuds().isEmpty()) {
            return 0;
        } else {
            double max = this.getListeNoeuds().get(0).getPos().getX();
            for (int i = 1; i < this.getListeNoeuds().size(); i++) {
                double cur = this.getListeNoeuds().get(i).getPos().getX();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }
    
    public double minX() {
        if (this.getListeNoeuds().isEmpty()) {
            return 0;
        } else {
            double min = this.getListeNoeuds().get(0).getPos().getX();
            for (int i = 1; i < this.getListeNoeuds().size(); i++) {
                double cur = this.getListeNoeuds().get(i).getPos().getX();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }
    
    public double maxY() {
        if (this.getListeNoeuds().isEmpty()) {
            return 0;
        } else {
            double max = this.getListeNoeuds().get(0).getPos().getY();
            for (int i = 1; i < this.getListeNoeuds().size(); i++) {
                double cur = this.getListeNoeuds().get(i).getPos().getY();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }

    public double minY() {
        if (this.getListeNoeuds().isEmpty()) {
            return 0;
        } else {
            double min = this.getListeNoeuds().get(0).getPos().getY();
            for (int i = 1; i < this.getListeNoeuds().size(); i++) {
                double cur = this.getListeNoeuds().get(i).getPos().getY();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }
   
    public void dessine(GraphicsContext context) {
        this.getTerrain().dessine(context);
        for (Noeud n : this.getListeNoeuds()) {
            n.dessine(context);
        }
        for (Barre b : this.getListeBarres()) {
            b.dessine(context);
        }
    }
    
    /**
     * affiche la matrice des forces de traction/compression des barres et de récation des noeuds
     * @param Treillis
     * reste a faire: tester si les forces sont supérieures aux valeurs des types de barres
     *               afficher T1, T2,...
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
                for(int j=0; j<nb; j++){
                    if((this.getListeNoeuds().get(i)==this.getListeBarres().get(j).getNoeudDebut())){
                        m.setCoeffs(i*2, j, Math.cos(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),
                                this.getListeBarres().get(j).getNoeudFin().getPos())));
                        m.setCoeffs(i*2+1, j, Math.sin(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),
                                this.getListeBarres().get(j).getNoeudFin().getPos())));
                    }
                    if( this.getListeNoeuds().get(i)==this.getListeBarres().get(j).getNoeudFin()){
                        m.setCoeffs(i*2, j, Math.cos(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),
                                this.getListeBarres().get(j).getNoeudDebut().getPos())));
                        m.setCoeffs(i*2+1, j, Math.sin(getAngleAlpha(this.getListeNoeuds().get(i).getPos(),
                                this.getListeBarres().get(j).getNoeudDebut().getPos())));
                    }
                }
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
        else if (c.getY()>=a.getY()){
            y=c.getY()-a.getY();
            angle= Math.PI-Math.atan2(y, x);
        }
    }
    else if(c.getX()>a.getY()){
        x=c.getX()-a.getX();
        if (c.getY()>=a.getY()){
            y=c.getY()-a.getY();
                angle= Math.atan2(y, x);
            }
        else if (a.getY()>c.getY()){
            y=a.getY()-c.getY();
            angle= -1*Math.atan2(y, x);
            }
        }
    else if(a.getX()==c.getX()){
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
   

 

