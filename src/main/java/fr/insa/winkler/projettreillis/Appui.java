/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;
/**
 *
 * @author mariewinkler
 */
public abstract class Appui extends Noeud{
    
    
    private TriangleTerrain triangleTerrain;
    private int point1;
    private int point2;
    private double position;
    
    public Appui (int identificateur, TriangleTerrain terrain, int point1, double position) {
        super(identificateur);
        this.triangleTerrain= terrain;
        this.point1=point1;
        this.point2=point1+1%3;
        this.position=position;
    }
    /**
     * constructeur permettant de créer un appui à partir d'un id, d'un triangle et d'un point
     * @param identificateur int
     * @param terrain TriangleTerrain
     * @param p Point (position de l'appui)
     */
    public Appui(int identificateur, TriangleTerrain terrain,Point p){
        super(identificateur);
        this.triangleTerrain=terrain;
        if(TriangleTerrain.positivite(p,this.getTriangle().getPT0(), this.getTriangle().getPT1())==0){
            this.point1=0;
            this.point2=1;
            if(this.getTriangle().getPT0().getX()!= this.getTriangle().getPT1().getX()){
                this.position=(p.getX()-Math.min(this.getTriangle().getPT0().getX(), this.getTriangle().getPT1().getX()))
                        /(Math.max(this.getTriangle().getPT0().getX(), this.getTriangle().getPT1().getX())
                        -Math.min(this.getTriangle().getPT0().getX(), this.getTriangle().getPT1().getX()));    
            }
            else{
                this.position=(p.getY()-Math.min(this.getTriangle().getPT0().getY(),this.getTriangle().getPT1().getY()))
                        /(Math.max(this.getTriangle().getPT0().getY(), this.getTriangle().getPT1().getY())
                        -Math.min(this.getTriangle().getPT0().getY(), this.getTriangle().getPT1().getY()));
            }
        }
        else if(TriangleTerrain.positivite(p,this.getTriangle().getPT1(), this.getTriangle().getPT2())==0){
            this.point1=1;
            this.point2=2;
            if(this.getTriangle().getPT1().getX()!= this.getTriangle().getPT2().getX()){
                this.position=(p.getX()-Math.min(this.getTriangle().getPT1().getX(), this.getTriangle().getPT2().getX()))
                        /(Math.max(this.getTriangle().getPT1().getX(), this.getTriangle().getPT2().getX())
                        -Math.min(this.getTriangle().getPT1().getX(), this.getTriangle().getPT2().getX()));
            }
            else{
                this.position=(p.getY()-Math.min(this.getTriangle().getPT1().getY(),this.getTriangle().getPT2().getY()))
                        /(Math.max(this.getTriangle().getPT1().getY(), this.getTriangle().getPT2().getY())
                        -Math.min(this.getTriangle().getPT1().getY(), this.getTriangle().getPT2().getY()));
            }
        }
        else if(TriangleTerrain.positivite(p,this.getTriangle().getPT2(), this.getTriangle().getPT0())==0){
            this.point1=2;
            this.point2=0;
            if(this.getTriangle().getPT2().getX()!= this.getTriangle().getPT0().getX()){
                this.position=(p.getX() -Math.min(this.getTriangle().getPT2().getX(), this.getTriangle().getPT0().getX()))
                        /(Math.max(this.getTriangle().getPT2().getX(), this.getTriangle().getPT0().getX())
                        -Math.min(this.getTriangle().getPT2().getX(), this.getTriangle().getPT0().getX()));
            }
             else{
                this.position=(p.getY()-Math.min(this.getTriangle().getPT2().getY(),this.getTriangle().getPT0().getY()))
                        /(Math.max(this.getTriangle().getPT2().getY(), this.getTriangle().getPT0().getY())
                        -Math.min(this.getTriangle().getPT2().getY(), this.getTriangle().getPT0().getY()));
            }
        }
    }
    
    public abstract String toString();
    
    /**
    * méthode permettant d'obtenir le point de position d'un appui
    * @return Point
    */
    public Point getPos(){
        Point p1=new Point(0,0);
        Point p2=new Point(0,0);
        if(this.point1==0){
            p1=this.getTriangle().getPT0();
            p2=this.getTriangle().getPT1();
        }
        if(this.point1==1){
            p1=this.getTriangle().getPT1();
            p2=this.getTriangle().getPT2();
        }
        if(this.point1==2){
            p1=this.getTriangle().getPT2();
            p2=this.getTriangle().getPT0();
        }
        double x=(Math.max(p1.getX(), p2.getX())-Math.min(p1.getX(), p2.getX()))*this.getPosition()+Math.min(p1.getX(), p2.getX());
        double y=(Math.max(p1.getY(), p2.getY())-Math.min(p1.getY(),p2.getY()))*this.getPosition()+Math.min(p1.getY(), p2.getY());
        Point p=new Point(x,y);
        return p;
    }
    

    public TriangleTerrain getTriangle() {
        return triangleTerrain;
    }

    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }

    public double getPosition() {
        return position;
    }

    public void setTerrain(TriangleTerrain terrain) {
        this.triangleTerrain = terrain;
    }

    public void setPoint1(int point1) {
        this.point1 = point1;
    }

    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    public void setPosition(double position) {
        this.position = position;
    }
    
}
