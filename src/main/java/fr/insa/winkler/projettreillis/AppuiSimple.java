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
public class AppuiSimple extends Appui{
    
    public AppuiSimple (int identificateur, TriangleTerrain terrain, int point1, int point2, double position) {
        super(identificateur,terrain,point1,point2,position);
    }
    
   public AppuiSimple(int identificateur, TriangleTerrain terrain,Point p){
       super(identificateur, terrain, p);
   }
   
   public String toString(){
       return "AppuiSimple;"+ this.getIdentifiant()+";"+this.getTriangle().getIdentificateur()+";"+ this.getPoint1()+";" 
               +this.getPosition();
   }
   
   /**
    * calcul l'angle que fait le vecteur normal au segment de terrain de l'appui avec l'horizontale (beta)
    * @return angle beta (double)
    */
    public double getAngleBeta(){
    int debut=this.getPoint1();
    double angle=0;
    if(debut==0){
        if((TriangleTerrain.positivite(this.getTriangle().getPT2(),this.getTriangle().getPT0(),this.getTriangle().getPT1()))==1){
            if(this.getTriangle().getPT0().getX()<=this.getTriangle().getPT1().getX()){
                angle =((Math.PI/2)*-1+ Treillis.getAngleAlpha(this.getTriangle().getPT0(), this.getTriangle().getPT1()));
            }
            else{
                angle= (Math.PI/2 +Treillis.getAngleAlpha(this.getTriangle().getPT1(), this.getTriangle().getPT0()));
            }
        }
        if((TriangleTerrain.positivite(this.getTriangle().getPT2(),this.getTriangle().getPT0(),this.getTriangle().getPT1()))==-1){
            if(this.getTriangle().getPT0().getX()<=this.getTriangle().getPT1().getX()){
                angle= (Math.PI/2+ Treillis.getAngleAlpha(this.getTriangle().getPT0(), this.getTriangle().getPT1()));
            }
            else{
                angle= ((Math.PI/2)*-1 +Treillis.getAngleAlpha(this.getTriangle().getPT1(), this.getTriangle().getPT0()));
            }
        }
    }
    else if(debut==1){
        if((TriangleTerrain.positivite(this.getTriangle().getPT0(),this.getTriangle().getPT1(),this.getTriangle().getPT2()))==1){
            if(this.getTriangle().getPT1().getX()<=this.getTriangle().getPT2().getX()){
                angle= ((Math.PI/2)*-1+ Treillis.getAngleAlpha(this.getTriangle().getPT1(), this.getTriangle().getPT2()));
            }
            else{
                angle= (Math.PI/2 +Treillis.getAngleAlpha(this.getTriangle().getPT2(), this.getTriangle().getPT1()));
            }
        }
        if((TriangleTerrain.positivite(this.getTriangle().getPT0(),this.getTriangle().getPT1(),this.getTriangle().getPT2()))==-1){
            if(this.getTriangle().getPT1().getX()<=this.getTriangle().getPT2().getX()){
                angle= (Math.PI/2+ Treillis.getAngleAlpha(this.getTriangle().getPT1(), this.getTriangle().getPT2()));
            }
            else{
                angle= ((Math.PI/2)*-1 +Treillis.getAngleAlpha(this.getTriangle().getPT2(), this.getTriangle().getPT1()));
            }
        }
    } 
    else if(debut==2){
        if((TriangleTerrain.positivite(this.getTriangle().getPT1(),this.getTriangle().getPT2(),this.getTriangle().getPT0()))==1){
            if(this.getTriangle().getPT2().getX()<=this.getTriangle().getPT0().getX()){
                angle= ((Math.PI/2)*-1+ Treillis.getAngleAlpha(this.getTriangle().getPT2(), this.getTriangle().getPT0()));
            }
            else{
                angle= (Math.PI/2 +Treillis.getAngleAlpha(this.getTriangle().getPT0(), this.getTriangle().getPT2()));
            }
        }
        if((TriangleTerrain.positivite(this.getTriangle().getPT1(),this.getTriangle().getPT2(),this.getTriangle().getPT0()))==-1){
            if(this.getTriangle().getPT2().getX()<=this.getTriangle().getPT0().getX()){
                angle= (Math.PI/2+ Treillis.getAngleAlpha(this.getTriangle().getPT2(), this.getTriangle().getPT0()));
            }
            else{
                angle= ((Math.PI/2)*-1 +Treillis.getAngleAlpha(this.getTriangle().getPT0(), this.getTriangle().getPT2()));
            }
        }
    }
    return angle;
    }
}
