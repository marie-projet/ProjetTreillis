/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.winkler.projettreillis;

/**
 *
 * @author elodieherrmann
 */
public class TriangleTerrain {
    private int identificateur;
    private Point PT0;
    private Point PT1;
    private Point PT2;
    
public TriangleTerrain (int identificateur, Point PT0, Point PT1, Point PT2){
    this.identificateur = identificateur; 
    this.PT0 = PT0;
    this.PT1 = PT1;
    this.PT2 = PT2;

}

/**
 * 
 * @param p Point à tester
 * @param p1 Premier point du segment de terrain
 * @param p2 Deuxième Point du segment de terrain
 * @return 0 si p est colinéaire à p1p2, 1 si l'angle (p1p2,p1p) est entre 0 et pi (mod 2pi); -1 sinon
 */
public static int positivite(Point p,Point p1, Point p2 ){
double angle=0;
double epsilon=Math.pow(10, -8);
Double p12= Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()));
Double p13= Math.sqrt((p1.getX()-p.getX())*(p1.getX()-p.getX())+(p1.getY()-p.getY())*(p1.getY()-p.getY()));
Double p23= Math.sqrt((p2.getX()-p.getX())*(p2.getX()-p.getX())+(p2.getY()-p.getY())*(p2.getY()-p.getY()));
double deltay= p2.getY()-p1.getY();
double deltax=p2.getX()-p1.getX();
if(deltax==0){
  if(p1.getY()<p2.getY()){
      if(p.getX()<=p1.getX()){
          angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13));
      }
      else{
          angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13))*-1;
      }
  }
  if (p1.getY()>p2.getY()){
      if(p.getX()<=p1.getX()){
          angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13))*-1;
      }
      else{
          angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13));
      }
  }
}
else{
double a=deltay/deltax;
double b= p1.getY()-a*p1.getX();
if(p1.getX()<=p2.getX()){
    if(p.getY()>=(a*p.getX()+b)){
        angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13));
    }
    else{
       angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13))*-1;
    }
}
if(p1.getX()>p2.getX()){
    if(p.getY()>(a*p.getX()+b)){
        angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13))*-1;
    }
    else{
        angle= Math.acos((p12*p12+p13*p13-p23*p23)/(2*p12*p13));
    }
}
}
System.out.println(angle);
if(Math.abs(angle % Math.PI)<epsilon){
    return 0;
}
if((angle>0 % 2*Math.PI )&& (angle < Math.PI % 2*Math.PI)){
    return 1;
}
else {
    return -1;
}
}

/**
 * 
 * @param p Point a tester
 * @return true si p est dans le TraingleTerrain, false sinon
 */
public boolean estDansTriangle(Point p){
    if((positivite(p,this.getPT0(),this.getPT1())==positivite(p,this.getPT1(),this.getPT2()))&&(positivite(p,this.getPT1(),this.getPT2())==positivite(p,this.getPT2(),this.getPT0()))){
        return true;
    }
    if(((positivite(p,this.getPT0(),this.getPT1()))==0)|| ((positivite(p,this.getPT1(),this.getPT2()))==0) ||((positivite(p,this.getPT2(),this.getPT0()))==0)){
        return true;
    }
    else{
        return false;
    }
}
    public int getIdentificateur() {
        return identificateur;
    }

    public Point getPT1() {
        return PT1;
    }

    public Point getPT2() {
        return PT2;
    }

    public Point getPT0() {
        return PT0;
    }

    public void setIdentificateur(int identificateur) {
        this.identificateur = identificateur;
    }

    public void setPT1(Point PT1) {
        this.PT1 = PT1;
    }

    public void setPT2(Point PT2) {
        this.PT2 = PT2;
    }

    public void setPT0(Point PT3) {
        this.PT0 = PT3;
    }
}
