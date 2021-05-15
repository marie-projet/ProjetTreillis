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
    
    public String modifierZC(double xmin, double xmax, double ymin, double ymax){
        String mes="";
        for (Noeud n:this.getListeNoeuds()){
            if((n.getPos().getX()<xmin)||(n.getPos().getX()>xmax)||(n.getPos().getY()<ymin)||(n.getPos().getY()>ymax)){
                mes=mes+"Le noeud "+n.getIdentifiant()+" n'est plus dans la zone constrcutible"+"\n";
            }
        }
        if(mes==""){
            this.getTerrain().setXmin(xmin);
            this.getTerrain().setXmax(xmax);
            this.getTerrain().setYmin(ymin);
            this.getTerrain().setYmax(ymax);
            mes=mes+"Zone constructible modifiée !";
        }
        return mes;
    }
    
    public String ajouterTriangle(int id, double x0, double y0, double x1, double y1, double x2, double y2){
        String mes="";
        for(TriangleTerrain t:this.getTerrain().getTriangles()){
            if (t.getIdentificateur()==id){
                mes=mes+"Il existe déjà un triangle avec cet identifiant";
            }
        }
        if (mes==""){
            Point p0=new Point(x0,y0);
            Point p1=new Point(x1,y1);
            Point p2=new Point(x2,y2);
            this.getTerrain().getTriangles().add(new TriangleTerrain(id,p0,p1,p2));
        }
        System.out.println(mes);
        return mes;
    }
    
    /**
     * crée un NoeudSimple à partir d'un point`
     * @param j int (identifiant du Noeud)
     * @param p Point (position du Noeud)
     * @return String message d'erreur
     */
    public String ajouterNoeudSimple ( int j,Point p){
        String mes="";
        for (int i=0; i<this.getListeNoeuds().size(); i++){
            if(this.getListeNoeuds().get(i).getIdentifiant()==j){
                mes=mes+"Il existe déjà un noeud avec cet identifiant"+"\n";
            }
        }
        for(int i=0; i<this.getTerrain().getTriangles().size();i++){
            if(this.getTerrain().getTriangles().get(i).estDansTriangle(p)==true){
                mes=mes+"Le point est dans un triangle terrain"+"\n";
 
            }
            if((p.getX()<this.getTerrain().getXmin())||(p.getX()>this.getTerrain().getXmax())
                    ||(p.getY()<this.getTerrain().getYmin())|| (p.getY()>this.getTerrain().getYmax())){
                mes=mes+"Le point n'est pas dans la zone constructible";
            }
        }
        if(mes==""){
            NoeudSimple n = new NoeudSimple(j,p);
            this.getListeNoeuds().add(n);
        }
        System.out.println(mes);
        return mes;
    }

    
     /**
     * créee un AppuiSimple à partir d'un point et d'un TriangleTerrain
     * @param id int (identifiant)
     * @param p Point (position du noeud)
     * @return String avec message d'erreur
     */
    public String ajouterAppuiSimple(int id, int triangle, int segment, double pos){
        String mes="";
        for (int i=0; i<this.getListeNoeuds().size(); i++){
            if(this.getListeNoeuds().get(i).getIdentifiant()==id){
                mes=mes+"Il existe déjà un noeud avec cet identifiant"+"\n";
            }
        }
        if((pos<0)||(pos>1)){
            mes=mes+"La position doit être un entier entre 0 et 1"+"\n";
        }
        if((segment!=0)&&(segment!=1)&&(segment!=2)){
            mes=mes+"Le numéro du premier point du segment sur lequel réside l'appui doit être un entier entre 1 et 3"+"\n";
        }
        if(mes==""){
            for(int i=0;i<this.getTerrain().getTriangles().size(); i++){
                if(this.getTerrain().getTriangles().get(i).getIdentificateur()==triangle){
                    AppuiSimple a = new AppuiSimple(id,this.getTerrain().getTriangles().get(i), segment,pos);
                    this.getListeNoeuds().add(a);
                }
            }
        }
        System.out.println(mes);
        return mes;
    }
    /**
     * créee un AppuiDouble à partir d'un point et d'un TriangleTerrain
     * @param id int (identifiant du Noeud)
     * @param p Point (position du noeud)
     * @retrun message d'erreur
     */
    public String ajouterAppuiDouble(int id, int triangle, int segment, double pos){
        String mes="";
        for (int i=0; i<this.getListeNoeuds().size(); i++){
            if(this.getListeNoeuds().get(i).getIdentifiant()==id){
                mes=mes+"Il existe déjà un noeud avec cet identifiant"+"\n";
            }
        }
        if((pos<0)||(pos>1)){
            mes=mes+"La position doit être un entier entre 0 et 1"+"\n";
        }
        if((segment!=0)&&(segment!=1)&&(segment!=2)){
            mes=mes+"Le numéro du premier point du segment sur lequel réside l'appui doit être un entier entre 1 et 3"+"\n";
        }
        if(mes==""){
            for(int i=0;i<this.getTerrain().getTriangles().size(); i++){
                if(this.getTerrain().getTriangles().get(i).getIdentificateur()==triangle){
                    AppuiDouble a = new AppuiDouble(id,this.getTerrain().getTriangles().get(i), segment,pos);
                    this.getListeNoeuds().add(a);
                }
            }
        }
        System.out.println(mes);
        return mes;
    }
    
    public String supprimerNoeud (int i){
        String mes="";
        for (int j=0; j<this.getListeNoeuds().size(); j++){
            if(this.getListeNoeuds().get(j).getIdentifiant()==i){
                for (int n=0; n<this.getListeBarres().size(); n++){
                    if ((this.getListeNoeuds().get(j)==this.getListeBarres().get(n).getNoeudDebut()) || 
                    (this.getListeNoeuds().get(j)==this.getListeBarres().get(n).getNoeudFin())){
                        mes=mes+"Le noeud est le noeud de début ou de fin d'une barre"+"\n";
                    }
                }
                if (mes==""){
                    this.getListeNoeuds().remove(this.getListeNoeuds().get(j));
                    mes="Noeud supprimé !";
                }
            }
        }
    System.out.println(mes);
    return mes;
    }
    
    /**
    * crée une nouvelle barre à partir de deux noeuds
    * @param id int (identifiant du Noeud)
    * @param type TypeBarre
    * @param n1 Noeud 1
    * @param n2 Noeud 2
    * @return message d'erreur
    */

    public String ajouterBarre (int id, int type, int deb, int fin ){
        String mes="";
        for (int i=0; i<this.getListeBarres().size(); i++){
            if(this.getListeBarres().get(i).getIdentifiant()==id){
                mes=mes+"Il existe déjà une barre avec cet identifiant"+"\n";
            }
        }
        TypeBarre typeB=new TypeBarre();
        Noeud n1=this.getListeNoeuds().get(0);
        Noeud n2=this.getListeNoeuds().get(0);
        for (int i=0; i<this.getCatalogue().getListe().size(); i++){
            if(this.getCatalogue().getListe().get(i).getIdentificateur()==type){
                typeB=this.getCatalogue().getListe().get(i);
            }
        }
        for (int i=0; i<this.getListeNoeuds().size(); i++){
            if(this.getListeNoeuds().get(i).getIdentifiant()==deb){
                n1=this.getListeNoeuds().get(i);
            }
            if(this.getListeNoeuds().get(i).getIdentifiant()==fin){
                n2=this.getListeNoeuds().get(i);
            }
        }
        if((n1.distance(n2)<typeB.getLongueurMin())|| n1.distance(n2)>typeB.getLongueurMax()){
            mes=mes+"La barre ne respecte pas les dimensions maximales ou minimales de son type"+"\n";
        }
        if(mes==""){
            Barre b = new Barre(id,n1,n2,typeB);
            this.getListeBarres().add(b);
            mes="Barre ajoutée !";
        }
        System.out.println(mes);
        return mes;
    }
    
         
    public String supprimerBarre (int i){
        String mes="";
        for (int j=0; j<this.getListeBarres().size(); j++){
            if(this.getListeBarres().get(j).getIdentifiant()==i){   
                this.getListeBarres().remove(this.getListeBarres().get(j));
                mes="Barre supprimée !";
            }
         }
    System.out.println(mes);
    return mes;
    }
    
    public void supprimerTout (){
         for (int i=0; i<this.listeNoeuds.size() ;i++){
             this.getListeNoeuds().remove(i);
         }
         for (int i =0 ; i<this.listeBarres.size() ; i++){
             this.getListeBarres().remove(i);
         }       
     }
    
     public String ajouterTypeBarre(int id, double cam, double longmin, double longmax, double resmaxt, double resmaxc ){
    String mes="";
     for(int i=0; i<this.getCatalogue().getListe().size(); i++){
            if(this.getCatalogue().getListe().get(i).getIdentificateur()==id){
                mes=mes+"Il existe déjà un type de barre avec cet identifiant"+"\n";
            }
            if (cam <0){
                mes = mes+"Le coût au metre ne peut être négatif"+"\n";
            }
           if (longmin <0) {
               mes = mes+"La longueur minimale ne peut être négative"+"\n";
           } else {
               if (longmax <0 ){
                   mes = mes+"La longueur maximale ne peut être négative"+"\n";
               }
           }
           if (resmaxt <0){
               mes=mes+"La resistance maximale à la tension ne peut être négative"+"\n";
           }
           if (resmaxc <0){
               mes=mes+"La resistance maximale à la ompression ne peut être négative"+"\n";
           }
           if(longmax<longmin){
               mes=mes+"La longueur maximale doit être inférieure à la longueur minimale"+"\n";
           }
     }
            if(mes==""){
                TypeBarre n = new TypeBarre(id, cam, longmin, longmax, resmaxt, resmaxc );
                this.getCatalogue().getListe().add(n);
                mes="Type de barre ajouté au catalogue!";
            } 
    System.out.println(mes);
    return mes;
    }
    
    public String supprimerTypeBarre(int i){
        String mes="";
        for(int j=0; j<this.getCatalogue().getListe().size(); j++){
            if(this.getCatalogue().getListe().get(j).getIdentificateur()==i){
                for (int n=0; n<this.getListeBarres().size(); n++){
                    if(this.getListeBarres().get(n).getType()==this.getCatalogue().getListe().get(j)){
                    mes=mes+"Une barre du treillis possède ce type, il ne peut donc pas être supprimé"+"\n";
                    }
                }
                if(mes==""){
                    this.getCatalogue().getListe().remove(this.getCatalogue().getListe().get(j));
                    mes="Type supprimé du catalogue !";
                }
            }
        }
        System.out.println(mes);
        return mes;
    }
    
     
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
            for(int i=0; i<this.getTerrain().getTriangles().size(); i++){
                if(this.getTerrain().getTriangles().get(i).getPT0().getX()>max){
                    max=this.getTerrain().getTriangles().get(i).getPT0().getX();
                }
                if(this.getTerrain().getTriangles().get(i).getPT1().getX()>max){
                    max=this.getTerrain().getTriangles().get(i).getPT1().getX();
                }
                if(this.getTerrain().getTriangles().get(i).getPT2().getX()>max){
                    max=this.getTerrain().getTriangles().get(i).getPT2().getX();
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
            for(int i=0; i<this.getTerrain().getTriangles().size(); i++){
                if(this.getTerrain().getTriangles().get(i).getPT0().getX()<min){
                    min=this.getTerrain().getTriangles().get(i).getPT0().getX();
                }
                if(this.getTerrain().getTriangles().get(i).getPT1().getX()<min){
                    min=this.getTerrain().getTriangles().get(i).getPT1().getX();
                }
                if(this.getTerrain().getTriangles().get(i).getPT2().getX()<min){
                    min=this.getTerrain().getTriangles().get(i).getPT2().getX();
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
            for(int i=0; i<this.getTerrain().getTriangles().size(); i++){
                if(this.getTerrain().getTriangles().get(i).getPT0().getY()>max){
                    max=this.getTerrain().getTriangles().get(i).getPT0().getY();
                }
                if(this.getTerrain().getTriangles().get(i).getPT1().getY()>max){
                    max=this.getTerrain().getTriangles().get(i).getPT1().getY();
                }
                if(this.getTerrain().getTriangles().get(i).getPT2().getY()>max){
                    max=this.getTerrain().getTriangles().get(i).getPT2().getY();
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
            for(int i=0; i<this.getTerrain().getTriangles().size(); i++){
                if(this.getTerrain().getTriangles().get(i).getPT0().getY()<min){
                    min=this.getTerrain().getTriangles().get(i).getPT0().getY();
                }
                if(this.getTerrain().getTriangles().get(i).getPT1().getY()<min){
                    min=this.getTerrain().getTriangles().get(i).getPT1().getY();
                }
                if(this.getTerrain().getTriangles().get(i).getPT2().getY()<min){
                    min=this.getTerrain().getTriangles().get(i).getPT2().getY();
                }
            }
            return min;
        }
    }
        /**
     * premet de choisir le type de la barre qu'on veut créer dans le catalogue
     * @param Treillis
     * @return TypeBarre
     */
    public TypeBarre choisiType() {
        System.out.println("liste des types disponibles : ");
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
    public TriangleTerrain choisiTriangle(){
        System.out.println("liste des triangles disponibles : ");
        int nbr = this.getTerrain().getTriangles().size();
        for (int i = 0; i < nbr; i++) {
            TriangleTerrain t= this.getTerrain().getTriangles().get(i);
                System.out.println(i+1 + ") " + t);
            }
        
        if (nbr == 0) {
            System.out.println("Aucun triangle disponible");
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
                return this.getTerrain().getTriangles().get(rep-1);
            }
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
        test.getCatalogue().add(new TypeBarre(2,50,60,70,80,78));
        test.getListeBarres().add(b1);
        test.getListeBarres().add(b2);
        test.getListeBarres().add(b3);
        return test;
    }
    
    //il manque les points 4,6 et 9
    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher le treillis");
            System.out.println("2) modifier la zone constructible");
            System.out.println("3) ajouter un triangle de terrain");
            System.out.println("4) supprimer un triangle de terrain");
            System.out.println("5) ajouter un noeud");
            System.out.println("6) modifier l'emplacement d'un noeud");
            System.out.println("7) supprimer un noeud");
            System.out.println("8) ajouter une barre");
            System.out.println("9) modifier le type d'une barre");
            System.out.println("10) supprimer une barre");
            System.out.println("11) ajouter un type de barre dans le catalogue");
            System.out.println("12) supprimer un type de barre dans le catalogue");
            System.out.println("13) afficher le rectangle englobant");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if(rep==2){
                System.out.println("Entrez l'abscisse minimale");
                double xmin=Lire.d();
                System.out.println("Entrez l'abscisse maxiamle");
                double xmax=Lire.d();
                System.out.println("Entrez l'ordonnée minimale");
                double ymin=Lire.d();
                System.out.println("Entrez l'ordonnée maxiamle");
                double ymax=Lire.d();
                this.modifierZC(xmin, xmax, ymin, ymax);
            }else if(rep==3){
                System.out.println("Entrez l'identifiant du traingle");
                int id=Lire.i();
                System.out.println("Entrez l'abscisse du point 1");
                double x1=Lire.d();
                System.out.println("Entrez l'ordonnée du point 1");
                double y1=Lire.d();
                System.out.println("Entrez l'abscisse du point 2");
                double x2=Lire.d();
                System.out.println("Entrez l'ordonnée du point 2");
                double y2=Lire.d();
                System.out.println("Entrez l'abscisse du point 3");
                double x3=Lire.d();
                System.out.println("Entrez l'ordonnée du point 3");
                double y3=Lire.d();
                this.ajouterTriangle(id, x1, y1, x2, y2, x3, y3);
            }else if (rep == 5) {
                System.out.println("1)NoeudSimple"+"\n"+"2)AppuiSimple"+"\n"+"3)AppuiDouble");
                int type=Lire.i();
                System.out.println("Saisissez l'identifiant du Noeud");
                int id=Lire.i();
                if (type==1){
                    Point np = Point.demandePoint();
                    this.ajouterNoeudSimple(id,np);
                }
                else if (type==2){
                    TriangleTerrain t=this.choisiTriangle();
                    System.out.println("Entrez le numéro du premier point du segment de terrain surlequel placer l'appui");
                    int point=Lire.i();
                    System.out.println("Entrez la position de l'appui sur le segment");
                    double pos=Lire.d();
                    this.ajouterAppuiSimple(id,t.getIdentificateur(),point,pos);
                }
                else if(type==3){
                    TriangleTerrain t=this.choisiTriangle();
                    System.out.println("Entrez le numéro du premier point du segment de terrain surlequel placer l'appui");
                    int point=Lire.i();
                    System.out.println("Entrez la position de l'appui sur le segment");
                    double pos=Lire.d();
                    this.ajouterAppuiDouble(id,t.getIdentificateur(),point,pos);
                }
            } else if (rep == 7) {
                System.out.println("Choisissez un Noeud :");
                Noeud n= this.choisiNoeud();
                this.supprimerNoeud(n.getIdentifiant());
            } else if (rep == 8) {
                System.out.println("Saisissez l'identifiant de la barre");
                int id=Lire.i();
                System.out.println("Choisissez le type de barre");
                TypeBarre type= choisiType();
                System.out.println("Choisissez le noeud de début de la barre");
                Noeud deb = this.choisiNoeud();
                if (deb != null) {
                    System.out.println("Choisissez le noeud de fin de la barre");
                    Noeud fin = this.choisiNoeud();
                    this.ajouterBarre(id,type.getIdentificateur(),deb.getIdentifiant(),fin.getIdentifiant());
                }
            } else if (rep == 10) {
                System.out.println("Choisissez une Barre :");
                Barre b= this.choisiBarre();
                this.supprimerBarre(b.getIdentifiant());
            }else if (rep == 11) { 
                System.out.println ("Saississez un indentifiant");
                int id = Lire.i();
                System.out.println("Saissisez un cout au mètre");
                double cam = Lire.i();
                System.out.println("Saisissez une longueur maximale de barre");
                double longmax = Lire.i();
                System.out.println(" Saisissez une longueur minimale de barre ");
                double longmin = Lire.i();
                System.out.println ("Saisissez la resistance maximale de la barre à la tension");
                double resmaxt = Lire.i();
                System.out.println("Saisissez la resistance maximale à la compression");
                double resmaxc = Lire.i();
               this.ajouterTypeBarre(id, cam, longmin, longmax, resmaxt, resmaxc);
            } else if(rep == 12){
                System.out.println("Choisissez un type de barre du catalogue");
                TypeBarre tb = this.choisiType();
                this.supprimerTypeBarre(tb.getIdentificateur());
            }else if (rep == 13) {
                System.out.println("maxX = " + this.maxX() + " ; "
                       + "minX = " + this.minX() + "\n"
                       + "maxY = " + this.maxY() + " ; "
                       + "minY = " + this.minY() + "\n");
            }
        }
    }
   
   
    public void dessine(GraphicsContext context) {
        this.getTerrain().dessine(context);
        for (Noeud n : this.getListeNoeuds()) {
            n.dessine(context);
        }
        for (Barre b : this.getListeBarres()) {
            b.dessine(context, Color.BLACK);
        }
    }
    
    /**
     * affiche la matrice des forces de traction/compression des barres et de récation des noeuds
     * @param Treillis
     * reste a faire: tester si les forces sont supérieures aux valeurs des types de barres
     *               afficher T1, T2,...
     */
    public Matrice calculForces(){
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
        return m.subCols(m.getNbrCol()-1,m.getNbrCol()-1);
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
   

 

