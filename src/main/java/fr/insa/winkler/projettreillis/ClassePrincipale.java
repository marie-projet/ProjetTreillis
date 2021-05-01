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
public class ClassePrincipale {
    public static void main (String args[]){
       Treillis t= Treillis.treillisTest();
       t.calculForces();
       Point p1= new Point (0,0);
       Point p2=new Point(0,1);
       Point p= new Point (-3,4);
       //System.out.println(TriangleTerrain.positivite(p, p1, p2));
       //System.out.println(t.getTerrain().getTriangles().get(0).estDansTriangle(t.getListeNoeuds().get(2).getPos()));
       AppuiSimple ap=(AppuiSimple) t.getListeNoeuds().get(1);
       System.out.println(ap.getAngleBeta());
}
}