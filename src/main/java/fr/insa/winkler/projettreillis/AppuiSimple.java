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
   
  public double getAngleBeta(){
  int debut=this.getPoint1();
  int fin= this.getPoint2();
  double angle=0;
  if((debut==0) && (fin==1)){
      System.out.println("test 2");
      if((TriangleTerrain.positivite(this.getTerrain().getPT2(),this.getTerrain().getPT0(),this.getTerrain().getPT1()))==1){
          System.out.println("test 3");
          if(this.getTerrain().getPT0().getX()<=this.getTerrain().getPT1().getX()){
              System.out.println("test");
              angle =((Math.PI/2)*-1+ Treillis.getAngleAlpha(this.getTerrain().getPT0(), this.getTerrain().getPT1()));
          }
          else{
              angle= (Math.PI/2 +Treillis.getAngleAlpha(this.getTerrain().getPT1(), this.getTerrain().getPT0()));
          }
      }
      if((TriangleTerrain.positivite(this.getTerrain().getPT2(),this.getTerrain().getPT0(),this.getTerrain().getPT1()))==-1){
          if(this.getTerrain().getPT0().getX()<=this.getTerrain().getPT1().getX()){
              angle= (Math.PI/2+ Treillis.getAngleAlpha(this.getTerrain().getPT0(), this.getTerrain().getPT1()));
          }
          else{
              angle= ((Math.PI/2)*-1 +Treillis.getAngleAlpha(this.getTerrain().getPT1(), this.getTerrain().getPT0()));
          }
      }
  }

    if((debut==1) && (fin==2)){
      if((TriangleTerrain.positivite(this.getTerrain().getPT0(),this.getTerrain().getPT1(),this.getTerrain().getPT2()))==1){
          if(this.getTerrain().getPT1().getX()<=this.getTerrain().getPT2().getX()){
              angle= ((Math.PI/2)*-1+ Treillis.getAngleAlpha(this.getTerrain().getPT1(), this.getTerrain().getPT2()));
          }
          else{
              angle= (Math.PI/2 +Treillis.getAngleAlpha(this.getTerrain().getPT2(), this.getTerrain().getPT1()));
          }
      }
      if((TriangleTerrain.positivite(this.getTerrain().getPT0(),this.getTerrain().getPT1(),this.getTerrain().getPT2()))==-1){
          if(this.getTerrain().getPT1().getX()<=this.getTerrain().getPT2().getX()){
              angle= (Math.PI/2+ Treillis.getAngleAlpha(this.getTerrain().getPT1(), this.getTerrain().getPT2()));
          }
          else{
              angle= ((Math.PI/2)*-1 +Treillis.getAngleAlpha(this.getTerrain().getPT2(), this.getTerrain().getPT1()));
          }
      }
  }

if((debut==2) && (fin==0)){
      if((TriangleTerrain.positivite(this.getTerrain().getPT1(),this.getTerrain().getPT2(),this.getTerrain().getPT0()))==1){
          if(this.getTerrain().getPT2().getX()<=this.getTerrain().getPT0().getX()){
              angle= ((Math.PI/2)*-1+ Treillis.getAngleAlpha(this.getTerrain().getPT2(), this.getTerrain().getPT0()));
          }
          else{
              angle= (Math.PI/2 +Treillis.getAngleAlpha(this.getTerrain().getPT0(), this.getTerrain().getPT2()));
          }
      }
      if((TriangleTerrain.positivite(this.getTerrain().getPT1(),this.getTerrain().getPT2(),this.getTerrain().getPT0()))==-1){
          if(this.getTerrain().getPT2().getX()<=this.getTerrain().getPT0().getX()){
              angle= (Math.PI/2+ Treillis.getAngleAlpha(this.getTerrain().getPT2(), this.getTerrain().getPT0()));
          }
          else{
              angle= ((Math.PI/2)*-1 +Treillis.getAngleAlpha(this.getTerrain().getPT0(), this.getTerrain().getPT2()));
          }
      }
  }
  return angle;
}
}
